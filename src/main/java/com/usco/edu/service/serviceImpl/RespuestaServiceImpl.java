package com.usco.edu.service.serviceImpl;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.usco.edu.dao.IPreguntaDao;
import com.usco.edu.dao.IReporteRespuestasDao;
import com.usco.edu.dao.IRespuestaDao;
import com.usco.edu.dao.IRespuestaOpcionesDao;
import com.usco.edu.dto.DatosGraficaPreguntaPrincipal;
import com.usco.edu.dto.ReporteAgrupado;
import com.usco.edu.dto.ReporteRespuesta;
import com.usco.edu.dto.ReporteRespuestaDetallado;
import com.usco.edu.entities.Pregunta;
import com.usco.edu.entities.Respuesta;
import com.usco.edu.entities.RespuestaOpciones;
import com.usco.edu.entities.Resultado;
import com.usco.edu.service.IRespuestaService;
import com.usco.edu.util.GenerarReporteExcelPoi;
import com.usco.edu.util.GenerarReporteExcelDetallado;

@Service
public class RespuestaServiceImpl implements IRespuestaService {

	@Autowired
	private IRespuestaDao dao;

	@Autowired
	private GenerarReporteExcelPoi generarExcel;
	
	@Autowired
	private GenerarReporteExcelDetallado generarExcelDetallado;

	@Autowired
	private IReporteRespuestasDao reporteDao;

	@Autowired
	private IPreguntaDao preguntaDao;

	@Autowired
	private IRespuestaOpcionesDao ropDao;

	@Transactional(readOnly = true)
	@Override
	public List<Respuesta> find() {

		return dao.find();
	}

	@Override
	public Respuesta findByCodigo(Long codigo) {

		try {

			return dao.findByCodigo(codigo);

		} catch (Exception e) {

			return null;

		}

	}

	@Override
	public int create(Respuesta respuesta) {

		try {

			dao.create(respuesta);
			return 1;

		} catch (Exception e) {
			return 0;
		}

	}

	@Override
	public int update(Respuesta respuesta) {

		try {
			dao.update(respuesta);
			return 1;
		} catch (Exception e) {
			return 0;
		}

	}

	@Override
	public int delete(int codigo) {

		try {
			dao.delete(codigo);
			return 1;

		} catch (Exception e) {
			return 0;
		}

	}
	
	
	// GENERAL ACREDITACION
	
	ByteArrayInputStream generarExcel(List<ReporteRespuesta> reporteRespuesta, String titulo) throws Exception {

		return generarExcel.generarpdfRespuestas(reporteRespuesta, titulo);

	}

	@Override
	public ByteArrayInputStream generarReporte(String titulo, int uaa) throws Exception {

		return generarExcel(reporteDao.generarDatosReporteTodos(uaa), titulo);
	}

	@Override
	public ByteArrayInputStream generarReportePorUsuario(int tus, String titulo, int uaa) throws Exception {

		return generarExcel(reporteDao.generarDatosReportePorTipoUsuario(tus, uaa), titulo);

	}
	
	@Override
	public ByteArrayInputStream generarDatosReportePrograma(int programa, String titulo, int uaa) throws Exception {

		return generarExcel(reporteDao.generarDatosReportePrograma(programa, uaa), titulo);

	}

	@Override
	public ByteArrayInputStream generarReportePorCuestionario(int cue, String titulo) throws Exception {
		// TODO Auto-generated method stub
		return generarExcel(reporteDao.generarDatosReportePorCuestionario(cue), titulo);

	}
	
	
	// DETALLADO
	
	ByteArrayInputStream generarExcelDetallado(List<ReporteRespuestaDetallado> reporteRespuestaDetallado, String titulo) throws Exception {

		return generarExcelDetallado.generarExcelRespuestasDetallado(reporteRespuestaDetallado, titulo);

	}
	
	@Override
	public ByteArrayInputStream generarReporteDetallado(String titulo, int uaa) throws Exception {

		return generarExcelDetallado(reporteDao.generarDatosReporteTodosDetallado(uaa), titulo);
	}

	@Override
	public ByteArrayInputStream generarReportePorUsuarioDetallado(int tus, String titulo, int uaa) throws Exception {

		return generarExcelDetallado(reporteDao.generarDatosReportePorTipoUsuarioDetallado(tus, uaa), titulo);

	}
	
	@Override
	public ByteArrayInputStream generarDatosReporteProgramaDetallado(int programa, String titulo, int uaa) throws Exception {

		return generarExcelDetallado(reporteDao.generarDatosReporteProgramaDetallado(programa, uaa), titulo);

	}

	@Override
	public ByteArrayInputStream generarReportePorCuestionarioDetallado(int cue, String titulo) throws Exception {
		// TODO Auto-generated method stub
		return generarExcelDetallado(reporteDao.generarDatosReportePorCuestionarioDetallado(cue), titulo);

	}
	

	@Override
	public List<DatosGraficaPreguntaPrincipal> datosGrafica(int cuestionario) {

		List<Pregunta> preguntas = preguntaDao.findByCuestionarioTipoPrincipal(cuestionario);

		List<DatosGraficaPreguntaPrincipal> datosGrafica = new ArrayList<DatosGraficaPreguntaPrincipal>();

		for (Pregunta p : preguntas) {

			int codigoPregunta = Math.toIntExact(p.getCodigo());
			List<RespuestaOpciones> lstRop = ropDao.findByPregunta(codigoPregunta);
			String[] nombres = new String[lstRop.size()];
			int[] valores = new int[lstRop.size()];
			int[] val = new int[lstRop.size()];
			DatosGraficaPreguntaPrincipal datoGrafica = new DatosGraficaPreguntaPrincipal();

			int contador = 0;

			for (RespuestaOpciones rop : lstRop) {

				int codigoRop = Math.toIntExact(rop.getCodigo());
				nombres[contador] = rop.getDescripcion();
				valores[contador] = reporteDao.valoresGraficas(codigoPregunta, codigoRop);
				
				val[contador] = codigoRop;
				contador++;
			}
			
			datoGrafica.setPregunta(p);
			datoGrafica.setVal(val);
			datoGrafica.setOpciones(nombres);
			datoGrafica.setValores(valores);
			datosGrafica.add(datoGrafica);

		}

		return datosGrafica;

	}
	
	//REPORTE AGRUPADO TEXTO Y OPCIONES DE RESPUESTA

	@Override
	public List<Pregunta> obtenerPreguntasOpcionesCuestionario(int cuestionario) {
		
		return reporteDao.obtenerPreguntasOpcionesCuestionario(cuestionario);
		
	}

	@Override
	public List<Pregunta> obtenerPreguntasTextoCuestionario(int cuestionario) {
		
		return reporteDao.obtenerPreguntasTextoCuestionario(cuestionario);
		
	}

	@Override
	public List<ReporteAgrupado> generarDatosReporteAgrupadoTexto(int cuestionario, String preguntas) {
		
		return reporteDao.generarDatosReporteAgrupadoTexto(cuestionario, preguntas);
		
	}

	@Override
	public List<ReporteAgrupado> generarDatosReporteAgrupadoOpciones(int cuestionario, String preguntas) {
		
		//preguntas = preguntas.replace(",undefined", "");
		
		
		return reporteDao.generarDatosReporteAgrupadoOpciones(cuestionario, preguntas);
		
	}

	@Override
	public List<Resultado> obtenerResultados(int cuestionario) {
		
		return reporteDao.obtenerResultados(cuestionario);
		
	}
	
}