package com.inmobiliaria.InmoGestion.controlador;

import com.inmobiliaria.InmoGestion.modelo.Categoria;
import com.inmobiliaria.InmoGestion.servicio.CategoriaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
    @RequestMapping("/categoria")
@RequiredArgsConstructor
public class CategoriaControlador {

        private final CategoriaServicio categoriaServicio;

        @GetMapping
        public ResponseEntity<List<Categoria>> obtenerTodas(){
            try{
                List<Categoria> c = categoriaServicio.obtenerTodas();
                return ResponseEntity.ok(c);
            }catch (Exception e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }


        @GetMapping("/{id}")
    public ResponseEntity<Categoria> obtenerPorId(@PathVariable Long id){
            try{
                Optional<Categoria> c = categoriaServicio.obtenerPorId(id);
                return c.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
            }catch (Exception e ){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    @PostMapping
    public ResponseEntity<Categoria> crear(@RequestBody Categoria dto){
        try{
            Categoria c = categoriaServicio.crear(dto);
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizar(@PathVariable Long id , @RequestBody Categoria dto){
            try{
                Categoria c = categoriaServicio.actualizar(id, dto);
                if(c != null) return new ResponseEntity<>(c, HttpStatus.OK);
                else return ResponseEntity.notFound().build();
            }catch (Exception e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HashMap<String, String>> eliminar(@PathVariable Long id){
            try {
                HashMap<String, String> respuesta = categoriaServicio.eliminarPorId(id);
                return new ResponseEntity<>(respuesta,HttpStatus.OK);
            }catch (Exception e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

    }



}
