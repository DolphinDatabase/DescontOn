package com.dev.ecommerce.modelos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "itens_promocao")
public class ItensPromocao {

	public ItensPromocao() {
		super();
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITE_PROMO_SEQ")
    @SequenceGenerator(sequenceName = "itenspromocao_seq", allocationSize = 1, name = "ITE_PROMO_SEQ")
	private Long itePromoId;

	@ManyToOne
	private Produto produto;
	
	@ManyToOne
	private Promocao promocao;


}