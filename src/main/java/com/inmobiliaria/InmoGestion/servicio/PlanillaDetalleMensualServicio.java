package com.inmobiliaria.InmoGestion.servicio;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inmobiliaria.InmoGestion.DTO.ArquilerDetalleDTO;
import com.inmobiliaria.InmoGestion.DTO.ArquilerResponse;
import com.inmobiliaria.InmoGestion.DTO.SimplifiedArquilerDetalleDTO;
import com.inmobiliaria.InmoGestion.modelo.Contrato;
import com.inmobiliaria.InmoGestion.modelo.PlanillaDetalleMensual;
import com.inmobiliaria.InmoGestion.modelo.PlanillaMaestroMensual;
import com.inmobiliaria.InmoGestion.repositorio.PlanillaDetalleMensualRepositorio;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlanillaDetalleMensualServicio {

    private final ContratoServicio contratoServicio;
    private final PlanillaDetalleMensualRepositorio planillaDetalleMensualRepositorio;

    public void crearDetallesMensual(PlanillaMaestroMensual idMaestroPlanilla){
        try{
            List<Contrato> contratoList = contratoServicio.findByEstadoActivo();
            List<PlanillaDetalleMensual> detallesPlanilla = new ArrayList<>();

            contratoList.forEach(item -> {
                PlanillaDetalleMensual detalle = new PlanillaDetalleMensual();
                detalle.setPlanillaMaestro(idMaestroPlanilla);
                detalle.setContrato(item);
                detalle.setImporteAlquiler(calcularArquilerPorContrato(item.getId()).getAmount()); // DEVUELVE EL AMOUNT DEL PERIODO CORRESPONDIENTE ( VER TEMA DIAS )
                detalle.setHonorarios(detalle.getImporteAlquiler().multiply(BigDecimal.valueOf(item.getPropietario().getPorcentaje_comision()).divide(BigDecimal.valueOf(100))));
                planillaDetalleMensualRepositorio.save(detalle);
            });
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }


    public SimplifiedArquilerDetalleDTO calcularArquilerPorContrato(Long contratoId) {
        try {
            Contrato contrato = contratoServicio.obtenerPorId(contratoId);
            List<SimplifiedArquilerDetalleDTO> periodos = generarCalculoArquiler(contrato.getImporteBase(), contrato.getFechaInicio().toString(), contrato.getActualizaCada(), contrato.getIndice().getNombre());
            SimplifiedArquilerDetalleDTO itemRetorno = new SimplifiedArquilerDetalleDTO();

            for (SimplifiedArquilerDetalleDTO item : periodos) {
                LocalDate fechaPeriodo = LocalDate.parse(item.getDate());
                LocalDate fechaActual = LocalDate.now();

                if (fechaActual.isAfter(fechaPeriodo)) {
                    itemRetorno = item;
                }
            }

            return itemRetorno;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<SimplifiedArquilerDetalleDTO> generarCalculoArquiler(BigDecimal importeBase, String fechaInicio, Integer actualizaCada, String indice) throws Exception {
        HttpResponse<String> response = Unirest.post("https://arquilerapi1.p.rapidapi.com/calculate")
                .header("x-rapidapi-key", "8c4a0f6e62mshcaab1f33712d5cdp1b3356jsna958d14f453f")
                .header("x-rapidapi-host", "arquilerapi1.p.rapidapi.com")
                .header("Content-Type", "application/json")
                .body(generarRequestBody(importeBase, fechaInicio, actualizaCada, indice))
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

    public String generarRequestBody(BigDecimal importeBase, String fechaInicio, Integer actualizaCada, String indice) throws JsonProcessingException {
        Map<String, Object> bodyParams = new HashMap<>();
        bodyParams.put("amount", importeBase);
        bodyParams.put("date", fechaInicio);
        bodyParams.put("months", actualizaCada);
        bodyParams.put("rate", indice);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(bodyParams);
        return requestBody;
    }

}
