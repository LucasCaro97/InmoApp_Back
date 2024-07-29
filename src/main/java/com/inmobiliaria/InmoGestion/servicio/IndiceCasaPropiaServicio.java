package com.inmobiliaria.InmoGestion.servicio;

import com.inmobiliaria.InmoGestion.modelo.IndiceCasaPropia;
import com.inmobiliaria.InmoGestion.modelo.IndiceIPC;
import com.inmobiliaria.InmoGestion.repositorio.IndiceCasaPropiaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IndiceCasaPropiaServicio {

    private final IndiceCasaPropiaRepositorio indiceCasaPropiaRepositorio;

    @Transactional(readOnly = true)
    public IndiceCasaPropia obtenerPorId(Long id){
        return indiceCasaPropiaRepositorio.findById(id).orElseGet(null);
    }

    @Transactional(readOnly = true)
    public List<IndiceCasaPropia> obtenerTodos(){
        return indiceCasaPropiaRepositorio.findAll();
    }

    @Transactional
    public IndiceCasaPropia crearIndice(IndiceCasaPropia indiceCasaPropiaDTO){
        try{
            IndiceCasaPropia indiceCasaPropia = new IndiceCasaPropia();
            indiceCasaPropia.setFecha(indiceCasaPropia.getFecha());
            indiceCasaPropia.setValor(indiceCasaPropia.getValor());
            return indiceCasaPropiaRepositorio.save(indiceCasaPropia);
        }catch (Exception e){
            throw new RuntimeException("Error al crear el registro");
        }
    }

    @Transactional
    public IndiceCasaPropia actualizarPorId(Long id, IndiceCasaPropia indiceCasaPropiaDTO){
        try{
            IndiceCasaPropia indiceCasaPropia = this.obtenerPorId(id);
            indiceCasaPropia.setFecha(indiceCasaPropia.getFecha());
            indiceCasaPropia.setValor(indiceCasaPropia.getValor());
            return indiceCasaPropiaRepositorio.save(indiceCasaPropia);
        }catch (Exception e){
            throw new RuntimeException("Error al actualizar el registro");
        }
    }

    @Transactional
    public HashMap<String, String> eliminarPorId(Long id){
        try{
            HashMap<String , String> respuesta = new HashMap<>();
            respuesta.put("mensaje", "Registro eliminado correctamente");
            indiceCasaPropiaRepositorio.deleteById(id);
            return respuesta;
        }catch (Exception e){
            throw new RuntimeException("Error al eliminar el registro");
        }
    }


}
