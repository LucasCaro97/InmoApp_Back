package com.inmobiliaria.InmoGestion.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
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
    @ManyToOne
    @JoinColumn(name = "fk_estado_contrato")
    private EstadoContrato estadoContrato;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String observaciones;
    private BigDecimal importeBase;
    private Integer actualizaCada;
    @ManyToOne
    @JoinColumn(name = "fk_indice")
    private Indice indice;



}
