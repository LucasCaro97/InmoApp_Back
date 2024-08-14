package com.inmobiliaria.InmoGestion.controlador;

import com.inmobiliaria.InmoGestion.modelo.Indice;
import com.inmobiliaria.InmoGestion.modelo.Servicios;
import com.inmobiliaria.InmoGestion.servicio.IndiceServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/indice")
public class IndiceControlador {

    private final IndiceServicio indiceServicio;

    @GetMapping
    public ResponseEntity<List<Indice>> obtenerTodas(){
        try{
            List<Indice> c = indiceServicio.getAll();
            return ResponseEntity.ok(c);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Indice> obtenerPorId(@PathVariable Long id){
        try{
            Indice indice = indiceServicio.obtenerPorId(id);
            if(indice != null) return ResponseEntity.ok(indice);
            else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e ){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Indice> crear(@RequestBody Indice indiceDTO){
        try{
            Indice indice = indiceServicio.create(indiceDTO);
            return new ResponseEntity<>(indice, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Indice> actualizar(@PathVariable Long id , @RequestBody Indice indiceDTO){
        try{
            Indice indice = indiceServicio.update(id, indiceDTO);
            if(indice != null) return new ResponseEntity<>(indice, HttpStatus.OK);
            else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HashMap<String, String>> eliminar(@PathVariable Long id){
        try {
            HashMap<String, String> respuesta = indiceServicio.deleteById(id);
            return new ResponseEntity<>(respuesta,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
