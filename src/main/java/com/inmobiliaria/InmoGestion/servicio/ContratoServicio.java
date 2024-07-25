package com.inmobiliaria.InmoGestion.servicio;

import com.inmobiliaria.InmoGestion.DTO.ContratoDTO;
import com.inmobiliaria.InmoGestion.modelo.Contrato;
import com.inmobiliaria.InmoGestion.modelo.Inmueble;
import com.inmobiliaria.InmoGestion.repositorio.ContratoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContratoServicio {

    private final ContratoRepositorio contratoRepositorio;
    private final InmuebleServicio inmuebleServicio;
    private final InquilinoServicio inquilinoServicio;
    private final TipoContratoServicio tipoContratoServicio;

    public void crearContrato(ContratoDTO contratoDTO){

        Inmueble inmueble = inmuebleServicio.obtenerPorId(contratoDTO.getInmuebleId()).orElse(null);
        Contrato contrato = new Contrato();
        contrato.setInmueble(inmueble);
        contrato.setInquilino(inquilinoServicio.obtenerPorId(contratoDTO.getInquilinoId()));
        contrato.setPropietario(inmueble.getPropietario());
        contrato.setTipoContrato(tipoContratoServicio.obtenerPorId(contratoDTO.getTipoContratoId()).orElse(null));
        contrato.setFechaInicio(contratoDTO.getFechaInicio());
        contrato.setFechaFin(contratoDTO.getFechaFin());
        contrato.setObservaciones(contratoDTO.getObservaciones());
        contratoRepositorio.save(contrato);
    }


}
