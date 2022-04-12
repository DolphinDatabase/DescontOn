package com.dev.ecommerce.modelos;

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
	private Long proId;
	
	public Produto() {
		super();
	}

	private String proNome;
	private Double proValor;
	private String proCategoria;
	private Integer proStatus=0;
	
}