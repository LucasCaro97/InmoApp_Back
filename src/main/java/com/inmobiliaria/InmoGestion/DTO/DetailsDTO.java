package com.inmobiliaria.InmoGestion.DTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DetailsDTO {

    private String date;
    private BigDecimal value;
    private BigDecimal month_before;
    private BigDecimal accumulate;

}

