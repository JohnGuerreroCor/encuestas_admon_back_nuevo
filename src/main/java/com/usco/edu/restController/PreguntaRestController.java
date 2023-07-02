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

import com.usco.edu.entities.Pregunta;
import com.usco.edu.exception.ModelNotFoundException;
import com.usco.edu.service.IPreguntaService;

@RestController
@RequestMapping(path = "api/pregunta")
public class PreguntaRestController {

	@Autowired
	private IPreguntaService service;

	@GetMapping(path = "find")
	public List<Pregunta> find() {

		return service.find();

	}

	@GetMapping(path = "find-codigo/{codigo}")
	public Pregunta findByCodigo(@PathVariable Long codigo) throws Exception {

		Pregunta pregunta = service.findByCodigo(codigo);
		if (pregunta == null) {
			throw new ModelNotFoundException("PREGUNTA NO ENCONTRADA");
		}

		return pregunta;

	}

	@PostMapping(path = "create/{user}")
	public int create(@RequestBody Pregunta pregunta, @PathVariable String user) {

		return service.create(pregunta, user);

	}

	@PutMapping(path = "update/{user}")
	public int update(@RequestBody Pregunta pregunta, @PathVariable String user) {

		return service.update(pregunta, user);
	}

	@GetMapping("remove/{codigo}/{user}")
	public int remove(@PathVariable int codigo, @PathVariable String user) {

		return service.delete(codigo, user);
	}

	@GetMapping("find-cues/{cues}")
	public List<Pregunta> findCuest(@PathVariable int cues) {

		return service.findCues(cues);
	}

	@GetMapping("find-dependencia/{codigo}")
	public List<Pregunta> findByDependencia(@PathVariable int codigo) {
		return service.findByDependencia(codigo);
	}

	@GetMapping("find-cuest/{cues}")
	public List<Pregunta> findCuest2(@PathVariable int cues) {

		return service.findByCue2(cues);
	}

	@GetMapping("find-admin/{cues}")
	public List<Pregunta> findCuesAdmin(@PathVariable int cues) {

		return service.findByCueAdmin(cues);
	}

	@GetMapping("pre-relacion/{cues}")
	public List<Pregunta> findPreguntasForRelacion(@PathVariable int cues) {

		return service.findByCuestAndTipoRespuestaRadiobOrSelect(cues);
	}
}
