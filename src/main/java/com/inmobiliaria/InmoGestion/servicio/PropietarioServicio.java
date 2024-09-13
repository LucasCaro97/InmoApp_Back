package com.inmobiliaria.InmoGestion.servicio;

import com.inmobiliaria.InmoGestion.modelo.Propietario;
import com.inmobiliaria.InmoGestion.repositorio.PropietarioRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PropietarioServicio {

    private final PropietarioRepositorio propietarioRepositorio;

    @Transactional
    public Propietario crear(Propietario propietarioDTO){
        try{
            Propietario propietario = new Propietario();
            propietario.setNombreCompleto(propietarioDTO.getNombreCompleto());
            propietario.setDireccion(propietarioDTO.getDireccion());
            propietario.setDni(propietarioDTO.getDni());
            propietario.setCuil(propietarioDTO.getCuil());
            propietario.setTelefono(propietarioDTO.getTelefono());
            propietario.setCorreo(propietarioDTO.getCorreo());
            propietario.setPorcentaje_comision(propietarioDTO.getPorcentaje_comision());
            return propietarioRepositorio.save(propietario);
        }catch (Exception e){
            throw new RuntimeException("Error al crear el registro");
        }
    }

    @Transactional(readOnly=true)
    public Propietario obtenerPorId(Long id){
        return propietarioRepositorio.findById(id).orElseGet(null);
    }

    @Transactional(readOnly = true)
    public List<Propietario> obtenerTodos(){
        return propietarioRepositorio.findAll();
    }

    @Transactional
    public Propietario actualizar(Long id, Propietario propietarioDTO){
        try{
            Propietario propietario = this.obtenerPorId(id);
            propietario.setNombreCompleto(propietarioDTO.getNombreCompleto());
            propietario.setDireccion(propietarioDTO.getDireccion());
            propietario.setDni(propietarioDTO.getDni());
            propietario.setCuil(propietarioDTO.getCuil());
            propietario.setTelefono(propietarioDTO.getTelefono());
            propietario.setCorreo(propietarioDTO.getCorreo());
            propietario.setPorcentaje_comision(propietarioDTO.getPorcentaje_comision());
            return propietarioRepositorio.save(propietario);
        }catch (Exception e){
            throw new RuntimeException("Error al editar el registro");
        }
    }

    @Transactional
    public HashMap<String, String> eliminarPorId(Long id){
        try{
            HashMap<String, String> respuesta = new HashMap<>();
            respuesta.put("mensaje", "Registro eliminado correctamente");
            propietarioRepositorio.deleteById(id);
            return respuesta;
        }catch (Exception e){
            throw new RuntimeException("Error al eliminar el registro");
        }
    }


}
