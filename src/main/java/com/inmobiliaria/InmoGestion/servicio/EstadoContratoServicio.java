package com.inmobiliaria.InmoGestion.servicio;

import com.inmobiliaria.InmoGestion.modelo.EstadoContrato;
import com.inmobiliaria.InmoGestion.repositorio.EstadoContratoRepositorio;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadoContratoServicio {

    private final EstadoContratoRepositorio estadoContratoRepositorio;


    @Transactional(readOnly = true)
    public EstadoContrato obtenerPorId(Long id){
        return estadoContratoRepositorio.findById(id).orElseGet(null);
    }

    @Transactional(readOnly = true)
    public List<EstadoContrato> obtenerTodos(){
        return estadoContratoRepositorio.findAll();
    }

    @Transactional
    public EstadoContrato crearEstado(EstadoContrato estadoContratoDTO){
        EstadoContrato estadoContrato = new EstadoContrato();
        estadoContrato.setNombre(estadoContratoDTO.getNombre());
        return estadoContratoRepositorio.save(estadoContrato);
    }

    @Transactional()
    public EstadoContrato actualizarEstado(Long id, EstadoContrato estadoContratoDTO){
        EstadoContrato estadoContrato = this.obtenerPorId(id);
        estadoContrato.setNombre(estadoContratoDTO.getNombre());
        return estadoContratoRepositorio.save(estadoContrato);
    }

    @Transactional
    public void eliminarEstado(Long id){
        /*
        * Validar la eliminacion de cada estado ---> Si existen cotratos con dicho estado que no se pueda
        * */
        estadoContratoRepositorio.deleteById(id);
    }


}
