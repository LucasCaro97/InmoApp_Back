package com.inmobiliaria.InmoGestion.controlador;

import com.inmobiliaria.InmoGestion.modelo.PlanillaDetalleMensual;
import com.inmobiliaria.InmoGestion.modelo.PlanillaMaestroMensual;
import com.inmobiliaria.InmoGestion.servicio.PlanillaMaestroMensualServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Month;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/planillamensual")
public class PlanillaMaestroControlador {

    private final PlanillaMaestroMensualServicio planillaMaestroMensualServicio;

    @GetMapping
    public ResponseEntity<List<PlanillaMaestroMensual>> getAll(){
        try{
            List<PlanillaMaestroMensual> planillaMaestroMensualList = planillaMaestroMensualServicio.obtenerTodas();
            return ResponseEntity.ok(planillaMaestroMensualList);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/{mes}/{anio}")
    public ResponseEntity<PlanillaMaestroMensual> generarPlanilla(@PathVariable Integer mes, @PathVariable Integer anio){
        try {
            PlanillaMaestroMensual planillaMensual = planillaMaestroMensualServicio.crearPlanillaMaestro(mes, anio);
            return ResponseEntity.ok(planillaMensual);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/testear")
//    public void generarPlanilla(){
//            planillaMaestroMensualServicio.crearPlanillaMaestroTest();
//    }


}
