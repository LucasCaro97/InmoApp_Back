package com.inmobiliaria.InmoGestion.controlador;

import com.inmobiliaria.InmoGestion.modelo.Inquilino;
import com.inmobiliaria.InmoGestion.servicio.InquilinoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/inquilino")
@RequiredArgsConstructor
public class InquilinoControlador {

    private final InquilinoServicio inquilinoServicio;

    @GetMapping
    public ResponseEntity<List<Inquilino>> getAll() {
        try {
            List<Inquilino> inquilinoList = inquilinoServicio.obtenerTodos();
            return ResponseEntity.ok(inquilinoList);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inquilino> obtenerPorId(@PathVariable Long id) {
        try {
            Inquilino inquilino = inquilinoServicio.obtenerPorId(id);
            if (inquilino != null) return ResponseEntity.ok(inquilino);
            else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<?> crear(@RequestBody Inquilino inquilinoDTO) {
        try {
            Inquilino inquilino = inquilinoServicio.crearInquilino(inquilinoDTO);
            return new ResponseEntity<>(inquilino, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inquilino> editar(@PathVariable Long id, @RequestBody Inquilino inquilinoDTO){
        try{
            Inquilino inquilino = inquilinoServicio.actualizarInquilino(id, inquilinoDTO);
            if(inquilino != null) return new ResponseEntity<>(inquilino, HttpStatus.OK);
            else return  ResponseEntity.notFound().build();
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HashMap<String, String>> eliminar (@PathVariable Long id){
         try{
             HashMap<String, String> respuesta = inquilinoServicio.eliminarPorId(id);
             return new ResponseEntity<>(respuesta, HttpStatus.OK);
         }catch (Exception e){
             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
         }
    }
}

