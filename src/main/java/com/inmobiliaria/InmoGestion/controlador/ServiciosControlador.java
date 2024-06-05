package com.inmobiliaria.InmoGestion.controlador;


import com.inmobiliaria.InmoGestion.modelo.Servicios;
import com.inmobiliaria.InmoGestion.servicio.ServiciosServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/servicios")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ServiciosControlador {

    private final ServiciosServicio serviciosServicio;

    @GetMapping
    public ResponseEntity<List<Servicios>> obtenerTodas(){
        try{
            List<Servicios> c = serviciosServicio.obtenerTodos();
            return ResponseEntity.ok(c);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Servicios> obtenerPorId(@PathVariable Long id){
        try{
            Optional<Servicios> c = serviciosServicio.obtenerPorId(id);
            return c.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
        }catch (Exception e ){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Servicios> crear(@RequestBody Servicios dto){
        try{
            Servicios c = serviciosServicio.crear(dto);
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servicios> actualizar(@PathVariable Long id , @RequestBody Servicios dto){
        try{
            System.out.println("Ingresando al servicio");
            Servicios c = serviciosServicio.actualizar(id, dto);
            if(c != null) return new ResponseEntity<>(c, HttpStatus.OK);
            else return ResponseEntity.notFound().build();
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HashMap<String, String>> eliminar(@PathVariable Long id){
        try {
            HashMap<String, String> respuesta = serviciosServicio.eliminarPorId(id);
            return new ResponseEntity<>(respuesta,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



}
