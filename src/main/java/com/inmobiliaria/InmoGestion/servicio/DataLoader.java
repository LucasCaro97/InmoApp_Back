package com.inmobiliaria.InmoGestion.servicio;

import com.inmobiliaria.InmoGestion.modelo.EstadoContrato;
import com.inmobiliaria.InmoGestion.modelo.EstadoInmueble;
import com.inmobiliaria.InmoGestion.modelo.Indice;
import com.inmobiliaria.InmoGestion.repositorio.EstadoContratoRepositorio;
import com.inmobiliaria.InmoGestion.repositorio.EstadoInmuebleRepositorio;
import com.inmobiliaria.InmoGestion.repositorio.IndiceRepositorio;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader {

    private final EstadoInmuebleRepositorio estadoInmuebleRepositorio;
    private final EstadoContratoRepositorio estadoContratoRepositorio;
    private final IndiceRepositorio indiceRepositorio;


    @Bean
    public CommandLineRunner loadDataEstadoContrato() {
        return args -> {
            if (estadoContratoRepositorio.count() == 0) {
                EstadoContrato estado1 = new EstadoContrato();
                estado1.setNombre("en tramite");
                EstadoContrato estado2 = new EstadoContrato();
                estado2.setNombre("firmado");
                EstadoContrato estado3 = new EstadoContrato();
                estado3.setNombre("rescindido");

                estadoContratoRepositorio.save(estado1);
                estadoContratoRepositorio.save(estado2);
                estadoContratoRepositorio.save(estado3);

            }
        };
    }

    @Bean
    public CommandLineRunner loadDataEstadoInmueble() {
        return args -> {
            if (indiceRepositorio.count() == 0) {
                Indice ind1 = new Indice();
                ind1.setNombre("icl");
                Indice ind2 = new Indice();
                ind2.setNombre("ipc");
                Indice ind3 = new Indice();
                ind3.setNombre("casa_propia");

                indiceRepositorio.save(ind1);
                indiceRepositorio.save(ind2);
                indiceRepositorio.save(ind3);

            }
        };

    }

    @Bean
    public CommandLineRunner loadDataIndice() {
        return args -> {
            if (estadoInmuebleRepositorio.count() == 0) {
                EstadoInmueble estado1 = new EstadoInmueble();
                estado1.setNombre("activo");
                EstadoInmueble estado2 = new EstadoInmueble();
                estado2.setNombre("ocupado");
                EstadoInmueble estado3 = new EstadoInmueble();
                estado3.setNombre("reservado");

                estadoInmuebleRepositorio.save(estado1);
                estadoInmuebleRepositorio.save(estado2);
                estadoInmuebleRepositorio.save(estado3);

            }
        };

    }
}
