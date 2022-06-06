package com.dev.ecommerce.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AplicarDescontoDTO {
    
    private List<SacolaDTO> sacola;
    private Double total;
    private String condicao;
    private String acao;

}
