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
import java.util.Optional;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import com.dev.ecommerce.dto.AplicarDescontoDTO;
import com.dev.ecommerce.dto.DescontoDTO;
import com.dev.ecommerce.dto.PromocaoDTO;
import com.dev.ecommerce.dto.SacolaDTO;
import com.dev.ecommerce.modelos.Compra;
import com.dev.ecommerce.modelos.ItensPromocao;
import com.dev.ecommerce.modelos.Produto;
import com.dev.ecommerce.modelos.Promocao;
import com.dev.ecommerce.repositorios.CompraRepositorio;
import com.dev.ecommerce.repositorios.ItensPromocaoRepositorio;
import com.dev.ecommerce.repositorios.ProdutoRepositorio;
import com.dev.ecommerce.repositorios.PromocaoRepositorio;
import com.dev.ecommerce.utils.Acao;
import com.dev.ecommerce.utils.Util;

@RestController
@RequestMapping("/sacola")
public class SacolaControle {
	
	List<Long> idsProdutosParaVerificacaoCompra = new ArrayList<Long>();
	ItensPromocao produtoPromocao = new ItensPromocao();

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
	
	@PostMapping("/verificarPromo")
	public List<PromocaoDTO> verificarPromo(@RequestBody List<Long> ids){
		List<PromocaoDTO> res = new ArrayList<>();
		ids.forEach(id->{
			Optional<Promocao> promocao = promocaoRepositorio.findById(id);
			if(promocao.isPresent()){
				Promocao exist = promocao.get();
				res.add(new PromocaoDTO(exist.getId(), exist.getNome(),exist.getDataCadastro(),exist.getCondicao(),exist.getAcao(),exist.getStatus()));
			}
		});
		return res;
	}
	
	@PostMapping("/parametrosPromo")
	public List<Long> parametrosPromo(@RequestBody Produto produto){
		idsProdutosParaVerificacaoCompra.add(produto.getId());
		idsProdutosParaVerificacaoCompra.forEach(item -> {
			produtoPromocao = itensPromocaoRepositorio.findAllByProdutoNoList(item);
		});
		return idsProdutosParaVerificacaoCompra;
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
		Integer quantidade = 0;
		for(SacolaDTO item:lista){
			quantidade += item.getQuantidade();
		}
		Acao acoes = new Acao();

		// acoes
		String ganhe = "Ganhe";
		String descontoProduto = "DescontoProduto";

		//condicao
		String produtoSelecionado = "ProdutoSelecionado";
		String categoria = "ProdutoCategoria";
		String menorValor = "ItemMenorValor";

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
			if(condicao.contains("CompraTotal")){
				engine.eval("CompraTotal="+total);
			}
			if(condicao.contains("QuantidadeTotal")){
				engine.eval("QuantidadeTotal="+quantidade);
			}

			//verificar condicao
			if(condicao.contains(produtoSelecionado)){
				if(acao.contains(menorValor)){
					res = (acoes.menorValor(lista,total,acao));
				}else{
					for(SacolaDTO item:lista){
						if(acao.contains(ganhe)){
							res.add(acoes.ganhe(item, acao));
						}
						if(acao.contains(descontoProduto)){
							res.add(acoes.descontoProduto(item,total,acao));
						}
					}
				}
				return res;
			}else{
				int index = 0;
				Boolean valido = false;
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
							if(c.contains("CompraTotal") || c.contains("QuantidadeTotal")){
								c = c.replace("]", "");
								c = c.replace("=", "==");
							}else{
								c = c.replace("]", "");
								c = c.replace("=", "==");
								c = (c.split(" ")[0]+index)+c.split(" ")[1];
							}
						}
						verify += c;
					}
					if(acao.contains(menorValor)){
						valido = (Boolean)engine.eval(verify);
					}else{
						if((Boolean)engine.eval(verify)){
							if(acao.contains(ganhe)){
								res.add(acoes.ganhe(item, acao));
							}
							if(acao.contains(descontoProduto)){
								res.add(acoes.descontoProduto(item,total,acao));
							}
						}

					}
					index++;
				}
				if(acao.contains(menorValor) && valido){

					res = acoes.menorValor(lista,total,acao);
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