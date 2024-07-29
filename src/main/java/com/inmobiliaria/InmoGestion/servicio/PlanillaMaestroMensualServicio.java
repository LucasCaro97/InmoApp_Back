package com.inmobiliaria.InmoGestion.servicio;

import com.inmobiliaria.InmoGestion.modelo.PlanillaMaestroMensual;
import com.inmobiliaria.InmoGestion.repositorio.PlanillaMaestroMensualRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanillaMaestroMensualServicio {


    private final PlanillaMaestroMensualRepositorio planillaMaestroRepo;

    @Transactional
    public PlanillaMaestroMensual crearPlanillaMaestro(Month mes, Integer year){
        PlanillaMaestroMensual planillaMaestroMensual = new PlanillaMaestroMensual();
        planillaMaestroMensual.setMes(mes);
        planillaMaestroMensual.setAnio(year);
        return planillaMaestroRepo.save(planillaMaestroMensual);
    }

    @Transactional
    public PlanillaMaestroMensual actualizarPlanillaMaestro(Long id, PlanillaMaestroMensual planillaMaestroMensualDTO){
        PlanillaMaestroMensual planillaMaestro = planillaMaestroRepo.findById(id).orElse(null);
        planillaMaestro.setMes(planillaMaestroMensualDTO.getMes());
        planillaMaestro.setAnio(planillaMaestroMensualDTO.getAnio());
        return planillaMaestroRepo.save(planillaMaestro);
    }

    @Transactional(readOnly = true)
    public PlanillaMaestroMensual obtenerPorId(Long id) { return planillaMaestroRepo.findById(id).orElseGet(null); }

    @Transactional(readOnly = true)
    public List<PlanillaMaestroMensual> obtenerTodas() { return planillaMaestroRepo.findAll(); }

    @Transactional
    public void eliminarPorId(Long id) { planillaMaestroRepo.deleteById(id); }

}
