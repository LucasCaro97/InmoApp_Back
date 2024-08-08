package com.inmobiliaria.InmoGestion.controlador;


import com.inmobiliaria.InmoGestion.DTO.InmuebleDTO;
import com.inmobiliaria.InmoGestion.modelo.Inmueble;
import com.inmobiliaria.InmoGestion.servicio.ImagenServicio;
import com.inmobiliaria.InmoGestion.servicio.InmuebleServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/inmueble")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class InmuebleControlador {

    private final InmuebleServicio inmuebleServicio;
    private final ImagenServicio imagenServicio;

    //HABILITAR PARA QUE SE PUEDA ACCEDER SIN TOKEN
    @GetMapping
    public ResponseEntity<List<Inmueble>> obtenerTodas(){
        try{
            List<Inmueble> c = inmuebleServicio.obtenerTodos();
            return ResponseEntity.ok(c);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    //HABILITAR PARA QUE SE PUEDA ACCEDER SIN TOKEN
    @GetMapping("/{id}")
    public ResponseEntity<Inmueble> obtenerPorId(@PathVariable Long id){
        try{
            Optional<Inmueble> c = inmuebleServicio.obtenerPorId(id);
            return c.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
        }catch (Exception e ){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(       @RequestParam("nombre") String nombre,
                                          @RequestParam("direccion") String direccion,
                                          @RequestParam("ciudad") String ciudad,
                                          @RequestParam("provincia") String provincia,
                                          @RequestParam("descripcion") String descripcion,
                                          @RequestParam("categoria") Long categoria,
                                          @RequestParam("caracteristicas") Long[] caracteristicas,
                                          @RequestParam("servicios") Long[] servicios,
                                          @RequestParam("ambientes") Long[] ambientes,
                                          @RequestParam("esVenta") Boolean esVenta,
                                          @RequestParam("esAlquiler") Boolean esAlquiler,
                                          @RequestParam("precioAlquiler") BigDecimal precioAlquiler,
                                          @RequestParam("precioVenta") BigDecimal precioVenta,
                                          @RequestParam("propietario") Long propietario,
                                          @RequestParam(value = "imagenes", required = false) MultipartFile[] imagenes){
        try {
            InmuebleDTO i = new InmuebleDTO();
            i.setNombre(nombre);
            i.setDireccion(direccion);
            i.setCiudad(ciudad);
            i.setProvincia(provincia);
            i.setDescripcion(descripcion);
            i.setCategoria(categoria);
            i.setCaracteristicas(caracteristicas);
            i.setServicios(servicios);
            i.setAmbientes(ambientes);
            i.setImagenes(imagenes);
            i.setEsVenta(esVenta);
            i.setEsAlquiler(esAlquiler);
            i.setPrecioAlquiler(precioAlquiler);
            i.setPrecioVenta(precioVenta);
            i.setPropietario(propietario);
            Inmueble c = inmuebleServicio.crear(i);
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id , @RequestParam("nombre") String nombre,
                                        @RequestParam("direccion") String direccion,
                                        @RequestParam("ciudad") String ciudad,
                                        @RequestParam("provincia") String provincia,
                                        @RequestParam("descripcion") String descripcion,
                                        @RequestParam("categoria") Long categoria,
                                        @RequestParam("caracteristicas") Long[] caracteristicas,
                                        @RequestParam("servicios") Long[] servicios,
                                        @RequestParam("ambientes") Long[] ambientes,
                                        @RequestParam("esVenta") Boolean esVenta,
                                        @RequestParam("esAlquiler") Boolean esAlquiler,
                                        @RequestParam("precioAlquiler") BigDecimal precioAlquiler,
                                        @RequestParam("precioVenta") BigDecimal precioVenta,
                                        @RequestParam("estadoInmueble") Long estadoInmueble,
                                        @RequestParam("propietario") Long propietario,
                                        @RequestParam(value = "imagenes", required = false) MultipartFile[] imagenes){
        try{
            InmuebleDTO inmuebleDTO = new InmuebleDTO();
            inmuebleDTO.setNombre(nombre);
            inmuebleDTO.setDireccion(direccion);
            inmuebleDTO.setCiudad(ciudad);
            inmuebleDTO.setProvincia(provincia);
            inmuebleDTO.setDescripcion(descripcion);
            inmuebleDTO.setCategoria(categoria);
            inmuebleDTO.setCaracteristicas(caracteristicas);
            inmuebleDTO.setServicios(servicios);
            inmuebleDTO.setAmbientes(ambientes);
            inmuebleDTO.setImagenes(imagenes);
            inmuebleDTO.setEsVenta(esVenta);
            inmuebleDTO.setEsAlquiler(esAlquiler);
            inmuebleDTO.setPrecioAlquiler(precioAlquiler);
            inmuebleDTO.setPrecioVenta(precioVenta);
            inmuebleDTO.setEstadoInmueble(estadoInmueble);
            inmuebleDTO.setPropietario(propietario);
            Inmueble c = inmuebleServicio.actualizar(id, inmuebleDTO);
            if(c != null) return new ResponseEntity<>(c, HttpStatus.OK);
            else return ResponseEntity.notFound().build();
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HashMap<String, String>> eliminar(@PathVariable Long id){
        try {
            System.out.println("Eliminar item " + id);
            HashMap<String, String> respuesta = inmuebleServicio.eliminarPorId(id);
            return new ResponseEntity<>(respuesta,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @DeleteMapping("{id}/deleteImage/{nameImg}")
    public ResponseEntity<?> eliminarImagen(@PathVariable Long id,@PathVariable String nameImg){
        try{
            Inmueble inmueble = inmuebleServicio.obtenerPorId(id).orElse(null);
            List<String> nuevaListaImg = inmueble.getListaImagenes().stream().filter(item -> !item.equals(nameImg)).collect(Collectors.toList());
            inmuebleServicio.actualizarImagenes(inmueble.getId(), nuevaListaImg);
            imagenServicio.eliminarImagen(nameImg);
            return  new ResponseEntity<>("Imagen eliminada correctamente", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
