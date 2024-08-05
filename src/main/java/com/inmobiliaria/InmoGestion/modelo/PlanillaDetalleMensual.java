package com.inmobiliaria.InmoGestion.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PlanillaDetalleMensual {

        @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "fk_maestro")
        @JsonBackReference
        private PlanillaMaestroMensual planillaMaestro;
        @ManyToOne
        @JoinColumn(name = "fk_contrato")
        private Contrato contrato;
        private BigDecimal importeAlquiler; // Revisar calculos a realizar con calculadora ARQUILER
        private BigDecimal expensas;
        private BigDecimal honorarios;
        private BigDecimal montoARendir;
        private LocalDate fechaCobro;
        private LocalDate fechaRendicion;
        private String observacion;



}
