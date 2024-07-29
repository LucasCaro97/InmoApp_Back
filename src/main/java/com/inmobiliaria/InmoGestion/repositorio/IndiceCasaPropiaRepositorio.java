package com.inmobiliaria.InmoGestion.repositorio;

import com.inmobiliaria.InmoGestion.modelo.IndiceCasaPropia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndiceCasaPropiaRepositorio extends JpaRepository<IndiceCasaPropia, Long> {



}
