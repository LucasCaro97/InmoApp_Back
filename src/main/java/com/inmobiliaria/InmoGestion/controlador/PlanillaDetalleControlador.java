package com.inmobiliaria.InmoGestion.controlador;

import com.inmobiliaria.InmoGestion.servicio.PlanillaDetalleMensualServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/planilladetalle")
public class PlanillaDetalleControlador {

    private final PlanillaDetalleMensualServicio planillaDetalleMensualServicio;

    @PutMapping("/{id}")
    public ResponseEntity<?> updateExpensas(@PathVariable Long id, @RequestBody BigDecimal valorExpesensas){
        try{
            planillaDetalleMensualServicio.actualizarExpensas(id, valorExpesensas);
            return ResponseEntity.ok(planillaDetalleMensualServicio);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Error al editar la celda" + e.getMessage());
        }
    }

}
