package com.usco.edu.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Component;

import com.usco.edu.dto.ReporteRespuestaDetallado;

@Component
public class GenerarReporteExcelDetallado {
	
	public ByteArrayInputStream generarExcelRespuestasDetallado(List<ReporteRespuestaDetallado> reporteRespuestasDetallado, String titulo)
			throws Exception {

		String[] columns = { "Usuario", "Codigo Estudiante", "Apellido", "Nombre", "Tipo Documento", "Indentificación", "Expedicion", "Edad", "Sede", "Programa", "Cuestionario", "Codigo", "Pregunta", "Respuesta" };
		int[] size = { 80 * 100, 80 * 100, 80 * 100, 80 * 100, 80 * 50, 80 * 100, 80 * 100, 80 * 30, 80 * 40, 80 * 200, 80 * 200, 80 * 50, 80 * 200, 80 * 200 };

		Workbook workbook = new SXSSFWorkbook();

		ByteArrayOutputStream stream = new ByteArrayOutputStream();

		Sheet sheet = workbook.createSheet(titulo);
		Row row = sheet.createRow(0);

		for (int i = 0; i < columns.length; i++) {
			Cell cell = row.createCell(i);
			sheet.setColumnWidth(i, size[i]);
			cell.setCellValue(columns[i]);
		}
		int indexRow = 1;
		for (ReporteRespuestaDetallado reporteRespuestaDetallado : reporteRespuestasDetallado) {
			row = sheet.createRow(indexRow);

			row.createCell(0).setCellValue(reporteRespuestaDetallado.getTus().getNombre());
			row.createCell(1).setCellValue(reporteRespuestaDetallado.getEst().getCodigo());
			row.createCell(2).setCellValue(reporteRespuestaDetallado.getPer().getApellido());
			row.createCell(3).setCellValue(reporteRespuestaDetallado.getPer().getNombre());
			row.createCell(4).setCellValue(reporteRespuestaDetallado.getPer().getTipoDocumento());
			row.createCell(5).setCellValue(reporteRespuestaDetallado.getPer().getIdentificacion());
			row.createCell(6).setCellValue(reporteRespuestaDetallado.getPer().getExpedicion());
			row.createCell(7).setCellValue(reporteRespuestaDetallado.getPer().getEdad());
			row.createCell(8).setCellValue(reporteRespuestaDetallado.getSede().getNombre());
			row.createCell(9).setCellValue(reporteRespuestaDetallado.getUaa().getNombre());
			row.createCell(10).setCellValue(reporteRespuestaDetallado.getCue().getNombre());
			row.createCell(11).setCellValue(reporteRespuestaDetallado.getPregunta().getIdentificador());
			row.createCell(12).setCellValue(reporteRespuestaDetallado.getPregunta().getDescripcion());
			row.createCell(13).setCellValue(reporteRespuestaDetallado.getRop().getDescripcion());
			indexRow++;
		}
		workbook.write(stream);
		workbook.close();

		return new ByteArrayInputStream(stream.toByteArray());

	}

}
