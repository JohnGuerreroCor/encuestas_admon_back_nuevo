package com.usco.edu.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import com.usco.edu.dto.DatosGraficaPreguntaPrincipal;
import com.usco.edu.dto.ReporteAgrupado;
import com.usco.edu.dto.ReporteRespuesta;
import com.usco.edu.entities.Pregunta;
import com.usco.edu.entities.Respuesta;
import com.usco.edu.entities.Resultado;

public interface IRespuestaService {

	List<Respuesta> find();

	Respuesta findByCodigo(Long codigo);

	int create(Respuesta respuesta);

	int update(Respuesta respuesta);

	int delete(int codigo);
	
	//RESULTADOS ENCUESTADOS
	
	List<Resultado> obtenerResultados(int cuestionario);
	
	//REPORTE AGRUPADO TEXTO Y OPCIONES DE RESPUESTA
	
	List<Pregunta> obtenerPreguntasOpcionesCuestionario(int cuestionario);
		
	List<Pregunta> obtenerPreguntasTextoCuestionario(int cuestionario);
		
	List<ReporteAgrupado> generarDatosReporteAgrupadoTexto(int cuestionario, String preguntas);
		
	List<ReporteAgrupado> generarDatosReporteAgrupadoOpciones(int cuestionario, String preguntas);
	
	//GENERAL ACREDITACION

	ByteArrayInputStream generarReporte(String titulo, int uaa) throws Exception;

	ByteArrayInputStream generarReportePorUsuario(int tus, String titulo, int uaa) throws Exception;

	ByteArrayInputStream generarReportePorCuestionario(int cue, String titulo) throws Exception;
	
	ByteArrayInputStream generarDatosReportePrograma(int programa, String titulo, int uaa) throws Exception;
	
	//DETALLADO
	
	ByteArrayInputStream generarReporteDetallado(String titulo, int uaa) throws Exception;

	ByteArrayInputStream generarReportePorUsuarioDetallado(int tus, String titulo, int uaa) throws Exception;

	ByteArrayInputStream generarReportePorCuestionarioDetallado(int cue, String titulo) throws Exception;
	
	ByteArrayInputStream generarDatosReporteProgramaDetallado(int programa, String titulo, int uaa) throws Exception;

	List<DatosGraficaPreguntaPrincipal> datosGrafica(int cuestionario);

}