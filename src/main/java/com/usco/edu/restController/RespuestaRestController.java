package com.usco.edu.restController;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usco.edu.dto.DatosGraficaPreguntaPrincipal;
import com.usco.edu.dto.ReporteAgrupado;
import com.usco.edu.entities.Pregunta;
import com.usco.edu.entities.Respuesta;
import com.usco.edu.entities.Resultado;
import com.usco.edu.service.IRespuestaService;

@RestController
@RequestMapping(path = "api/respuesta")
public class RespuestaRestController {

	@Autowired
	private IRespuestaService service;

	@GetMapping(path = "find")
	public List<Respuesta> find() {

		return service.find();
	}

	@GetMapping(path = "find-codigo/{codigo}")
	public Respuesta findByCodigo(@PathVariable Long codigo) {

		return service.findByCodigo(codigo);

	}

	@PostMapping(path = "create")
	public int create(@RequestBody Respuesta respuesta) {

		return service.create(respuesta);

	}

	@PutMapping(path = "update")
	public int update(@RequestBody Respuesta respuesta) {

		return service.update(respuesta);

	}

	@PutMapping(path = "remove/{codigo}")
	public int remove(@PathVariable int codigo) {

		return service.delete(codigo);

	}
	
	// GENERAL ACREDITACION

	@GetMapping(path = "reporte/{uaa}/{titulo}")
	public ResponseEntity<InputStreamResource> ReporteExcelTodos(@PathVariable int uaa, @PathVariable String titulo)
			throws Exception {

		ByteArrayInputStream stream = service.generarReporte(titulo, uaa);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=\"" + titulo + ".xlsx\"");

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
	}
	
	@GetMapping(path = "reporte-p/{programa}/{uaa}/{titulo}")
	public ResponseEntity<InputStreamResource> ReporteExcelPrograma(@PathVariable int programa, @PathVariable int uaa, @PathVariable String titulo)
			throws Exception {

		ByteArrayInputStream stream = service.generarDatosReportePrograma(programa, titulo, uaa);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=\"" + titulo + ".xlsx\"");

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
	}

	@GetMapping(path = "reporte-c/{titulo}/{cue}")
	public ResponseEntity<InputStreamResource> ReporteExcelCuestionario(@PathVariable String titulo,
			@PathVariable int cue) throws Exception {

		ByteArrayInputStream stream = service.generarReportePorCuestionario(cue, titulo);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=\"" + titulo + ".xlsx\"");

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
	}

	@GetMapping(path = "reporte-tus/{uaa}/{titulo}/{tus}")
	public ResponseEntity<InputStreamResource> ReporteExcelUsuarioTipo(@PathVariable int uaa,
			@PathVariable String titulo, @PathVariable int tus) throws Exception {

		ByteArrayInputStream stream = service.generarReportePorUsuario(tus, titulo, uaa);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=\"" + titulo + ".xlsx\"");

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
	}
	
	
	// DETALLADO
	
	@GetMapping(path = "reporte-detallado/{uaa}/{titulo}")
	public ResponseEntity<InputStreamResource> ReporteExcelTodosDetallado(@PathVariable int uaa, @PathVariable String titulo)
			throws Exception {

		ByteArrayInputStream stream = service.generarReporteDetallado(titulo, uaa);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=\"" + titulo + ".xlsx\"");

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
	}
	
	@GetMapping(path = "reporte-p-detallado/{programa}/{uaa}/{titulo}")
	public ResponseEntity<InputStreamResource> ReporteExcelProgramaDetallado(@PathVariable int programa, @PathVariable int uaa, @PathVariable String titulo)
			throws Exception {

		ByteArrayInputStream stream = service.generarDatosReporteProgramaDetallado(programa, titulo, uaa);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=\"" + titulo + ".xlsx\"");

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
	}

	@GetMapping(path = "reporte-c-detallado/{titulo}/{cue}")
	public ResponseEntity<InputStreamResource> ReporteExcelCuestionarioDetallado(@PathVariable String titulo,
			@PathVariable int cue) throws Exception {

		ByteArrayInputStream stream = service.generarReportePorCuestionarioDetallado(cue, titulo);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=\"" + titulo + ".xlsx\"");

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
	}

	@GetMapping(path = "reporte-tus-detallado/{uaa}/{titulo}/{tus}")
	public ResponseEntity<InputStreamResource> ReporteExcelUsuarioTipoDetallado(@PathVariable int uaa,
			@PathVariable String titulo, @PathVariable int tus) throws Exception {

		ByteArrayInputStream stream = service.generarReportePorUsuarioDetallado(tus, titulo, uaa);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=\"" + titulo + ".xlsx\"");

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
	}

	@GetMapping("grafica-principal/{cue}")
	public List<DatosGraficaPreguntaPrincipal> generarDatosGrafica(@PathVariable int cue) {

		return service.datosGrafica(cue);
	}
	
	
	//REPORTE AGRUPADO TEXTO Y OPCIONES DE RESPUESTA
	
	@GetMapping(path = "obtener-preguntas-opciones/{cuestionario}")
	public List<Pregunta> obtenerPreguntasOpcionesCuestionario(@PathVariable int cuestionario) {

		return service.obtenerPreguntasOpcionesCuestionario(cuestionario);

	}
	
	@GetMapping(path = "obtener-preguntas-texto/{cuestionario}")
	public List<Pregunta> obtenerPreguntasTextoCuestionario(@PathVariable int cuestionario) {

		return service.obtenerPreguntasTextoCuestionario(cuestionario);

	}
	
	@GetMapping(path = "generar-reporte-agrupado-texto/{cuestionario}/{preguntas}")
	public List<ReporteAgrupado> generarDatosReporteAgrupadoTexto(@PathVariable int cuestionario, @PathVariable String preguntas) {

		return service.generarDatosReporteAgrupadoTexto(cuestionario, preguntas);

	}
	
	@GetMapping(path = "generar-reporte-agrupado-opciones/{cuestionario}/{preguntas}")
	public List<ReporteAgrupado> generarDatosReporteAgrupadoOpciones(@PathVariable int cuestionario, @PathVariable String preguntas) {

		return service.generarDatosReporteAgrupadoOpciones(cuestionario, preguntas);

	}
	
	@GetMapping(path = "obtener-resultados/{cuestionario}")
	public List<Resultado> obtenerResultados(@PathVariable int cuestionario) {

		return service.obtenerResultados(cuestionario);

	}

}
