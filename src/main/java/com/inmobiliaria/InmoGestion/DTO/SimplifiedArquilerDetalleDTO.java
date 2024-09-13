package com.inmobiliaria.InmoGestion.DTO;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SimplifiedArquilerDetalleDTO {

    private String date;
    private BigDecimal amount;

}
