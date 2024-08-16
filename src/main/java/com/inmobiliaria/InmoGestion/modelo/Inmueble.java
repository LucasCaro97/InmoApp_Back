package com.inmobiliaria.InmoGestion.modelo;


import com.inmobiliaria.InmoGestion.servicio.AmbientesServicio;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Inmueble {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private String direccion;
    private String ciudad;
    private String provincia;

    @ManyToOne
    @JoinColumn(name = "fk_categoria")
    private Categoria categoria;                       // casa - departamento / terreno - lote - chacra

    @ManyToMany
    @JoinTable(
            name = "inmueble_servicios",
            joinColumns = @JoinColumn(name = "inmueble_id"),
            inverseJoinColumns = @JoinColumn(name = "servicios_id")
    )
    private List<Servicios> servicios;                 // Agua - Luz - Asfalto - Wifi - etc

    @ManyToMany
    @JoinTable(
            name = "inmueble_caracteristicas",
            joinColumns = @JoinColumn(name = "inmueble_id"),
            inverseJoinColumns = @JoinColumn(name = "caracteristicas_id")
    )
    private List<Caracteristicas> caracteristicas;           // 2 dormitorios - 1 baño - cocina - comedor - living - lavadero - garage - piscina - patio - 2 depositos - canil para perros

    @ManyToMany
    @JoinTable(
            name = "inmueble_ambientes",
            joinColumns = @JoinColumn(name = "inmueble_id"),
            inverseJoinColumns = @JoinColumn(name = "ambientes_id")
    )
    private List<Ambientes> ambientes;  // ---> SELECCION MULTIPLE []

    @ElementCollection
    private List<String>    listaImagenes;

    private Boolean esAlquiler;
    private Boolean esVenta;

    private BigDecimal precioAlquiler;
    private BigDecimal precioVenta;

    @ManyToOne
    @JoinColumn(name = "fk_propietario")
    private Propietario propietario;

    @ManyToOne
    @JoinColumn(name = "fk_estado_inmueble")
    private EstadoInmueble estadoInmueble;



}
