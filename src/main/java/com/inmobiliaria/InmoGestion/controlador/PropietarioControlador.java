package com.inmobiliaria.InmoGestion.controlador;

import com.inmobiliaria.InmoGestion.modelo.Propietario;
import com.inmobiliaria.InmoGestion.servicio.PropietarioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/propietario")
public class PropietarioControlador {

    private final PropietarioServicio propietarioServicio;

    @GetMapping
    public ResponseEntity<List<Propietario>> getAll(){
        try{
            List<Propietario> propietarioList = propietarioServicio.obtenerTodos();
            return ResponseEntity.ok(propietarioList);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Propietario> getPropietario(@PathVariable Long id){
        try{
            Propietario propietario = propietarioServicio.obtenerPorId(id);
            if(propietario != null) return ResponseEntity.ok(propietario);
            else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Propietario> createPropietario(@RequestBody Propietario propietarioDTO){
        try{
            Propietario propietario = propietarioServicio.crear(propietarioDTO);
            return new ResponseEntity<>(propietario, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Propietario> updatePropietario(@PathVariable Long id, @RequestBody Propietario propietarioDTO){
        try{
            Propietario propietario = propietarioServicio.actualizar(id, propietarioDTO);
            if(propietario != null ) return new ResponseEntity<>(propietario, HttpStatus.OK);
            else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HashMap<String, String>> deletePropietario(@PathVariable Long id){
        try{
            HashMap<String, String> respueta = propietarioServicio.eliminarPorId(id);
            return new ResponseEntity<>(respueta, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
