package com.inmobiliaria.InmoGestion.servicio;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inmobiliaria.InmoGestion.DTO.ArquilerDetalleDTO;
import com.inmobiliaria.InmoGestion.DTO.ArquilerResponse;
import com.inmobiliaria.InmoGestion.DTO.SimplifiedArquilerDetalleDTO;
import com.inmobiliaria.InmoGestion.repositorio.PlanillaDetalleMensualRepositorio;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlanillaDetalleMensualServicio {

    private final PlanillaDetalleMensualRepositorio planillaDetalleRepo;
    private final ContratoServicio contratoServicio;





    public List<SimplifiedArquilerDetalleDTO> generarCalculo() throws Exception {
        HttpResponse<String> response = Unirest.post("https://arquilerapi1.p.rapidapi.com/calculate")
                .header("x-rapidapi-key", "2bed41cea6msh8e171750e7d6023p1fd74djsn7e2a2189e00d")
                .header("x-rapidapi-host", "arquilerapi1.p.rapidapi.com")
                .header("Content-Type", "application/json")
                .body("{\"amount\":10000,\"date\":\"2023-01-01\",\"months\":3,\"rate\":\"ipc\"}")
                .asString();

        ObjectMapper objectMapper = new ObjectMapper();
        ArquilerResponse arquilerResponse = objectMapper.readValue(response.getBody(), ArquilerResponse.class);

        return arquilerResponse.getData().stream()
                .map(detail -> {
                    SimplifiedArquilerDetalleDTO simplified = new SimplifiedArquilerDetalleDTO();
                    simplified.setDate(detail.getDate());
                    simplified.setAmount(detail.getAmount());
                    return simplified;
                }).collect(Collectors.toList());

    }


}
