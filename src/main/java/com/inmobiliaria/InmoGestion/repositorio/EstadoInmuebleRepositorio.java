package com.inmobiliaria.InmoGestion.repositorio;

import com.inmobiliaria.InmoGestion.modelo.EstadoInmueble;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoInmuebleRepositorio extends JpaRepository<EstadoInmueble, Long> {


    EstadoInmueble findByNombre(String nombre);

}
