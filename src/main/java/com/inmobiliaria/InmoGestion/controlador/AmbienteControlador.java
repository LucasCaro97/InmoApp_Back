package com.inmobiliaria.InmoGestion.controlador;

import com.inmobiliaria.InmoGestion.modelo.Ambientes;
import com.inmobiliaria.InmoGestion.modelo.Caracteristicas;
import com.inmobiliaria.InmoGestion.servicio.AmbientesServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("ambientes")
@RequiredArgsConstructor
public class AmbienteControlador {

    private final AmbientesServicio ambientesServicio;

    @GetMapping
    public ResponseEntity<List<Ambientes>> obtenerTodos(){
        try{
            return ResponseEntity.ok(ambientesServicio.obtenerTodos());
        }catch (Exception e){
            System.out.println(e.getStackTrace());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ambientes> obtenerPorId(@PathVariable Long id){
        try {
            Optional<Ambientes> t = ambientesServicio.obtenerPorId(id);
            return t.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
        }catch (Exception e ){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Ambientes> crear(@RequestBody Ambientes dto){
        try{
            return new ResponseEntity<>(ambientesServicio.crear(dto), HttpStatus.CREATED);
        }catch (Exception e){
            System.out.println(e.getStackTrace());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ambientes> actualizar(@PathVariable Long id, @RequestBody Ambientes dto){
        try{
            Ambientes c = ambientesServicio.actualizar(id, dto);
            if(c != null) return new ResponseEntity<>(c , HttpStatus.OK);
            else return ResponseEntity.notFound().build();
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HashMap<String,String>> eliminar(@PathVariable Long id){
        try{
            HashMap<String, String> respuesta = ambientesServicio.eliminarPorId(id);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
