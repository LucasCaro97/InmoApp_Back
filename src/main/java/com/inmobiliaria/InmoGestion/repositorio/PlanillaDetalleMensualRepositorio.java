package com.inmobiliaria.InmoGestion.repositorio;

import com.inmobiliaria.InmoGestion.modelo.PlanillaDetalleMensual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanillaDetalleMensualRepositorio extends JpaRepository<PlanillaDetalleMensual, Long> {


}
