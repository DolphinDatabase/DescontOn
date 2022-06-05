package com.dev.ecommerce.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.dev.ecommerce.dto.DescontoDTO;
import com.dev.ecommerce.dto.SacolaDTO;
import com.dev.ecommerce.repositorios.ItensPromocaoRepositorio;

public class Acao {
	
	@Autowired
	ItensPromocaoRepositorio itensPromocaoRepositorio;
	
	Util util = new Util();
	
	public DescontoDTO descontoProduto(SacolaDTO item,Double total,String acao) {
		Double totalPorItem;
		if(acao.contains("ValorTotal")){
			totalPorItem = total;
		}else{
			totalPorItem= item.getQuantidade() * item.getValor();
		}
		DescontoDTO desconto = new DescontoDTO(null,null);
		desconto.setId(item.getId());
		desconto.setDesconto(totalPorItem * (util.converterDouble(acao)/100));
		return desconto;
	}
	
	public DescontoDTO ganhe(SacolaDTO item,String acao) {
		DescontoDTO desconto = new DescontoDTO(null, null);
		desconto.setId(item.getId());
		desconto.setDesconto(item.getValor() * util.converterInteger(acao));
		return desconto;
	}
	
	public List<DescontoDTO> menorValor(List<SacolaDTO> sacola, Double total ,String acao){
		SacolaDTO menor = sacola.get(0); 
		Double totalPorItem;
		for(SacolaDTO item:sacola){
			if((item.getValor()*item.getQuantidade()) < (menor.getValor()*menor.getQuantidade())){
				menor = item;
			}
		}
		if(acao.contains("ValorTotal")){
			totalPorItem = total;
		}else{
			totalPorItem= menor.getQuantidade() * menor.getValor();
		}
		DescontoDTO desconto = new DescontoDTO(menor.getId(),totalPorItem * (util.converterDouble(acao)/100));
		List<DescontoDTO> res = new ArrayList<>();
		res.add(desconto);
		return res;
	}
	
}
