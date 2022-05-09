package com.dev.ecommerce.dto;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SacolaDTO {

	private Long id;
    private String nome;
	private Double valor;
	private String categoria;
	private Integer status;
    private Integer quantidade;
	private String condicao;
	private String acao;
	
}