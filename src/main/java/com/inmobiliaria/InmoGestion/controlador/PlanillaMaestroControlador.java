package com.inmobiliaria.InmoGestion.controlador;

import com.inmobiliaria.InmoGestion.Reportes.PlanillaMensualExcel;
import com.inmobiliaria.InmoGestion.modelo.Contrato;
import com.inmobiliaria.InmoGestion.modelo.PlanillaDetalleMensual;
import com.inmobiliaria.InmoGestion.modelo.PlanillaMaestroMensual;
import com.inmobiliaria.InmoGestion.servicio.ContratoServicio;
import com.inmobiliaria.InmoGestion.servicio.PlanillaMaestroMensualServicio;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/planillamensual")
public class PlanillaMaestroControlador {

    private final PlanillaMaestroMensualServicio planillaMaestroMensualServicio;
    private final ContratoServicio contratoServicio;

    @GetMapping
    public ResponseEntity<List<PlanillaMaestroMensual>> getAll(){
        try{
            List<PlanillaMaestroMensual> planillaMaestroMensualList = planillaMaestroMensualServicio.obtenerTodas();
            return ResponseEntity.ok(planillaMaestroMensualList);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{mes}/{anio}")
    public ResponseEntity<PlanillaMaestroMensual> getByMonthAndYear(@PathVariable Integer mes, @PathVariable Integer anio){
        try{
            PlanillaMaestroMensual planillaMaestroMensual = planillaMaestroMensualServicio.obtenerPorMesAndAnio(mes,anio);
            return ResponseEntity.ok(planillaMaestroMensual);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{mes}/{anio}")
    public ResponseEntity<PlanillaMaestroMensual> generarPlanilla(@PathVariable Integer mes, @PathVariable Integer anio){
        try {
            List<Contrato> contratoList = contratoServicio.findByEstadoActivo();
            PlanillaMaestroMensual planillaMensual = planillaMaestroMensualServicio.crearPlanillaMaestro(mes, anio, contratoList);
            return ResponseEntity.ok(planillaMensual);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/exportar/{mes}/{anio}")
    public void generarExcelPlanilla(HttpServletResponse response, @PathVariable Integer mes, @PathVariable Integer anio){
        try{
            response.setContentType("application/octec-stream");
            String cabecera = "Content-Disposition";
            String valor = "attachment; filename=PlanillaMensual_" + mes + "_" + anio + ".xlsx";
            response.setHeader(cabecera,valor);
            PlanillaMensualExcel exporter = new PlanillaMensualExcel(planillaMaestroMensualServicio.obtenerDetalles(mes, anio));
            exporter.exportar(response);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
