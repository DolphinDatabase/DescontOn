package com.dev.ecommerce.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import com.dev.ecommerce.dto.AplicarDescontoDTO;
import com.dev.ecommerce.dto.DescontoDTO;
import com.dev.ecommerce.dto.SacolaDTO;
import com.dev.ecommerce.modelos.Compra;
import com.dev.ecommerce.modelos.ItensPromocao;
import com.dev.ecommerce.repositorios.CompraRepositorio;
import com.dev.ecommerce.repositorios.ItensPromocaoRepositorio;
import com.dev.ecommerce.repositorios.ProdutoRepositorio;
import com.dev.ecommerce.repositorios.PromocaoRepositorio;
import com.dev.ecommerce.utils.Acao;
import com.dev.ecommerce.utils.Util;

@RestController
@RequestMapping("/sacola")
public class SacolaControle {

	@Autowired
	CompraRepositorio compraRepositorio;

	@Autowired
	PromocaoRepositorio promocaoRepositorio;

	@Autowired
	ItensPromocaoRepositorio itensPromocaoRepositorio;

	@Autowired
	ProdutoRepositorio produtoRepositorio;

	@PostMapping
	public Compra salvar(@RequestBody Compra compra) {
		return compraRepositorio.save(compra);
	}

	@PostMapping("/verificarDesconto")
	public Map<String, List<SacolaDTO>> listarPromocao(@RequestBody List<SacolaDTO> items) {
		Map<String, List<SacolaDTO>> res = new HashMap<String, List<SacolaDTO>>();
		items.forEach(item -> {
			List<ItensPromocao> find = itensPromocaoRepositorio.findAllByProduto(item.getId());
			find.forEach(f -> {
				String key = f.getPromocao().getId() + ", " + f.getPromocao().getNome() + ", "
						+ f.getPromocao().getCondicao() + ", " + f.getPromocao().getAcao();
				if (res.containsKey(key)) {
					res.get(key).add(item);
				} else {
					List<SacolaDTO> value = new ArrayList<>();
					value.add(item);
					res.put(key, value);
				}
			});
		});
		return res;
	}

	@PostMapping("/aplicarDesconto")
	public List<DescontoDTO> aplicarDesconto(@RequestBody AplicarDescontoDTO sacola){
		List<SacolaDTO> lista = sacola.getSacola();
		Double total = sacola.getTotal();
		String condicao = sacola.getCondicao();
		String acao = sacola.getAcao();
		List<DescontoDTO> res = new ArrayList<>();
		ScriptEngineManager factory = new ScriptEngineManager();
		ScriptEngine engine = factory.getEngineByName("JavaScript");
		Acao acoes = new Acao();

		// acoes
		String ganhe = "Ganhe";
		String descontoProduto = "DescontoProduto";

		//condicao
		String produtoSelecionado = "ProdutoSelecionado";
		String categoria = "ProdutoCategoria";

		//definir variaveis
		try{
			if(condicao.contains(categoria)){
				int index = 0;
				for(SacolaDTO item:lista){
					engine.eval(categoria+index+"='"+item.getCategoria()+"'");
					index++;
				}
			}
			if(condicao.contains("ProdutoQuantidade")){
				int index = 0;
				for(SacolaDTO item:lista){
					engine.eval("ProdutoQuantidade"+index+"="+item.getQuantidade());
					index++;
				}
			}
			if(condicao.contains("ProdutoValor")){
				int index = 0;
				for(SacolaDTO item:lista){
					engine.eval("ProdutoValor"+index+"="+(item.getQuantidade()*item.getValor()));
					index++;
				}
			}

			//verificar condicao
			if(condicao.contains(produtoSelecionado)){
				for(SacolaDTO item:lista){
					if(acao.contains(ganhe)){
						res.add(acoes.ganhe(item, acao));
					}
					if(acao.contains(descontoProduto)){
						res.add(acoes.descontoProduto(item, acao));
					}
				}
				return res;
			}else{
				int index = 0;
				for(SacolaDTO item:lista){
					String verify="";
					for(String c:condicao.split("\n")){
						c = c.replace("[&&]", "&&");
						c = c.replace("[||]", "||");
						c = c.replace("[", "");
						if(c.contains(categoria)){
							c = c.replace("]", index+"=='");
							c += "'";
						}else{
							c = c.replace("]", "");
							c = (c.split(" ")[0]+index)+c.split(" ")[1];
						}
						verify += c;
					}
					if((Boolean)engine.eval(verify)){
						if(acao.contains(ganhe)){
							res.add(acoes.ganhe(item, acao));
						}
						if(acao.contains(descontoProduto)){
							res.add(acoes.descontoProduto(item, acao));
						}
					}
					index++;
				}
				return res;
			}
		}catch(Exception err){
			System.out.println(err.getMessage());
			res.add(new DescontoDTO(null,null));
			return res;
		}
	}

}