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
@Table(name = "compra")
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COM_SEQ")
    @SequenceGenerator(sequenceName = "compra_seq", allocationSize = 1, name = "COM_SEQ")
	private Long comId;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date comDataCompra = new Date();
	private Double comValorTotal=0.;

}
