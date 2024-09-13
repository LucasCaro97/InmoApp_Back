package com.inmobiliaria.InmoGestion.Reportes;
import com.inmobiliaria.InmoGestion.modelo.PlanillaDetalleMensual;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

@Getter
@Setter
public class PlanillaMensualExcel {

    private XSSFWorkbook libro;
    private XSSFSheet hoja;
    private List<PlanillaDetalleMensual> listaDeCobros;

    public PlanillaMensualExcel(List<PlanillaDetalleMensual> listaCobros){
        libro = new XSSFWorkbook();
        hoja = libro.createSheet("Planilla Mensual");
        this.listaDeCobros = listaCobros;
    }

    private void escribirCabeceraDeLaTabla(){
        Row fila = hoja.createRow(0);

        CellStyle estilo = libro.createCellStyle();
        XSSFFont fuente = libro.createFont();
        fuente.setBold(true);
        fuente.setFontHeight(16);
        estilo.setFont(fuente);

        Cell celda = fila.createCell(0);
        celda.setCellValue("Propietario");
        celda.setCellStyle(estilo);

        Cell celda1 = fila.createCell(1);
        celda1.setCellValue("Inquilino");
        celda1.setCellStyle(estilo);

        Cell celda2 = fila.createCell(2);
        celda2.setCellValue("Tipo de Contrato");
        celda2.setCellStyle(estilo);

        Cell celda3 = fila.createCell(3);
        celda3.setCellValue("Fecha Con.");
        celda3.setCellStyle(estilo);

        Cell celda4 = fila.createCell(4);
        celda4.setCellValue("Ubicacion");
        celda4.setCellStyle(estilo);

        Cell celda5 = fila.createCell(5);
        celda5.setCellValue("Imp.");
        celda5.setCellStyle(estilo);

        Cell celda6 = fila.createCell(6);
        celda6.setCellValue("Serv");
        celda6.setCellStyle(estilo);

        Cell celda7 = fila.createCell(7);
        celda7.setCellValue("Hno");
        celda7.setCellStyle(estilo);

        Cell celda8 = fila.createCell(8);
        celda8.setCellValue("Total");
        celda8.setCellStyle(estilo);

        Cell celda9 = fila.createCell(9);
        celda9.setCellValue("Fecha");
        celda9.setCellStyle(estilo);

        Cell celda10 = fila.createCell(10);
        celda10.setCellValue("Observaciones");
        celda10.setCellStyle(estilo);

        Cell celda11 = fila.createCell(11);
        celda11.setCellValue("Fecha");
        celda11.setCellStyle(estilo);

        Cell celda12 = fila.createCell(12);
        celda12.setCellValue("FIRMA");
        celda12.setCellStyle(estilo);


    }

    private void escribirDatosDeLaTabla(){
        int numeroFilas = 1;
        CellStyle estilo = libro.createCellStyle();
        XSSFFont fuente = libro.createFont();
        fuente.setFontHeight(14);
        estilo.setFont(fuente);

        for(PlanillaDetalleMensual p : listaDeCobros){
            Row fila = hoja.createRow(numeroFilas++);
            Cell celda = fila.createCell(0);
            celda.setCellValue(p.getContrato().getPropietario().getNombreCompleto());
            hoja.autoSizeColumn(0);
            celda.setCellStyle(estilo);

            Cell celda1 = fila.createCell(1);
            celda1.setCellValue(p.getContrato().getInquilino().getNombreCompleto());
            hoja.autoSizeColumn(1);
            celda.setCellStyle(estilo);

            Cell celda2 = fila.createCell(2);
            celda2.setCellValue(p.getContrato().getTipoContrato().getNombre());
            hoja.autoSizeColumn(2);
            celda.setCellStyle(estilo);

            Cell celda3 = fila.createCell(3);
            celda3.setCellValue(p.getContrato().getFechaFin().toString());
            hoja.autoSizeColumn(3);
            celda.setCellStyle(estilo);

            Cell celda4 = fila.createCell(4);
            celda4.setCellValue(p.getContrato().getInmueble().getDireccion());
            hoja.autoSizeColumn(4);
            celda.setCellStyle(estilo);

            Cell celda5 = fila.createCell(5);
            celda5.setCellValue(p.getImporteAlquiler().doubleValue());
            hoja.autoSizeColumn(5);
            celda.setCellStyle(estilo);

            Cell celda6 = fila.createCell(6);
            celda6.setCellValue("");
            hoja.autoSizeColumn(6);
            celda.setCellStyle(estilo);

            Cell celda7 = fila.createCell(7);
            celda7.setCellValue(p.getHonorarios().doubleValue());
            hoja.autoSizeColumn(7);
            celda.setCellStyle(estilo);

            Cell celda8 = fila.createCell(8);
            celda8.setCellValue(p.getImporteAlquiler().subtract(p.getHonorarios()).doubleValue());
            hoja.autoSizeColumn(8);
            celda.setCellStyle(estilo);

            Cell celda9 = fila.createCell(9);
            celda9.setCellValue("");
            hoja.autoSizeColumn(9);
            celda.setCellStyle(estilo);

            Cell celda10 = fila.createCell(10);
            celda10.setCellValue("");
            hoja.autoSizeColumn(10);
            celda.setCellStyle(estilo);

            Cell celda11 = fila.createCell(11);
            celda11.setCellValue("");
            hoja.autoSizeColumn(11);
            celda.setCellStyle(estilo);

            Cell celda12 = fila.createCell(12);
            celda12.setCellValue("");
            hoja.autoSizeColumn(12);
            celda.setCellStyle(estilo);

        }

    }

    public void exportar(HttpServletResponse response){
        try{
            escribirCabeceraDeLaTabla();
            escribirDatosDeLaTabla();
            ServletOutputStream outputStream = response.getOutputStream();
            libro.write(outputStream);
            libro.close();
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
