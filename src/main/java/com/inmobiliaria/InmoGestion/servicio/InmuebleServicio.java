package com.inmobiliaria.InmoGestion.servicio;


import com.inmobiliaria.InmoGestion.DTO.InmuebleDTO;
import com.inmobiliaria.InmoGestion.modelo.*;
import com.inmobiliaria.InmoGestion.repositorio.InmuebleRepositorio;
import com.inmobiliaria.InmoGestion.repositorio.TipoOperacionRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class InmuebleServicio {

    private final InmuebleRepositorio inmuebleRepositorio;
    private final CategoriaServicio categoriaServicio;
    private final CaracteristicasServicio caracteristicasServicio;
    private final ServiciosServicio serviciosServicio;
    private final AmbientesServicio ambientesServicio;
    private final ImagenServicio imagenServicio;
    private final EstadoInmuebleServicio estadoInmuebleServicio;
    private final PropietarioServicio propietarioServicio;

    @Transactional
    public Inmueble crear (InmuebleDTO dto){
        try{
            List<Caracteristicas> listaDeCaracteristicas = new ArrayList<>();
            List<Servicios> listaDeServicios = new ArrayList<>();
            List<Ambientes> listaDeAmbientes = new ArrayList<>();


            for (Long c: dto.getCaracteristicas()) {
                listaDeCaracteristicas.add(caracteristicasServicio.obtenerPorId(c).orElse(null));
            }

            for (Long s: dto.getServicios()) {
                listaDeServicios.add(serviciosServicio.obtenerPorId(s).orElse(null));
            }

            for (Long a: dto.getAmbientes()) {
                listaDeAmbientes.add(ambientesServicio.obtenerPorId(a).orElse(null));
            }

            Inmueble inmueble = new Inmueble();
            inmueble.setNombre(dto.getNombre());
            inmueble.setDireccion(dto.getDireccion());
            inmueble.setCiudad(dto.getCiudad());
            inmueble.setProvincia(dto.getProvincia());
            inmueble.setDescripcion(dto.getDescripcion());
            inmueble.setCategoria(categoriaServicio.obtenerPorId(dto.getCategoria()).orElse(null));
            inmueble.setCaracteristicas(listaDeCaracteristicas);
            inmueble.setServicios(listaDeServicios);
            inmueble.setAmbientes(listaDeAmbientes);
            inmueble.setListaImagenes(imagenServicio.almacenarImagenes(dto.getImagenes()));
            inmueble.setEsAlquiler(dto.getEsAlquiler());
            inmueble.setEsVenta(dto.getEsVenta());
            inmueble.setPrecioAlquiler(dto.getPrecioAlquiler());
            inmueble.setPrecioVenta(dto.getPrecioVenta());
            inmueble.setPropietario(propietarioServicio.obtenerPorId(dto.getPropietario()));
            inmueble.setEstadoInmueble( (estadoInmuebleServicio.obtenerActivo() != null ) ? estadoInmuebleServicio.obtenerActivo() : null ); // Busca estado inmueble por id = 1 *- Para ello siempre el primer estadoInmueble a crear debe ser 'activo'-*
            return inmuebleRepositorio.save(inmueble);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Error al crear el registro");
        }
    }


    @Transactional
    public Inmueble actualizar (Long id, InmuebleDTO dto){
        try{
            Inmueble inmueble = inmuebleRepositorio.findById(id).orElse(null);
            if (inmueble != null){
                List<Caracteristicas> listaDeCaracteristicas = new ArrayList<>();
                List<Servicios> listaDeServicios = new ArrayList<>();
                List<Ambientes> listaDeAmbientes = new ArrayList<>();


                for (Long c: dto.getCaracteristicas()) {
                    listaDeCaracteristicas.add(caracteristicasServicio.obtenerPorId(c).orElse(null));
                }

                for (Long s: dto.getServicios()) {
                    listaDeServicios.add(serviciosServicio.obtenerPorId(s).orElse(null));
                }

                for (Long a: dto.getAmbientes()) {
                    listaDeAmbientes.add(ambientesServicio.obtenerPorId(a).orElse(null));
                }


                inmueble.setNombre(dto.getNombre());
                inmueble.setDireccion(dto.getDireccion());
                inmueble.setCiudad(dto.getCiudad());
                inmueble.setProvincia(dto.getProvincia());
                inmueble.setDescripcion(dto.getDescripcion());
                inmueble.setCategoria(categoriaServicio.obtenerPorId(dto.getCategoria()).orElse(null));
                inmueble.setCaracteristicas(listaDeCaracteristicas);
                inmueble.setServicios(listaDeServicios);
                inmueble.setAmbientes(listaDeAmbientes);
                if(dto.getImagenes() != null) inmueble.setListaImagenes(imagenServicio.almacenarImagenes(dto.getImagenes(), inmueble.getListaImagenes()));
                inmueble.setEsAlquiler(dto.getEsAlquiler());
                inmueble.setEsVenta(dto.getEsVenta());
                inmueble.setPrecioAlquiler(dto.getPrecioAlquiler());
                inmueble.setPrecioVenta(dto.getPrecioVenta());
                inmueble.setEstadoInmueble(estadoInmuebleServicio.obtenerPorId(dto.getEstadoInmueble()));
                return inmuebleRepositorio.save(inmueble);
            }else{
                throw new RuntimeException("No se ha encontrado el registro");
            }
        }catch (Exception e){
            throw new RuntimeException("Error al actualizar el registro");
        }
    }
    @Transactional
    public Inmueble actualizarImagenes(Long id, List<String> listaImagenes){
        try{
            Inmueble inmueble = inmuebleRepositorio.findById(id).orElse(null);
            inmueble.setListaImagenes(listaImagenes);
            return inmuebleRepositorio.save(inmueble);
            }catch (Exception e){
            throw new RuntimeException("Error al actualizar el registro");
        }
    }

    @Transactional(readOnly = true)
    public Optional<Inmueble> obtenerPorId(Long id){
        return inmuebleRepositorio.findById(id);
    }


    @Transactional(readOnly = true)
    public List<Inmueble> obtenerTodos(){
        return inmuebleRepositorio.findAll();
    }

    @Transactional
    public HashMap<String, String> eliminarPorId(Long id){
        try {
            System.out.println("Ingreso al servicio eliminar");
            //CREO UN HASHMAP PARA DEVOLVER LA RESPUESTA
            HashMap<String, String> respuesta = new HashMap<>();
            Optional<Inmueble> inmuebleOptional = inmuebleRepositorio.findById(id); // BUSCO EL INMUEBLE EN LA BD

            //Verifico si existe el inmueble
            if(inmuebleOptional.isPresent()) { //SI EXISTE EL INMUEBLE
                Inmueble inmueble = inmuebleOptional.get(); // OBTENGO EL OBJETO
                List<String> listaImagenes = inmueble.getListaImagenes();   // PASO LOS NOMBRES DE LAS IMAGENES QUE TIENE VINCULADAS
                inmuebleRepositorio.deleteById(id); //ELIMINO DE LA BD
                if (!listaImagenes.isEmpty()) { // SI TIENE IMAGENES LAS ELIMINO
                    imagenServicio.eliminarImagenes(listaImagenes);
                }

                respuesta.put("mensaje", "Se ha eliminardo correctamente el registro");
            }else{
                respuesta.put("mensaje", "No se encontró el inmueble con el ID especificado");
            }

            return  respuesta;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Error al eliminar el registro");
        }
    }


}
