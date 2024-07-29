package com.inmobiliaria.InmoGestion.servicio;

import com.inmobiliaria.InmoGestion.modelo.Contrato;
import com.inmobiliaria.InmoGestion.modelo.PlanillaDetalleMensual;
import com.inmobiliaria.InmoGestion.modelo.PlanillaMaestroMensual;
import com.inmobiliaria.InmoGestion.repositorio.PlanillaDetalleMensualRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanillaDetalleMensualServicio {

    private final PlanillaDetalleMensualRepositorio planillaDetalleRepo;
    private final ContratoServicio contratoServicio;

    /*
    *   CADA LINEA DETALLE VA A CORRESPONDER A CADA CONTRATO QUE ESTE CON ESTADO 'EN CURSO'
    * */

//    public void crearDetallesPlanilla(PlanillaMaestroMensual planillaMaestroMensualDTO){
//        List<Contrato> listaContratosActivos = contratoServicio.findByEstado(2L);
//
//        for (Contrato c : listaContratosActivos ){
//            PlanillaDetalleMensual planillaDetalleMensual = new PlanillaDetalleMensual();
//            planillaDetalleMensual.setContrato(c);
//
//
//            /*
//            *       Valor Nuevo = [ Valor inicial del Contrato ] * ( [ Valor del índice actual ] / [ Valor del índice al inicio contrato ] )
//            *       NuevoImporte = precioBase * ( indiceActual / indiceBase ) indiceBase = indice estipulado en el inicio del contrato
//            */
                /*
                 *   API PARA CONSULTAR AL BCRA LOS INDICES DE ICL ---> api.bcra.gob.ar/estadisticas/v2.0/datosvariable/40/2023-08-01/2023-08-02
                 * */
//        }
//
//        }
//
//
//    }



}
