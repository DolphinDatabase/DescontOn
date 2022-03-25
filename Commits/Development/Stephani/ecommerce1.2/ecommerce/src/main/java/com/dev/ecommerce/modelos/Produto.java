package com.dev.ecommerce.modelos;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "produto")
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long proId;
	
	public Produto() {
		super();
	}

	private String proNome;
	//private int proId;
	private Double proValor;
	private String proCategoria;
	
	public String getProNome() {
		return proNome;
		
	}
	public void setProNome(String proNome) {
		this.proNome = proNome;
	}
	public Long getProId() {
		return proId;
	}
	public void setProId(Long proId) {
		this.proId = proId;
	}
	public Double getProValor() {
		return proValor;
	}
	public void setProValor(Double proValor) {
		this.proValor = proValor;
	}
	public String getProCategoria() {
		return proCategoria;
	}
	public void setProCategoria(String proCategoria) {
		this.proCategoria = proCategoria;
	}



}