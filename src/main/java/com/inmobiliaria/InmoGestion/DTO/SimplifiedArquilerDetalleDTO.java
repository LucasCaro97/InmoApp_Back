package com.inmobiliaria.InmoGestion.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class SimplifiedArquilerDetalleDTO {

    private String date;
    private BigDecimal amount;

}
