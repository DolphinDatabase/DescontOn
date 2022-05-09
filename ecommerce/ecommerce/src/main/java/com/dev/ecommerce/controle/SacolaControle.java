package com.dev.ecommerce.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import com.dev.ecommerce.dto.DescontoDTO;
import com.dev.ecommerce.dto.SacolaDTO;
import com.dev.ecommerce.modelos.Compra;
import com.dev.ecommerce.modelos.ItensPromocao;
import com.dev.ecommerce.repositorios.CompraRepositorio;
import com.dev.ecommerce.repositorios.ItensPromocaoRepositorio;
import com.dev.ecommerce.repositorios.PromocaoRepositorio;


@RestController
@RequestMapping("/sacola")
public class SacolaControle {

	@Autowired
	CompraRepositorio compraRepositorio;

	@Autowired
	PromocaoRepositorio promocaoRepositorio; 

	@Autowired
	ItensPromocaoRepositorio itensPromocaoRepositorio;

	@PostMapping
	public Compra salvar(@RequestBody Compra compra){
		return compraRepositorio.save(compra);
	}

	@PostMapping("/desconto")
	public List<DescontoDTO> buscarPromocao(@RequestBody List<SacolaDTO> itensCompra) {
		List<SacolaDTO> lista = new ArrayList<>();
		itensCompra.forEach(item->{
			List<ItensPromocao> find = itensPromocaoRepositorio.findAllByProduto(item.getId());
			if(!find.isEmpty()){
				item.setAcao(find.get(0).getPromocao().getAcao());
				item.setCondicao(find.get(0).getPromocao().getCondicao());
				lista.add(item);
			}
		});	
		
		String acao1 = "[Desconto]";
		String condicao1 = "[ProdutoSelecionado]";
		List<DescontoDTO> res = new ArrayList<>();

		for(SacolaDTO item:lista){
			if(item.getCondicao().contains(condicao1)) {
				//Desconto Simples
				if(item.getAcao().contains(acao1)) {
					String acao = item.getAcao().replaceAll("[\\D]", "");
					Double porcentagem = Double.parseDouble(acao);
					Double totalPorItem= item.getQuantidade() * item.getValor();
					DescontoDTO desconto = new DescontoDTO();
					desconto.setId(item.getId());
					desconto.setDesconto(totalPorItem * (porcentagem/100));
					res.add(desconto);
				}
			}
		}

		return res;

	}




}