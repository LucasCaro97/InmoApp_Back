package com.inmobiliaria.InmoGestion.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Month;
import java.time.Year;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PlanillaMaestroMensual {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Month mes;
    private Integer anio;

    @OneToMany(mappedBy = "planillaMaestro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlanillaDetalleMensual> detalles;


}
