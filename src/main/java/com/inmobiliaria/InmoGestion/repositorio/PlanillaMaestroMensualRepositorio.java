package com.inmobiliaria.InmoGestion.repositorio;

import com.inmobiliaria.InmoGestion.modelo.PlanillaMaestroMensual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanillaMaestroMensualRepositorio extends JpaRepository<PlanillaMaestroMensual, Long> {


}
