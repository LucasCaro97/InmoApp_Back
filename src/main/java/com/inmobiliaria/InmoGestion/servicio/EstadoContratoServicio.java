package com.inmobiliaria.InmoGestion.servicio;

import com.inmobiliaria.InmoGestion.modelo.EstadoContrato;
import com.inmobiliaria.InmoGestion.repositorio.EstadoContratoRepositorio;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadoContratoServicio {

    private final EstadoContratoRepositorio estadoContratoRepositorio;

    /*
    *   ORDEN DE GENERACION DE ESTADOS ( 1 EN TRAMITE , 2 EN CURSO, 3 RESCINDIDO )
    * */

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
        try{
            EstadoContrato estadoContrato = new EstadoContrato();
            estadoContrato.setNombre(estadoContratoDTO.getNombre());
            return estadoContratoRepositorio.save(estadoContrato);
        }catch (Exception e){
            throw  new RuntimeException("Error al crear el registro");
        }
    }

    @Transactional()
    public EstadoContrato actualizarEstado(Long id, EstadoContrato estadoContratoDTO){
        try{
            EstadoContrato estadoContrato = this.obtenerPorId(id);
            estadoContrato.setNombre(estadoContratoDTO.getNombre());
            return estadoContratoRepositorio.save(estadoContrato);
        }catch (Exception e) {
            throw new RuntimeException("Error al actualizar el registro");
        }
    }

    @Transactional
    public HashMap<String, String> eliminarEstado(Long id){
        try{
            HashMap<String, String> respuesta = new HashMap<>();
            respuesta.put("mensaje", "Registro eliminado correctamente");
            estadoContratoRepositorio.deleteById(id);
            return respuesta;
        }catch (Exception e){
            throw new RuntimeException("Error al eliminar el registro");
        }
    }

    @Transactional(readOnly = true)
    public EstadoContrato obtenerEstadoEnTramite(){
        try{
            return estadoContratoRepositorio.findByNombre("en tramite");
        }catch (Exception e){
            throw new RuntimeException("Error al buscar estado en tramite");
        }
    }


}
