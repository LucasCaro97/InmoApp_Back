package com.inmobiliaria.InmoGestion.servicio;

import com.inmobiliaria.InmoGestion.modelo.Indice;
import com.inmobiliaria.InmoGestion.repositorio.IndiceRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IndiceServicio {

    private final IndiceRepositorio indiceRepositorio;

    @Transactional(readOnly = true)
    public Indice obtenerPorId(Long id ){
        return indiceRepositorio.findById(id).orElseGet(null);
    }

    @Transactional(readOnly = true)
    public List<Indice> getAll(){
        return indiceRepositorio.findAll();
    }

    @Transactional
    public Indice create(Indice indiceDTO){
        try{
            Indice indice = new Indice();
            indice.setNombre(indiceDTO.getNombre());
            return indiceRepositorio.save(indice);
        }catch (Exception e){
            throw new RuntimeException("Error al crear el registro");
        }
    }

    @Transactional
    public Indice update(Long id, Indice indiceDTO){
        try{
            Indice indice = this.obtenerPorId(id);
            indice.setNombre(indiceDTO.getNombre());
            return indiceRepositorio.save(indice);
        }catch (Exception e){
            throw new RuntimeException("Error al actualizar el registro");
        }
    }

    @Transactional
    public HashMap<String, String> deleteById(Long id){
        try{
            HashMap<String , String> respuesta = new HashMap<>();
            respuesta.put("mensaje", "Registro eliminado correctamente");
            indiceRepositorio.deleteById(id);
            return respuesta;
        }catch (Exception e){
            throw new RuntimeException("Error al eliminar el registro");
        }
    }

}
