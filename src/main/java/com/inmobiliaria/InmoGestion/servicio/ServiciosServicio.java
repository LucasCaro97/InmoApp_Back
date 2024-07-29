package com.inmobiliaria.InmoGestion.servicio;

import com.inmobiliaria.InmoGestion.modelo.Servicios;
import com.inmobiliaria.InmoGestion.repositorio.ServiciosRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiciosServicio {

    public final ServiciosRepositorio serviciosRepositorio;

    @Transactional
    public Servicios crear (Servicios dto){
        try{
            return serviciosRepositorio.save(dto);
        }catch (Exception e){
            throw new RuntimeException("Error al crear el registro");
        }
    }


    @Transactional
    public Servicios actualizar (Long id, Servicios dto){
        try{
            Servicios s = serviciosRepositorio.findById(id).orElse(null);
            if (s != null){
                s.setNombre(dto.getNombre());
                return serviciosRepositorio.save(s);
            }else{
                throw new RuntimeException("No se ha encontrado el registro");
            }
        }catch (Exception e){
            throw new RuntimeException("Error al actualizar el registro");
        }
    }

    @Transactional(readOnly = true)
    public Optional<Servicios> obtenerPorId(Long id){
        return serviciosRepositorio.findById(id);
    }


    @Transactional(readOnly = true)
    public List<Servicios> obtenerTodos(){
        return serviciosRepositorio.findAll();
    }

    @Transactional
    public HashMap<String, String> eliminarPorId(Long id){
        try {
            HashMap<String, String> respuesta = new HashMap<>();
            respuesta.put("mensaje", "Se ha eliminado correctamente el registro");
            serviciosRepositorio.deleteById(id);
            return  respuesta;
        }catch (Exception e){
            throw new RuntimeException("Error al eliminar el registro");
        }
    }



}
