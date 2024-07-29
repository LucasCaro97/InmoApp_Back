package com.inmobiliaria.InmoGestion.controlador;

import com.inmobiliaria.InmoGestion.DTO.ArquilerDetalleDTO;
import com.inmobiliaria.InmoGestion.DTO.ContratoDTO;
import com.inmobiliaria.InmoGestion.DTO.SimplifiedArquilerDetalleDTO;
import com.inmobiliaria.InmoGestion.modelo.Contrato;
import com.inmobiliaria.InmoGestion.servicio.ContratoServicio;
import com.inmobiliaria.InmoGestion.servicio.PlanillaDetalleMensualServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contrato")
public class ContratoControlador {

    private final ContratoServicio contratoServicio;

    @GetMapping
    public ResponseEntity<List<Contrato>> getAll(){
        try{
            List<Contrato> contrato = contratoServicio.obtenerTodos();
            return ResponseEntity.ok(contrato);
        }catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Contrato> getById(@PathVariable Long id){
        try{
            Contrato contrato = contratoServicio.obtenerPorId(id);
            if (contrato!= null ) return ResponseEntity.ok(contrato);
            else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Contrato> create (@RequestBody ContratoDTO contratoDTO){
        try{
            Contrato contrato = contratoServicio.crearContrato(contratoDTO);
            return new ResponseEntity<>(contrato, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contrato> update (@PathVariable Long id, @RequestBody ContratoDTO contratoDTO){
        try{
            Contrato contrato = contratoServicio.actulizarContrato(id , contratoDTO);
            return new ResponseEntity<>(contrato , HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HashMap<String, String>> delete(@PathVariable Long id){
        try{
            HashMap<String, String> respuesta = contratoServicio.eliminarPorId(id);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    /*
    * EndPoint de prueba para peticiones a la api de arquiler
    *
    @GetMapping
    public ResponseEntity<List<SimplifiedArquilerDetalleDTO>> calcularAlquiler(){
        try{
            return ResponseEntity.ok(planillaDetalleMensualServicio.generarCalculo());
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    */

}
