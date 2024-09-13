package com.inmobiliaria.InmoGestion.repositorio;

import com.inmobiliaria.InmoGestion.modelo.TipoContrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoContratoRepositorio extends JpaRepository<TipoContrato, Long> {


}
