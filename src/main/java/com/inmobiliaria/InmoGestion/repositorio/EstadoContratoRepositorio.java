package com.inmobiliaria.InmoGestion.repositorio;

import com.inmobiliaria.InmoGestion.modelo.EstadoContrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoContratoRepositorio extends JpaRepository<EstadoContrato, Long> {


    EstadoContrato findByNombre(String enTramite);

}
