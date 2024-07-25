package com.inmobiliaria.InmoGestion.servicio;

import com.inmobiliaria.InmoGestion.modelo.Inquilino;
import com.inmobiliaria.InmoGestion.repositorio.InquilinoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InquilinoServicio {

    private final InquilinoRepositorio inquilinoRepositorio;

    public Inquilino obtenerPorId(Long id){ return inquilinoRepositorio.findById(id).orElseGet(null); }

}
