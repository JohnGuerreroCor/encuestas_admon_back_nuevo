package com.usco.edu.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usco.edu.entities.PreguntaRespuestas;
import com.usco.edu.service.IPreguntaRespuestaService;

@RestController
@RequestMapping(path = "api/pregunta-respuesta")
public class PreguntaRespuestasRestController {

	@Autowired
	private IPreguntaRespuestaService service;

	@GetMapping(path = "find")
	public List<PreguntaRespuestas> find() {

		return service.find();

	}

	@GetMapping(path = "find-cue/{codigo}")
	public List<PreguntaRespuestas> findBydCuestionario(@PathVariable Long codigo) {

		return service.findByCuestionario(codigo);
	}

	@GetMapping(path = "find-codigo/{codigo}")
	public PreguntaRespuestas findByCodigo(@PathVariable Long codigo) {

		return service.findByCodigo(codigo);

	}

	@PostMapping(path = "create/{user}")
	public int create(@RequestBody PreguntaRespuestas preguntaRespuestas, @PathVariable String user) {

		return service.create(preguntaRespuestas, user);

	}

	@PutMapping(path = "update/{user}")
	public int update(@RequestBody PreguntaRespuestas preguntaRespuestas, @PathVariable String user) {

		return service.update(preguntaRespuestas, user);
	}

	@GetMapping(path = "remove/{codigo}/{user}")
	public int remove(@PathVariable int codigo, @PathVariable String user) {
		return service.delete(codigo, user);

	}

	@GetMapping(path = "find-pregunta/{codigo}")
	public List<PreguntaRespuestas> findByPregunta(@PathVariable int codigo) {
		return service.findByPregunta(codigo);

	}

}
