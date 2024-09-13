package com.inmobiliaria.InmoGestion.modelo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "planilla_maestro_mensual", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"mes", "anio"})
})
public class PlanillaMaestroMensual {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer mes;
    private Integer anio;

    @OneToMany(mappedBy = "planillaMaestro", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<PlanillaDetalleMensual> detalles;


}
