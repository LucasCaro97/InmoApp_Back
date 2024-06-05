package com.inmobiliaria.InmoGestion.servicio;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ImagenServicio {

    @Value("${ruta.imagenes}")
    private String rutaImagenes;

    public List<String> almacenarImagenes(MultipartFile[] imagenes) {
        try{
            if(imagenes != null){
                List<String> nombresImagenes = new ArrayList<>();
                for (MultipartFile imagenDTO : imagenes){
                    nombresImagenes.add(almacenarImagen(imagenDTO));
                }
                return nombresImagenes;
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<String> almacenarImagenes(MultipartFile[] imagenes, List<String> imagenesExistentes) {
        try{
            if(imagenes != null){
                List<String> nombresImagenes = new ArrayList<>();
                for (MultipartFile imagenDTO : imagenes){
                    nombresImagenes.add(almacenarImagen(imagenDTO));
                }
                List<String> todasLasImagenes = new ArrayList<>(imagenesExistentes);
                todasLasImagenes.addAll(nombresImagenes);
                return todasLasImagenes;
            }
            return imagenesExistentes;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public String almacenarImagen(MultipartFile imagenDTO) {
        try{
            //Genera un nombre unico para la imagen
            String nombreImagen = UUID.randomUUID().toString() + "_" + imagenDTO.getOriginalFilename();
            //Crear la ruta completa de la imagen
            String rutaCompleta = rutaImagenes + "/" + nombreImagen;
            //Guardar la imagen en el servidor
            FileOutputStream fos = new FileOutputStream(rutaCompleta);
            fos.write(imagenDTO.getBytes());
            fos.close();
            return nombreImagen;
        }catch (IOException e){
            throw new RuntimeException("Error al almacenar la imagen");
        }

    }


    public void eliminarImagen(String nombreImagen) {
        try {
            // Crear la ruta completa de la imagen a eliminar
            String rutaCompleta = rutaImagenes + "/" + nombreImagen;
            // Crear el objeto File para la imagen
            File imagen = new File(rutaCompleta);
            // Verificar si la imagen existe antes de intentar eliminarla
            if (imagen.exists()) {
                // Eliminar la imagen
                if (!imagen.delete()) {
                    throw new RuntimeException("No se pudo eliminar la imagen: " + nombreImagen);
                }
            } else {
                throw new RuntimeException("La imagen no existe: " + nombreImagen);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la imagen: " + nombreImagen, e);
        }
    }

    public void eliminarImagenes(List<String> listaImagenes) {
        try{
            if(listaImagenes!=null){
                for (String item: listaImagenes) {
                    eliminarImagen(item);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

