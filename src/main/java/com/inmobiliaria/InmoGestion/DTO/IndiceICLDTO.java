package com.inmobiliaria.InmoGestion.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IndiceICLDTO {

    private Integer variableId;
    private String startDate;
    private String endDate;
    private Double valor;

}
