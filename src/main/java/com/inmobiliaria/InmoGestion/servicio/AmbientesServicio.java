package com.inmobiliaria.InmoGestion.servicio;

import com.inmobiliaria.InmoGestion.modelo.Ambientes;
import com.inmobiliaria.InmoGestion.modelo.Servicios;
import com.inmobiliaria.InmoGestion.repositorio.AmbientesRepositorio;
import com.inmobiliaria.InmoGestion.repositorio.ServiciosRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AmbientesServicio {

    public final AmbientesRepositorio ambientesRepositorio;

    @Transactional
    public Ambientes crear (Ambientes dto){
        try{
            return ambientesRepositorio.save(dto);
        }catch (Exception e){
            throw new RuntimeException("Error al crear el registro");
        }
    }


    @Transactional
    public Ambientes actualizar (Long id, Ambientes dto){
        try{
            Ambientes a = ambientesRepositorio.findById(id).orElse(null);
            if (a != null){
                a.setNombre(dto.getNombre());
                return ambientesRepositorio.save(a);
            }else{
                throw new RuntimeException("No se ha encontrado el registro");
            }
        }catch (Exception e){
            throw new RuntimeException("Error al actualizar el registro");
        }
    }

    @Transactional(readOnly = true)
    public Optional<Ambientes> obtenerPorId(Long id){
        return ambientesRepositorio.findById(id);
    }


    @Transactional(readOnly = true)
    public List<Ambientes> obtenerTodos(){
        return ambientesRepositorio.findAll();
    }

    @Transactional
    public HashMap<String, String> eliminarPorId(Long id){
        try {
            HashMap<String, String> respuesta = new HashMap<>();
            respuesta.put("mensaje", "Se ha eliminardo correctamente el registro");
            ambientesRepositorio.deleteById(id);
            return  respuesta;
        }catch (Exception e){
            throw new RuntimeException("Error al eliminar el registro");
        }
    }
}
