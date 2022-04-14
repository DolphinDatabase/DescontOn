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
@Table(name = "itens_compra")
public class ItensCompra {
	
	public ItensCompra() {
		super();
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITE_SEQ")
    @SequenceGenerator(sequenceName = "itenscompra_seq", allocationSize = 1, name = "ITE_SEQ")
	private Long iteId;

	@ManyToOne
	private Produto produto;

	@ManyToOne
	private Compra compra;

	private Integer iteQuantidade;
	private Double iteValorUnitario=0.;
	private Double iteValorTotal=0.;

}
