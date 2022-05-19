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
import com.dev.ecommerce.modelos.Produto;
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
	public Compra salvar(@RequestBody Compra compra){
		return compraRepositorio.save(compra);
	}

	@PostMapping("/desconto")
	public List<DescontoDTO> buscarPromocao(@RequestBody List<SacolaDTO> itensCompra) {
		List<SacolaDTO> lista = new ArrayList<>();
		itensCompra.forEach(item->{
			List<ItensPromocao> find = itensPromocaoRepositorio.findAllByProduto(item.getId());
			List<Produto> encontrar = produtoRepositorio.findAllByProduto(item.getId());
			if(!find.isEmpty()){
				item.setAcao(find.get(0).getPromocao().getAcao());
				item.setCondicao(find.get(0).getPromocao().getCondicao());
				item.setStatus(find.get(0).getPromocao().getStatus());
				item.setCategoria(encontrar.get(0).getCategoria());
				item.setValor(encontrar.get(0).getValor());
				lista.add(item);
			}
		});	

		//acoes
		String ganhe = "[Ganhe]";
		String descontoProduto = "[DescontoProduto]";
		//condicoes
		String produtoSelecionado = "[ProdutoSelecionado]";
		String categoria = "[ProdutoCategoria]";
		String quantidadeMaior = "[ProdutoQuantidade >]";
		String quantidadeMenor = "[ProdutoQuantidade <]";
		String quantidadeIgual = "[ProdutoQuantidade =]";
		String produtoValorMaior = "[ProdutoValor >]";
		String produtoValorMenor = "[ProdutoValor <]";
		String produtoValorIgual = "[ProdutoValor =]";

		List<DescontoDTO> res = new ArrayList<>();
		Util util = new Util();
		Acao acoes = new Acao();
		

		for(SacolaDTO item:lista){
			//PRODUTO SELECIONADO
			if(item.getStatus() == 0 ){
			if(item.getCondicao().contains(produtoSelecionado)) {
				if(item.getAcao().contains(descontoProduto)) {	
					res.add(acoes.descontoProduto(item));
				}
				if(item.getAcao().contains(ganhe)){
					res.add(acoes.ganhe(item));
				}

			}

			//VALOR PRODUTO >
			if(item.getCondicao().contains(produtoValorMaior)) {
				String condicao = item.getCondicao();

				if(item.getAcao().contains(descontoProduto) && (item.getValor() > util.converterDouble(condicao))) {	
					res.add(acoes.descontoProduto(item));
				}
				if(item.getAcao().contains(ganhe) && (item.getValor() > util.converterDouble(condicao))){
					res.add(acoes.ganhe(item));
				}

			}
			
			//VALOR PRODUTO < 
			if(item.getCondicao().contains(produtoValorMenor)) {
				String condicao = item.getCondicao();

				if(item.getAcao().contains(descontoProduto) && (item.getValor() < util.converterDouble(condicao))) {	
					res.add(acoes.descontoProduto(item));
				}
				if(item.getAcao().contains(ganhe) && (item.getValor() < util.converterDouble(condicao))){
					res.add(acoes.ganhe(item));
				}

			}			

			//VALOR PRODUTO =
			if(item.getCondicao().contains(produtoValorIgual)) {
				String condicao = item.getCondicao();
				if(item.getAcao().contains(descontoProduto) && (item.getValor().equals(util.converterDouble(condicao)))) {
					res.add(acoes.descontoProduto(item));
				}
				if(item.getAcao().contains(ganhe) && (item.getValor().equals(util.converterDouble(condicao)))) {
					res.add(acoes.ganhe(item));
				}

			}	
			
			//CATEGORIA
			if(item.getCondicao().contains(categoria)) {

				if(item.getCategoria().contains("Cosmeticos") && (item.getCondicao().contains("1"))){
				if(item.getAcao().contains(descontoProduto)) {	
					res.add(acoes.descontoProduto(item));
				}
				if(item.getAcao().contains(ganhe)){
					res.add(acoes.ganhe(item));
				}
					
				}

				if(item.getCategoria().contains("Perfumaria") && (item.getCondicao().contains("2"))){
					if(item.getAcao().contains(descontoProduto)) {	
						res.add(acoes.descontoProduto(item));
					}
					if(item.getAcao().contains(ganhe)){
						res.add(acoes.ganhe(item));
					}
						
					}

					if(item.getCategoria().contains("Saude") && (item.getCondicao().contains("3"))){
						if(item.getAcao().contains(descontoProduto)) {	
							res.add(acoes.descontoProduto(item));
						}
						if(item.getAcao().contains(ganhe)){
							res.add(acoes.ganhe(item));
						}
							
						}
			}
			//PRODUTOQUANTIDADE >
			if(item.getCondicao().contains(quantidadeMaior)) {
				String condicao = item.getCondicao();
				if(item.getAcao().contains(descontoProduto) && (item.getQuantidade() > util.converterInteger(condicao))) {
					res.add(acoes.descontoProduto(item));		
			}
				if(item.getAcao().contains(ganhe) && (item.getQuantidade() > util.converterInteger(condicao))){
					res.add(acoes.ganhe(item));	
			}
			
			}
				//OPERADOR PRODUTOQUANTIDADE <
				if(item.getCondicao().contains(quantidadeMenor)) {
					String condicao = item.getCondicao();
					if(item.getAcao().contains(descontoProduto) && (item.getQuantidade() < util.converterInteger(condicao))) {
						res.add(acoes.descontoProduto(item));			
				}
					if(item.getAcao().contains(ganhe) && (item.getQuantidade() < util.converterInteger(condicao))){
						res.add(acoes.ganhe(item));
				}
					
				}			

				//OPERADOR PRODUTOQUANTIDADE =
				if(item.getCondicao().contains(quantidadeIgual)) {
					String condicao = item.getCondicao();
					if(item.getAcao().contains(descontoProduto) && (item.getQuantidade() == util.converterInteger(condicao))) {
						res.add(acoes.descontoProduto(item));
				}
					if(item.getAcao().contains(ganhe) && (item.getQuantidade() == util.converterInteger(condicao))){
						res.add(acoes.ganhe(item));
				}
					
				}	}		
		}
		return res;
	}
}