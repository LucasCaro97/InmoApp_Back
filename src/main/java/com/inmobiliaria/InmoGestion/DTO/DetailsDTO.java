package com.inmobiliaria.InmoGestion.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DetailsDTO {

    private String date;
    private BigDecimal value;
    private BigDecimal month_before;
    private BigDecimal accumulate;

}

