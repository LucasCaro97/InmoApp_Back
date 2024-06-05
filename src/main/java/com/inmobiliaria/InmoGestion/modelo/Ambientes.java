package com.inmobiliaria.InmoGestion.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Ambientes {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;    //    2 dormitorios - 1 baño - cocina - comedor - living - lavadero - garage - piscina - patio - 2 depositos - canil para perros

}
