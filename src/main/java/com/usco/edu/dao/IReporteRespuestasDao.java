package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.dto.ReporteRespuesta;
import com.usco.edu.dto.ReporteRespuestaDetallado;

public interface IReporteRespuestasDao {
	
	// GENERAL ACREDITACION

	List<ReporteRespuesta> generarDatosReporteTodos(int uaa);

	List<ReporteRespuesta> generarDatosReportePorTipoUsuario(int tus, int uaa);

	List<ReporteRespuesta> generarDatosReportePorCuestionario(int cuestionario);

	List<ReporteRespuesta> generarDatosReportePrograma(int programa, int uaa);
	
	// DETALLADO
	
	List<ReporteRespuestaDetallado> generarDatosReporteTodosDetallado(int uaa);

	List<ReporteRespuestaDetallado> generarDatosReportePorTipoUsuarioDetallado(int tus, int uaa);

	List<ReporteRespuestaDetallado> generarDatosReportePorCuestionarioDetallado(int cuestionario);

	List<ReporteRespuestaDetallado> generarDatosReporteProgramaDetallado(int programa, int uaa);
	
	int valoresGraficas(int pregunta, int rop);
	

}
