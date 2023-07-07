package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.dto.ReporteAgrupado;
import com.usco.edu.dto.ReporteRespuesta;
import com.usco.edu.dto.ReporteRespuestaDetallado;
import com.usco.edu.entities.Pregunta;
import com.usco.edu.entities.Resultado;

public interface IReporteRespuestasDao {
	
	//RESULTADO TOTAL
	
	List<Resultado> obtenerResultados(int cuestionario);
	
	//REPORTE AGRUPADO TEXTO Y OPCIONES DE RESPUESTA
	
	List<Pregunta> obtenerPreguntasOpcionesCuestionario(int cuestionario);
	
	List<Pregunta> obtenerPreguntasTextoCuestionario(int cuestionario);
	
	List<ReporteAgrupado> generarDatosReporteAgrupadoTexto(int cuestionario, String preguntas);
	
	List<ReporteAgrupado> generarDatosReporteAgrupadoOpciones(int cuestionario, String preguntas);
	
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
