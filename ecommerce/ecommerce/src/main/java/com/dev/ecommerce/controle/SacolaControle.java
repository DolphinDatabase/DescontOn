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
				item.setStatus(find.get(0).getPromocao().getStatus());
				lista.add(item);
			}
		});	
		//acao e condicao desconto simples
		String descontoProduto = "[DescontoProduto]";
		String produtoSelecionado = "[ProdutoSelecionado]";
		//acao progressivo é a mesma que a desconto simples, o que muda é a condicao, linha 69
		String quantidadeMaior = "[ProdutoQuantidade >]";
		//brinde condicao é a mesma que progressiva mas muda a acao
		String ganhe = "[Ganhe]";
		//operadores novos - faltam do valorTotal
		String quantidadeMenor = "[ProdutoQuantidade <]";
		String quantidadeIgual = "[ProdutoQuantidade =]";

		List<DescontoDTO> res = new ArrayList<>();
		Util util = new Util();
		Acao acoes = new Acao();
		

		for(SacolaDTO item:lista){
			//CONDIÇÃO1 PRODUTO SELECIONADO
			if(item.getStatus() == 0 ){
			if(item.getCondicao().contains(produtoSelecionado)) {
				//Desconto Simples
				if(item.getAcao().contains(descontoProduto)) {	
					res.add(acoes.descontoProduto(item));
				}
				//ganhe com produtoSelecionado
				if(item.getAcao().contains(ganhe)){
					res.add(acoes.ganhe(item));
				}

			}
				//Progressivo e Brinde
				//CONDIÇÃO2 PRODUTOQUANTIDADE >
			if(item.getCondicao().contains(quantidadeMaior)) {
				String condicao = item.getCondicao();
				//Progressivo
				if(item.getAcao().contains(descontoProduto) && (item.getQuantidade() > util.converterInteger(condicao))) {
					res.add(acoes.descontoProduto(item));		
			}
				//Brinde
				if(item.getAcao().contains(ganhe) && (item.getQuantidade() > util.converterInteger(condicao))){
					res.add(acoes.ganhe(item));	
			}
			
			}
				//OPERADOR PRODUTOQUANTIDADE <
				if(item.getCondicao().contains(quantidadeMenor)) {
					String condicao = item.getCondicao();
					//Progressivo
					if(item.getAcao().contains(descontoProduto) && (item.getQuantidade() < util.converterInteger(condicao))) {
						res.add(acoes.descontoProduto(item));			
				}
					//Brinde
					if(item.getAcao().contains(ganhe) && (item.getQuantidade() < util.converterInteger(condicao))){
						res.add(acoes.ganhe(item));
				}
					
				}			

				//OPERADOR PRODUTOQUANTIDADE =
				if(item.getCondicao().contains(quantidadeIgual)) {
					String condicao = item.getCondicao();
					//Progressivo
					if(item.getAcao().contains(descontoProduto) && (item.getQuantidade() == util.converterInteger(condicao))) {
						res.add(acoes.descontoProduto(item));
				}
					//Brinde
					if(item.getAcao().contains(ganhe) && (item.getQuantidade() == util.converterInteger(condicao))){
						res.add(acoes.ganhe(item));
				}
					
				}	}		
		}
		return res;
	}
}