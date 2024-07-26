package com.inmobiliaria.InmoGestion.servicio;

import com.inmobiliaria.InmoGestion.modelo.TipoContrato;
import com.inmobiliaria.InmoGestion.repositorio.TipoContratoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        TipoContrato tipoContrato = new TipoContrato();
        tipoContrato.setNombre(tipoContratoDTO.getNombre());
        return tipoContratoRepositorio.save(tipoContrato);
    }

    public TipoContrato actualizarTipoContrato(Long id, TipoContrato tipoContratoDTO){
        TipoContrato tipoContrato = this.obtenerPorId(id);
        tipoContrato.setNombre(tipoContratoDTO.getNombre());
        return tipoContratoRepositorio.save(tipoContrato);
    }

    @Transactional
    public void eliminarPorId(Long id){
        tipoContratoRepositorio.deleteById(id);
    }
}
