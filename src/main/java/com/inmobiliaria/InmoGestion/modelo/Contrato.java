package com.inmobiliaria.InmoGestion.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Contrato {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "fk_tipo_contrato")
    private TipoContrato tipoContrato;
    @ManyToOne
    @JoinColumn(name = "fk_propietario")
    private Propietario propietario;
    @ManyToOne
    @JoinColumn(name = "fk_inquilino")
    private Inquilino inquilino;
    @ManyToOne
    @JoinColumn(name = "fk_inmueble")
    private Inmueble inmueble;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String observaciones;



}
