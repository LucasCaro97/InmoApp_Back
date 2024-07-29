package com.inmobiliaria.InmoGestion.servicio;

import com.inmobiliaria.InmoGestion.modelo.IndiceICL;
import com.inmobiliaria.InmoGestion.repositorio.IndiceICLRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IndiceICLServicio {

    private final IndiceICLRepositorio indiceICLRepositorio;

    @Transactional(readOnly = true)
    public IndiceICL obtenerPorId(Long id){
        return indiceICLRepositorio.findById(id).orElseGet(null);
    }

    @Transactional(readOnly = true)
    public List<IndiceICL> obtenerTodos(){
        return indiceICLRepositorio.findAll();
    }

    @Transactional
    public IndiceICL crearIndice(IndiceICL indiceICLDTO){
        try{
            IndiceICL indiceICL = new IndiceICL();
            indiceICL.setFecha(indiceICLDTO.getFecha());
            indiceICL.setValor(indiceICLDTO.getValor());
            return indiceICLRepositorio.save(indiceICL);
        }catch (Exception e){
            throw new RuntimeException("Error al crear el registro");
        }
    }

    @Transactional
    public IndiceICL actualizarPorId(Long id, IndiceICL indiceICLDTO){
        try{
            IndiceICL indiceICL = this.obtenerPorId(id);
            indiceICL.setFecha(indiceICLDTO.getFecha());
            indiceICL.setValor(indiceICL.getValor());
            return indiceICLRepositorio.save(indiceICL);
        }catch (Exception e){
            throw new RuntimeException("Error al actualizar el registro");
        }
    }

    @Transactional
    public HashMap<String, String> eliminarPorId(Long id){
        try{
            HashMap<String , String> respuesta = new HashMap<>();
            respuesta.put("mensaje", "Registro eliminado correctamente");
            indiceICLRepositorio.deleteById(id);
            return respuesta;
        }catch (Exception e){
            throw new RuntimeException("Error al eliminar el registro");
        }
    }



}
