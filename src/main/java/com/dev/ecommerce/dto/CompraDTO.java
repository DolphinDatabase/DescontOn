package com.dev.ecommerce.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class CompraDTO {

	private Long id;
	private Date dataCompra;
	private Double valorTotal;
	private Double desconto;
	private Double subValorTotal;
	
}
