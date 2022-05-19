package com.dev.ecommerce.utils;

import org.springframework.beans.factory.annotation.Autowired;
import com.dev.ecommerce.dto.DescontoDTO;
import com.dev.ecommerce.dto.SacolaDTO;
import com.dev.ecommerce.repositorios.ItensPromocaoRepositorio;

public class Acao {
	
	@Autowired
	ItensPromocaoRepositorio itensPromocaoRepositorio;
	
	Util util = new Util();
	
	public DescontoDTO descontoProduto(SacolaDTO item) {
		String acao = item.getAcao();					
		Double totalPorItem= item.getQuantidade() * item.getValor();
		DescontoDTO desconto = new DescontoDTO(null,null);
		desconto.setId(item.getId());
		desconto.setDesconto(totalPorItem * (util.converterDouble(acao)/100));
		return desconto;
	}
	
	public DescontoDTO ganhe(SacolaDTO item) {
		String acao = item.getAcao();
		DescontoDTO desconto = new DescontoDTO(null, null);
		desconto.setId(item.getId());
		desconto.setDesconto(item.getValor() * util.converterInteger(acao));
		return desconto;
	}
	
	
	
}
