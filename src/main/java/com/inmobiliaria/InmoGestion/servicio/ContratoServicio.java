package com.inmobiliaria.InmoGestion.servicio;

import com.inmobiliaria.InmoGestion.DTO.ContratoDTO;
import com.inmobiliaria.InmoGestion.modelo.Contrato;
import com.inmobiliaria.InmoGestion.modelo.Inmueble;
import com.inmobiliaria.InmoGestion.repositorio.ContratoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContratoServicio {

    private final ContratoRepositorio contratoRepositorio;
    private final InmuebleServicio inmuebleServicio;
    private final InquilinoServicio inquilinoServicio;
    private final TipoContratoServicio tipoContratoServicio;
    private final EstadoContratoServicio estadoContratoServicio;

    @Transactional
    public Contrato crearContrato(ContratoDTO contratoDTO){
        Inmueble inmueble = inmuebleServicio.obtenerPorId(contratoDTO.getInmuebleId()).orElse(null);
        Contrato contrato = new Contrato();
        contrato.setInmueble(inmueble);
        contrato.setInquilino(inquilinoServicio.obtenerPorId(contratoDTO.getInquilinoId()));
        contrato.setPropietario(inmueble.getPropietario());
        contrato.setTipoContrato(tipoContratoServicio.obtenerPorId(contratoDTO.getTipoContratoId()));
        contrato.setFechaInicio(contratoDTO.getFechaInicio());
        contrato.setFechaFin(contratoDTO.getFechaFin());
        contrato.setObservaciones(contratoDTO.getObservaciones());
        contrato.setEstadoContrato(estadoContratoServicio.obtenerPorId(contratoDTO.getEstadoContrato()));
        return contratoRepositorio.save(contrato);
    }

    @Transactional(readOnly = true)
    public Contrato obtenerPorId(Long id){ return contratoRepositorio.findById(id).orElseGet(null); }

    @Transactional(readOnly = true)
    public List<Contrato> obtenerTodos() { return contratoRepositorio.findAll(); }


    @Transactional
    public Contrato actulizarContrato(ContratoDTO contratoDTO){
        /*  AGREGAR VALIDACIONES --> SI EL CONTRATO ESTA EN CURSO O RESCINDIDO NO SE PUEDE MODIFICAR
            UNICAMENTE CUANDO ESTE EN TRAMITE
        */
        Inmueble inmueble = inmuebleServicio.obtenerPorId(contratoDTO.getInmuebleId()).orElse(null);
        Contrato contrato = new Contrato();
        contrato.setInmueble(inmueble);
        contrato.setInquilino(inquilinoServicio.obtenerPorId(contratoDTO.getInquilinoId()));
        contrato.setPropietario(inmueble.getPropietario());
        contrato.setTipoContrato(tipoContratoServicio.obtenerPorId(contratoDTO.getTipoContratoId()));
        contrato.setFechaInicio(contratoDTO.getFechaInicio());
        contrato.setFechaFin(contratoDTO.getFechaFin());
        contrato.setObservaciones(contratoDTO.getObservaciones());
        contrato.setEstadoContrato(estadoContratoServicio.obtenerPorId(contratoDTO.getEstadoContrato()));
        return contratoRepositorio.save(contrato);
    }


    public List<Contrato> findByEstado(Long id) {

    }
}
