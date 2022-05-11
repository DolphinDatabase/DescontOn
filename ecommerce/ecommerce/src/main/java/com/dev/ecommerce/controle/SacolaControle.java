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

		//valor da compra - falta menor valor
		String valorTotal = "[ValorTotal >]";
		String descontoTotal = "[DescontoTotal]";

		//operadores novos - faltam do valorTotal
		String quantidadeMenor = "[ProdutoQuantidade <]";
		String quantidadeIgual = "[ProdutoQuantidade =]";



		List<DescontoDTO> res = new ArrayList<>();
		Util util = new Util();
		Compra compra = new Compra();

		for(SacolaDTO item:lista){
			//CONDIÇÃO1 PRODUTO SELECIONADO
			if(item.getCondicao().contains(produtoSelecionado)) {
				//Desconto Simples
				if(item.getAcao().contains(descontoProduto)) {
					String acao = item.getAcao();					
					Double totalPorItem= item.getQuantidade() * item.getValor();
					DescontoDTO desconto = new DescontoDTO(null,null);
					desconto.setId(item.getId());
					desconto.setDesconto(totalPorItem * (util.converterDouble(acao)/100));
					res.add(desconto);
				}
				//ganhe com produtoSelecionado
				if(item.getAcao().contains(ganhe)){
					String acao = item.getAcao();
					DescontoDTO desconto = new DescontoDTO(null,null);
					desconto.setId(item.getId());
					desconto.setDesconto(item.getValor() * util.converterInteger(acao));
					res.add(desconto);
					
				}

				//DescontoTotal

				//DescontoMenorValor
			}


				//Progressivo e Brinde
				//CONDIÇÃO2 PRODUTOQUANTIDADE >
			if(item.getCondicao().contains(quantidadeMaior)) {
				String condicao = item.getCondicao();
				//Progressivo
				if(item.getAcao().contains(descontoProduto) && (item.getQuantidade() > util.converterInteger(condicao))) {
					String acao = item.getAcao();					
					Double totalPorItem= item.getQuantidade() * item.getValor();
					DescontoDTO desconto = new DescontoDTO(null,null);
					desconto.setId(item.getId());
					desconto.setDesconto(totalPorItem * (util.converterDouble(acao)/100));
					res.add(desconto);
						
			}
				//Brinde
				if(item.getAcao().contains(ganhe) && (item.getQuantidade() > util.converterInteger(condicao))){
				String acao = item.getAcao();
				DescontoDTO desconto = new DescontoDTO(null, null);
				desconto.setId(item.getId());
				desconto.setDesconto(item.getValor() * util.converterInteger(acao));
				res.add(desconto);
				
			}
				
			}
				//OPERADOR PRODUTOQUANTIDADE <
				if(item.getCondicao().contains(quantidadeMenor)) {
					String condicao = item.getCondicao();
					//Progressivo
					if(item.getAcao().contains(descontoProduto) && (item.getQuantidade() < util.converterInteger(condicao))) {
						String acao = item.getAcao();					
						Double totalPorItem= item.getQuantidade() * item.getValor();
						DescontoDTO desconto = new DescontoDTO(null, null);
						desconto.setId(item.getId());
						desconto.setDesconto(totalPorItem * (util.converterDouble(acao)/100));
						res.add(desconto);
							
				}
					//Brinde
					if(item.getAcao().contains(ganhe) && (item.getQuantidade() < util.converterInteger(condicao))){
					String acao = item.getAcao();
					DescontoDTO desconto = new DescontoDTO(null,null);
					desconto.setId(item.getId());
					desconto.setDesconto(item.getValor() * util.converterInteger(acao));
					res.add(desconto);
					
				}
					
				}			

				//OPERADOR PRODUTOQUANTIDADE =
				if(item.getCondicao().contains(quantidadeIgual)) {
					String condicao = item.getCondicao();
					//Progressivo
					if(item.getAcao().contains(descontoProduto) && (item.getQuantidade() == util.converterInteger(condicao))) {
						String acao = item.getAcao();					
						Double totalPorItem= item.getQuantidade() * item.getValor();
						DescontoDTO desconto = new DescontoDTO(null, null);
						desconto.setId(item.getId());
						desconto.setDesconto(totalPorItem * (util.converterDouble(acao)/100));
						res.add(desconto);
							
				}
					//Brinde
					if(item.getAcao().contains(ganhe) && (item.getQuantidade() == util.converterInteger(condicao))){
					String acao = item.getAcao();
					DescontoDTO desconto = new DescontoDTO(null, null);
					desconto.setId(item.getId());
					desconto.setDesconto(item.getValor() * util.converterInteger(acao));
					res.add(desconto);
					
				}
					
				}			

			//VALOR DA COMPRA teste
			//CONDIÇÃO3 VALORTOTAL >
			if(item.getCondicao().contains(valorTotal)){
				String condicao = item.getCondicao();					
		
				if(item.getAcao().contains(descontoTotal) && (compra.getValorTotal() > util.converterDouble(condicao))){
					String acao = item.getAcao();
					Double valorCompra =  compra.getValorTotal();
					DescontoDTO desconto = new DescontoDTO(null, null);
					desconto.setId(item.getId());
					desconto.setDesconto(valorCompra * ( util.converterInteger(acao)/100));
					res.add(desconto);
				}
			}
		}
		return res;
	}
}