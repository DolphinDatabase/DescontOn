package com.dev.ecommerce.modelos;

import java.util.Date;

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
    @SequenceGenerator(sequenceName = "progressivo_seq", allocationSize = 1, name = "PROMO_SEQ")
	private Long promocaoId;
	
    private String promoNome;

	@Temporal(TemporalType.TIMESTAMP)
	private Date promoDataCadastro = new Date();

    private String promoMecanica;


}
