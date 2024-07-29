package com.inmobiliaria.InmoGestion.controlador;

import com.inmobiliaria.InmoGestion.modelo.EstadoInmueble;
import com.inmobiliaria.InmoGestion.servicio.EstadoInmuebleServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/estadoinmueble")
@RequiredArgsConstructor
public class EstadoInmuebleControlador {

    private final EstadoInmuebleServicio estadoInmuebleServicio;

    @GetMapping
    public ResponseEntity<List<EstadoInmueble>>  getAll(){
        try{
            List<EstadoInmueble> estadoInmuebleList = estadoInmuebleServicio.obtenerTodos();
            return ResponseEntity.ok(estadoInmuebleList);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoInmueble> getById(@PathVariable Long id, EstadoInmueble estadoInmuebleDTO){
        try{
            EstadoInmueble estadoInmueble = estadoInmuebleServicio.actualizar(id, estadoInmuebleDTO);
            return new ResponseEntity<>(estadoInmueble, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<EstadoInmueble> create(@RequestBody EstadoInmueble estadoInmuebleDTO){
        try{
            EstadoInmueble estadoInmueble = estadoInmuebleServicio.crear(estadoInmuebleDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("{id}")
    public ResponseEntity<EstadoInmueble> update (@PathVariable Long id, @RequestBody EstadoInmueble estadoInmuebleDTO){
        try{
            EstadoInmueble estadoInmueble = estadoInmuebleServicio.actualizar(id, estadoInmuebleDTO);
            if(estadoInmueble != null) return new ResponseEntity<>(estadoInmueble, HttpStatus.OK);
            else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HashMap<String, String>> delteById(@PathVariable Long id){
        try{
            HashMap<String, String> respuesta = estadoInmuebleServicio.eliminarPorId(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
