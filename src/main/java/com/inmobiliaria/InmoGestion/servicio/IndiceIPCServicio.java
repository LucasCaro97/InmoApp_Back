package com.inmobiliaria.InmoGestion.servicio;

import com.inmobiliaria.InmoGestion.modelo.IndiceIPC;
import com.inmobiliaria.InmoGestion.repositorio.IndiceIPCRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IndiceIPCServicio {

    private final IndiceIPCRepositorio indiceIPCRepositorio;


    @Transactional(readOnly = true)
    public IndiceIPC obtenerPorId(Long id){
        return indiceIPCRepositorio.findById(id).orElseGet(null);
    }

    @Transactional(readOnly = true)
    public List<IndiceIPC> obtenerTodos(){
        return indiceIPCRepositorio.findAll();
    }

    @Transactional
    public IndiceIPC crearIndice(IndiceIPC indiceIPCDTO){
        try{
            IndiceIPC indiceIPC = new IndiceIPC();
            indiceIPC.setFecha(indiceIPCDTO.getFecha());
            indiceIPC.setValor(indiceIPCDTO.getValor());
            return indiceIPCRepositorio.save(indiceIPC);
        }catch (Exception e){
            throw new RuntimeException("Error al crear el registro");
        }
    }

    @Transactional
    public IndiceIPC actualizarPorId(Long id, IndiceIPC indiceIPCDTO){
        try{
            IndiceIPC indiceIPC = this.obtenerPorId(id);
            indiceIPC.setFecha(indiceIPCDTO.getFecha());
            indiceIPC.setValor(indiceIPCDTO.getValor());
            return indiceIPCRepositorio.save(indiceIPC);
        }catch (Exception e){
            throw new RuntimeException("Error al actualizar el registro");
        }
    }

    @Transactional
    public HashMap<String, String> eliminarPorId(Long id){
        try{
            HashMap<String , String> respuesta = new HashMap<>();
            respuesta.put("mensaje", "Registro eliminado correctamente");
            indiceIPCRepositorio.deleteById(id);
            return respuesta;
        }catch (Exception e){
            throw new RuntimeException("Error al eliminar el registro");
        }
    }


}
