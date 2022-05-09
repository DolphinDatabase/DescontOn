package com.dev.ecommerce.dto;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DescontoDTO {
    
    private Long id;
    private Double desconto;

}
