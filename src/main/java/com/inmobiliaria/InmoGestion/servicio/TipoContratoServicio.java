package com.inmobiliaria.InmoGestion.servicio;

import com.inmobiliaria.InmoGestion.modelo.TipoContrato;
import com.inmobiliaria.InmoGestion.repositorio.TipoContratoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoContratoServicio {

    private final TipoContratoRepositorio tipoContratoRepositorio;

    @Transactional(readOnly = true)
    public TipoContrato obtenerPorId(Long id) {
        return tipoContratoRepositorio.findById(id).orElseGet(null);
    }

    @Transactional(readOnly = true)
    public List<TipoContrato> obtenerTodos(){
        return tipoContratoRepositorio.findAll();
    }

    @Transactional
    public TipoContrato crearTipoContrado(TipoContrato tipoContratoDTO){
        try{
            TipoContrato tipoContrato = new TipoContrato();
            tipoContrato.setNombre(tipoContratoDTO.getNombre());
            return tipoContratoRepositorio.save(tipoContrato);
        }catch (Exception e){
            throw new RuntimeException("Error al crear el registro");
        }
    }

    public TipoContrato actualizarTipoContrato(Long id, TipoContrato tipoContratoDTO){
        try{
            TipoContrato tipoContrato = this.obtenerPorId(id);
            tipoContrato.setNombre(tipoContratoDTO.getNombre());
            return tipoContratoRepositorio.save(tipoContrato);
        }catch (Exception e){
            throw new RuntimeException("Error al actualizar el registro");
        }
    }

    @Transactional
    public HashMap<String, String> eliminarPorId(Long id){
        try{
            HashMap<String, String> respuesta = new HashMap<>();
            respuesta.put("mensaje", "Registro eliminado correctamente");
            tipoContratoRepositorio.deleteById(id);
            return respuesta;
        }catch (Exception e){
            throw new RuntimeException("Error al eliminar el registro");
        }
    }
}
