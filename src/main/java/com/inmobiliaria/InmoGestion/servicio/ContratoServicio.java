package com.inmobiliaria.InmoGestion.servicio;

import com.inmobiliaria.InmoGestion.DTO.ContratoDTO;
import com.inmobiliaria.InmoGestion.modelo.Contrato;
import com.inmobiliaria.InmoGestion.modelo.Inmueble;
import com.inmobiliaria.InmoGestion.repositorio.ContratoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContratoServicio {

    private final ContratoRepositorio contratoRepositorio;
    private final InmuebleServicio inmuebleServicio;
    private final InquilinoServicio inquilinoServicio;
    private final TipoContratoServicio tipoContratoServicio;
    private final EstadoContratoServicio estadoContratoServicio;
    private final IndiceServicio indiceServicio;

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
        contrato.setIndice(indiceServicio.obtenerPorId(contratoDTO.getIndice()));
        contrato.setActualizaCada(contratoDTO.getActualizaCada());
        return contratoRepositorio.save(contrato);
    }

    @Transactional(readOnly = true)
    public Contrato obtenerPorId(Long id){ return contratoRepositorio.findById(id).orElseGet(null); }

    @Transactional(readOnly = true)
    public List<Contrato> obtenerTodos() { return contratoRepositorio.findAll(); }


    @Transactional
    public Contrato actulizarContrato(Long id , ContratoDTO contratoDTO){
        /*  AGREGAR VALIDACIONES --> SI EL CONTRATO ESTA EN CURSO O RESCINDIDO NO SE PUEDE MODIFICAR
            UNICAMENTE CUANDO ESTE EN TRAMITE
        */
        Inmueble inmueble = inmuebleServicio.obtenerPorId(contratoDTO.getInmuebleId()).orElse(null);
        Contrato contrato = this.obtenerPorId(id);
        contrato.setInmueble(inmueble);
        contrato.setInquilino(inquilinoServicio.obtenerPorId(contratoDTO.getInquilinoId()));
        contrato.setPropietario(inmueble.getPropietario());
        contrato.setTipoContrato(tipoContratoServicio.obtenerPorId(contratoDTO.getTipoContratoId()));
        contrato.setFechaInicio(contratoDTO.getFechaInicio());
        contrato.setFechaFin(contratoDTO.getFechaFin());
        contrato.setObservaciones(contratoDTO.getObservaciones());
        contrato.setEstadoContrato(estadoContratoServicio.obtenerPorId(contratoDTO.getEstadoContrato()));
        contrato.setIndice(indiceServicio.obtenerPorId(contratoDTO.getIndice()));
        contrato.setActualizaCada(contratoDTO.getActualizaCada());
        return contratoRepositorio.save(contrato);
    }

    @Transactional
    public HashMap<String, String> eliminarPorId(Long id){
        try{
            HashMap<String, String> respuesa = new HashMap<>();
            respuesa.put("mensaje", "Registro eliminado correctamente");
            contratoRepositorio.deleteById(id);
            return respuesa;
        }catch (Exception e){
            throw new RuntimeException("Error al eliminar el registro");
        }
    }


//    public List<Contrato> findByEstado(Long id) {
//
//    }
}
