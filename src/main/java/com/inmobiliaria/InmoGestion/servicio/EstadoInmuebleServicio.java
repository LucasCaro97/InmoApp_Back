package com.inmobiliaria.InmoGestion.servicio;

import com.inmobiliaria.InmoGestion.modelo.EstadoInmueble;
import com.inmobiliaria.InmoGestion.repositorio.EstadoInmuebleRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadoInmuebleServicio {

    private final EstadoInmuebleRepositorio estadoInmuebleRepositorio;

    @Transactional(readOnly = true)
    public EstadoInmueble obtenerPorId(Long id){
        return estadoInmuebleRepositorio.findById(id).orElseGet(null);
    }

    @Transactional(readOnly = true)
    public List<EstadoInmueble> obtenerTodos(){
        return estadoInmuebleRepositorio.findAll();
    }

    @Transactional
    public EstadoInmueble crear(EstadoInmueble estadoInmuebleDTO){
        try{
            EstadoInmueble estadoInmueble = new EstadoInmueble();
            estadoInmueble.setNombre(estadoInmuebleDTO.getNombre());
            return estadoInmuebleRepositorio.save(estadoInmueble);
        }catch (Exception e){
            throw  new RuntimeException("Error al crear el registro");
        }
    }

    @Transactional
    public EstadoInmueble actualizar(Long id, EstadoInmueble estadoInmuebleDTO){
        try{
            EstadoInmueble estadoInmueble = this.obtenerPorId(id);
            estadoInmueble.setNombre(estadoInmuebleDTO.getNombre());
            return estadoInmuebleRepositorio.save(estadoInmueble);
        }catch (Exception e){
            throw  new RuntimeException("Error al actualizar el registro");
        }
    }

    @Transactional
    public HashMap<String, String> eliminarPorId(Long id){
        try{
            HashMap<String, String> respuesta = new HashMap<>();
            respuesta.put("mensaje", "Registro eliminado correctamente");
            estadoInmuebleRepositorio.deleteById(id);
            return respuesta;
        }catch (Exception e){
            throw  new RuntimeException("Error al eliminar el registro");
        }
    }

}
