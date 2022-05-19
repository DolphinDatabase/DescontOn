package com.dev.ecommerce.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

@Entity
@Table(name = "produto")
public class Produto  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRO_SEQ")
	@SequenceGenerator(sequenceName = "produto_seq", allocationSize = 1, name = "PRO_SEQ")
	@Column(name = "id")
	private Long id;

	@Column(name = "pro_nome")
	private String nome;

	@Column(name = "pro_valor")
	private Double valor;

	@Column(name = "pro_categoria")
	private String categoria;
	
	@Column(name = "pro_status")
	private Integer status=0;
	
}