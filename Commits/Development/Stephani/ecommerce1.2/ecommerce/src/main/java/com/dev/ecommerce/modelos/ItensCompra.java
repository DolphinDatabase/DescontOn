package com.dev.ecommerce.modelos;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "itens_compra")
public class ItensCompra implements Serializable{
	
	public ItensCompra() {
		super();
	}
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long iteId;

	@ManyToOne
	private Produto produto;

	@ManyToOne
	private Compra compra;

	private Integer iteQuantidade;

	private Double iteValorUnitario=0.;
	
	private Double iteValorTotal=0.;

	public Long getIteId() {
		return iteId;
	}

	public void setIteId(Long iteId) {
		this.iteId = iteId;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public Integer getIteQuantidade() {
		return iteQuantidade;
	}

	public void setIteQuantidade(Integer iteQuantidade) {
		this.iteQuantidade = iteQuantidade;
	}

	public Double getIteValorUnitario() {
		return iteValorUnitario;
	}

	public void setIteValorUnitario(Double iteValorUnitario) {
		this.iteValorUnitario = iteValorUnitario;
	}

	public Double getIteValorTotal() {
		return iteValorTotal;
	}

	public void setIteValorTotal(Double iteValorTotal) {
		this.iteValorTotal = iteValorTotal;
	}
	
}
