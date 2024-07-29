package com.inmobiliaria.InmoGestion.servicio;

import com.inmobiliaria.InmoGestion.modelo.Inquilino;
import com.inmobiliaria.InmoGestion.repositorio.InquilinoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InquilinoServicio {

    private final InquilinoRepositorio inquilinoRepositorio;

    public Inquilino obtenerPorId(Long id){ return inquilinoRepositorio.findById(id).orElseGet(null); }

    @Transactional
    public Inquilino crearInquilino(Inquilino inquilinoDTO){
        try{
            Inquilino inquilino = new Inquilino();
            inquilino.setNombreCompleto(inquilinoDTO.getNombreCompleto());
            inquilino.setDni(inquilinoDTO.getDni());
            inquilino.setCuil(inquilinoDTO.getCuil());
            inquilino.setCorreo(inquilinoDTO.getCorreo());
            inquilino.setTelefono(inquilinoDTO.getTelefono());
            return inquilinoRepositorio.save(inquilino);
        }catch (Exception e){
            throw new RuntimeException("Error al crear el registro");
        }
    }

    @Transactional
    public Inquilino actualizarInquilino(Long id , Inquilino inquilinoDTO){
        try{
            Inquilino inquilino = this.obtenerPorId(id);
            inquilino.setNombreCompleto(inquilinoDTO.getNombreCompleto());
            inquilino.setDni(inquilinoDTO.getDni());
            inquilino.setCuil(inquilinoDTO.getCuil());
            inquilino.setCorreo(inquilinoDTO.getCorreo());
            inquilino.setTelefono(inquilinoDTO.getTelefono());
            return inquilinoRepositorio.save(inquilino);
        }catch (Exception e){
            throw new RuntimeException("Error al actualizar el registro");
        }
    }

    @Transactional(readOnly = true)
    public List<Inquilino> obtenerTodos(){ return inquilinoRepositorio.findAll(); }

    @Transactional
    public HashMap<String, String> eliminarPorId(Long id){
        try{
            HashMap<String, String> respuesta = new HashMap<>();
            respuesta.put("mensaje", "Se ha eliminado correctamente el registro");
            inquilinoRepositorio.deleteById(id);
            return respuesta;
        }catch (Exception e){
            throw new RuntimeException("Error al eliminar el registro");
        }
    }

}
