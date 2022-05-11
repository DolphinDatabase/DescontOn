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

@Setter
@Getter
@Entity
@Table(name = "itens_compra")
public class ItensCompra {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITE_SEQ")
    @SequenceGenerator(sequenceName = "itenscompra_seq", allocationSize = 1, name = "ITE_SEQ")
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "produto_id")
	private Produto produto;

	@ManyToOne
	@JoinColumn(name = "compra_id")
	private Compra compra;

	@Column(name = "com_quantidade")
	private Integer quantidade;

	@Column(name = "com_valor_unitario")
	private Double valorUnitario=0.;

	@Column(name = "com_valor_total")
	private Double valorTotal=0.;

}
