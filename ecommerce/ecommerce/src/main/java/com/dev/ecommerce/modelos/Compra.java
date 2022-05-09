package com.dev.ecommerce.modelos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

@Entity
@Table(name = "compra")
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id")
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_compra")
	private Date dataCompra = new Date();
	
	@Column(name = "valor_total")
	private Double valorTotal=0.0;

	@Column(name = "com_desconto")
	private Double desconto;
	
	//transformar DTO
	@Transient
	private Double subValorTotal;

}
