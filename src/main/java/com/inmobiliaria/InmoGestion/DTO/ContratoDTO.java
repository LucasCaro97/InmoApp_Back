package com.inmobiliaria.InmoGestion.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContratoDTO {

    private Long inmuebleId;
    private Long inquilinoId;
    private Long tipoContratoId;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String observaciones;
    private Long estadoContrato;
    private BigDecimal importeBase;
    private Long indice;
    private Integer actualizaCada;

}
