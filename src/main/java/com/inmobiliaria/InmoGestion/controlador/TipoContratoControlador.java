package com.inmobiliaria.InmoGestion.controlador;

import com.inmobiliaria.InmoGestion.modelo.TipoContrato;
import com.inmobiliaria.InmoGestion.servicio.TipoContratoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/tipocontrato")
@RequiredArgsConstructor
public class TipoContratoControlador {

    private final TipoContratoServicio tipoContratoServicio;


    @GetMapping()
    public ResponseEntity<List<TipoContrato>> getAll(){
        try{
            List<TipoContrato> tipoContratoList = tipoContratoServicio.obtenerTodos();
            return ResponseEntity.ok(tipoContratoList);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoContrato> getById(@PathVariable Long id){
        try{
            TipoContrato tipoContrato = tipoContratoServicio.obtenerPorId(id);
            return ResponseEntity.ok(tipoContrato);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<TipoContrato> create(@RequestBody TipoContrato tipoContratoDTO){
        try{
            TipoContrato tipoContrato = tipoContratoServicio.crearTipoContrado(tipoContratoDTO);
            return new ResponseEntity<>(tipoContrato, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoContrato> update (@PathVariable Long id, @RequestBody TipoContrato tipoContratoDTO){
        try{
            TipoContrato tipoContrato = tipoContratoServicio.actualizarTipoContrato(id, tipoContratoDTO);
            if(tipoContrato != null) return new ResponseEntity<>(tipoContrato, HttpStatus.OK);
            else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HashMap<String, String>> deleteById(@PathVariable Long id){
        try{
            HashMap<String, String> respuesta = tipoContratoServicio.eliminarPorId(id);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
