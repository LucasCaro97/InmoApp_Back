package com.inmobiliaria.InmoGestion.repositorio;

import com.inmobiliaria.InmoGestion.modelo.PlanillaDetalleMensual;
import com.inmobiliaria.InmoGestion.modelo.PlanillaMaestroMensual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanillaDetalleMensualRepositorio extends JpaRepository<PlanillaDetalleMensual, Long> {


    List<PlanillaDetalleMensual> findByPlanillaMaestro(PlanillaMaestroMensual planillaMestro);
}
