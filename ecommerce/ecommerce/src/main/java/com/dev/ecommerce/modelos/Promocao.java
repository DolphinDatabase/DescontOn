package com.dev.ecommerce.modelos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "promocao")
public class Promocao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROMO_SEQ")
    @SequenceGenerator(sequenceName = "promocao_seq", allocationSize = 1, name = "PROMO_SEQ")
	@Column(name="id")
	private Long id;
	
	@Column(name="promo_nome")
    private String nome;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "promo_data_cadastro")
	private Date dataCadastro = new Date();

	@Column(name="promo_condicao")
    private String condicao;

	@Column(name="promo_acao")
	private String acao;

	@Column(name="promo_status")
	private Integer status=0;


}
