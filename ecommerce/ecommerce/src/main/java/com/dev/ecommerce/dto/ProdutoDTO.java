package com.dev.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class ProdutoDTO {
	
	private Long id;
	private String nome;
	private Double valor;
	private String categoria;
	private Integer status;
}
