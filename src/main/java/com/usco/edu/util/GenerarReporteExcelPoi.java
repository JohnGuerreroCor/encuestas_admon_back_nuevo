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

import com.usco.edu.dto.ReporteRespuesta;

@Component
public class GenerarReporteExcelPoi {

	public ByteArrayInputStream generarpdfRespuestas(List<ReporteRespuesta> reporteRespuestas, String titulo)
			throws Exception {

		String[] columns = { "usuario", "cuestionario", "codigo", "pregunta", "respuesta" };
		int[] size = { 35 * 256, 85 * 256, 35 * 256, 100 * 256, 35 * 256 };

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
		for (ReporteRespuesta reporteRespuesta : reporteRespuestas) {
			row = sheet.createRow(indexRow);

			row.createCell(0).setCellValue(reporteRespuesta.getTus().getNombre());
			row.createCell(1).setCellValue(reporteRespuesta.getCue().getNombre());
			row.createCell(2).setCellValue(reporteRespuesta.getPregunta().getIdentificador());
			row.createCell(3).setCellValue(reporteRespuesta.getPregunta().getDescripcion());
			row.createCell(4).setCellValue(reporteRespuesta.getRop().getDescripcion());
			indexRow++;
		}
		workbook.write(stream);
		workbook.close();

		return new ByteArrayInputStream(stream.toByteArray());

	}
}
