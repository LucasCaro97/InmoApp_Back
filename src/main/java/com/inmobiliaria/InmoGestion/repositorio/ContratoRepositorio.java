package com.inmobiliaria.InmoGestion.repositorio;

import com.inmobiliaria.InmoGestion.modelo.Contrato;
import com.inmobiliaria.InmoGestion.modelo.EstadoContrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContratoRepositorio extends JpaRepository<Contrato, Long> {


    @Query(value = "SELECT * FROM contrato  WHERE fk_estado_contrato = ?", nativeQuery = true)
    List<Contrato> findByEstadoEnTramite(Long id);


}
