package com.inmobiliaria.InmoGestion.controlador;


import com.inmobiliaria.InmoGestion.modelo.Caracteristicas;
import com.inmobiliaria.InmoGestion.modelo.TipoOperacion;
import com.inmobiliaria.InmoGestion.servicio.CaracteristicasServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/caracteristicas")
@RequiredArgsConstructor
public class CaracteristicasControlador {

    private final CaracteristicasServicio caracteristicasServicio;

    @GetMapping
    public ResponseEntity<List<Caracteristicas>> obtenerTodos(){
        try{
            return ResponseEntity.ok(caracteristicasServicio.obtenerCaracteristicas());
        }catch (Exception e){
            System.out.println(e.getStackTrace());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Caracteristicas> obtenerPorId(@PathVariable Long id){
        try {
            Optional<Caracteristicas> t = caracteristicasServicio.obtenerPorId(id);
            return t.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
        }catch (Exception e ){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Caracteristicas> crear(@RequestBody Caracteristicas dto){
            try{
                return new ResponseEntity<>(caracteristicasServicio.crear(dto), HttpStatus.CREATED);
            }catch (Exception e){
                System.out.println(e.getStackTrace());
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Caracteristicas> actualizar(@PathVariable Long id, @RequestBody Caracteristicas dto){
        try{
            Caracteristicas c = caracteristicasServicio.actualizar(id, dto);
            if(c != null) return new ResponseEntity<>(c , HttpStatus.OK);
            else return ResponseEntity.notFound().build();
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HashMap<String,String>> eliminar(@PathVariable Long id){
        try{
            HashMap<String, String> respuesta = caracteristicasServicio.deleteById(id);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
