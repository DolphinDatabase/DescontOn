package com.dev.ecommerce.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class PromocaoDTO {
	
	private Long id;
	private String nome;
	private Date dataCadastro;
	private String condicao;
	private String acao;
	private Integer status;
}
