package com.inmobiliaria.InmoGestion.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ArquilerDetalleDTO {

    private String date;
    private BigDecimal value;
    private boolean estimated;
    private BigDecimal dif;
    private BigDecimal amount;
    private List<DetailsDTO> details;


}
