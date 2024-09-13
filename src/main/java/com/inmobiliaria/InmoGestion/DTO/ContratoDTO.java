package com.inmobiliaria.InmoGestion.DTO;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ContratoDTO {

    private Long inmuebleId;
    private Long inquilinoId;
    private Long tipoContratoId;
    private String fechaInicio;
    private String fechaFin;
    private String observaciones;
    private Long estadoContrato;
    private BigDecimal importeBase;
    private Long indice;
    private Integer actualizaCada;

}
