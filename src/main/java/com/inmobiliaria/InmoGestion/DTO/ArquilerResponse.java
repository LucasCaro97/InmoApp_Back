package com.inmobiliaria.InmoGestion.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ArquilerResponse {

    private boolean success;
    private List<ArquilerDetalleDTO> data;

}
