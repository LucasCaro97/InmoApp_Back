package com.inmobiliaria.InmoGestion.servicio;

import com.inmobiliaria.InmoGestion.modelo.Inquilino;
import com.inmobiliaria.InmoGestion.repositorio.InquilinoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InquilinoServicio {

    private final InquilinoRepositorio inquilinoRepositorio;

    public Inquilino obtenerPorId(Long id){ return inquilinoRepositorio.findById(id).orElseGet(null); }

    @Transactional
    public Inquilino crearInquilino(Inquilino inquilinoDTO){
        Inquilino inquilino = new Inquilino();
        inquilino.setNombreCompleto(inquilinoDTO.getNombreCompleto());
        inquilino.setDni(inquilinoDTO.getDni());
        inquilino.setCuil(inquilinoDTO.getCuil());
        inquilino.setCorreo(inquilinoDTO.getCorreo());
        inquilino.setTelefono(inquilinoDTO.getTelefono());
        return inquilinoRepositorio.save(inquilino);
    }

    @Transactional
    public Inquilino actualizarInquilino(Long id , Inquilino inquilinoDTO){
        Inquilino inquilino = this.obtenerPorId(id);
        inquilino.setNombreCompleto(inquilinoDTO.getNombreCompleto());
        inquilino.setDni(inquilinoDTO.getDni());
        inquilino.setCuil(inquilinoDTO.getCuil());
        inquilino.setCorreo(inquilinoDTO.getCorreo());
        inquilino.setTelefono(inquilinoDTO.getTelefono());
        return inquilinoRepositorio.save(inquilino);
    }

    @Transactional(readOnly = true)
    public List<Inquilino> obtenerTodos(){ return inquilinoRepositorio.findAll(); }

    @Transactional
    public void eliminarPorId(Long id){
        inquilinoRepositorio.deleteById(id);
    }

}
