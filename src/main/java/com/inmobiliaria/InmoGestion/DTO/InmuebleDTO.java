package com.inmobiliaria.InmoGestion.DTO;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
@ToString
public class InmuebleDTO {

    private String nombre;
    private String direccion;
    private String ciudad;
    private String provincia;
    private String descripcion;
    private Long categoria;
    private Long[] ambientes;
    private Long[] servicios;
    private Long[] caracteristicas;
    private MultipartFile[] imagenes;
    private Boolean esAlquiler;
    private Boolean esVenta;
    private BigDecimal precioAlquiler;
    private BigDecimal precioVenta;
    private Long estadoInmueble;
    private Long propietario;

}
