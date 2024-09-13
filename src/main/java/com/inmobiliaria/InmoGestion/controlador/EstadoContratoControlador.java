package com.inmobiliaria.InmoGestion.controlador;

import com.inmobiliaria.InmoGestion.modelo.EstadoContrato;
import com.inmobiliaria.InmoGestion.servicio.EstadoContratoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/estadocontrato")
@RequiredArgsConstructor
public class EstadoContratoControlador {

    private final EstadoContratoServicio estadoContratoServicio;

    @GetMapping()
    public ResponseEntity<List<EstadoContrato>> getAll(){
        try{
            List<EstadoContrato> estadoContratoList = estadoContratoServicio.obtenerTodos();
            return ResponseEntity.ok(estadoContratoList);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoContrato> getById(@PathVariable Long id){
        try{
            EstadoContrato estadoContrato = estadoContratoServicio.obtenerPorId(id);
            return  ResponseEntity.ok(estadoContrato);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> create (@RequestBody EstadoContrato estadoContratoDTO){
        try{
            EstadoContrato estadoContrato = estadoContratoServicio.crearEstado(estadoContratoDTO);
            return new ResponseEntity<>(estadoContrato, HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadoContrato> update(@PathVariable Long id, @RequestBody EstadoContrato estadoContratoDTO){
        try{
            EstadoContrato estadoContrato = estadoContratoServicio.actualizarEstado(id, estadoContratoDTO);
            return new ResponseEntity<>(estadoContrato, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HashMap<String, String>> delteById(@PathVariable Long id){
        try{
            HashMap<String, String> respuesta = estadoContratoServicio.eliminarEstado(id);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
