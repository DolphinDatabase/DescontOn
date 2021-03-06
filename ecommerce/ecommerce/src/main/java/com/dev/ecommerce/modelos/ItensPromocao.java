package com.dev.ecommerce.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="itens_promocao")
public class ItensPromocao {
    
    @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITE_PROMO_SEQ")
    @SequenceGenerator(sequenceName = "itenspromocao_seq", allocationSize = 1, name = "ITE_PROMO_SEQ")
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "produto_id")
	private Produto produto;

	@ManyToOne
	@JoinColumn(name = "promocao_id")
	private Promocao promocao;    

}
