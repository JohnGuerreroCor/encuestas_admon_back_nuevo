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

import com.usco.edu.entities.PRespuestaSubOpciones;
import com.usco.edu.service.IPreguntaRespuestaSubOpcionesService;

@RestController
@RequestMapping(path = "api/pregunta-sub")
public class PreguntaRespuestaSubOpcionesRestController {

	@Autowired
	private IPreguntaRespuestaSubOpcionesService service;

	@GetMapping(path = "find")
	public List<PRespuestaSubOpciones> find() {

		return service.find();
	}

	@GetMapping(path = "find-codigo/{codigo}")
	public PRespuestaSubOpciones findByCodigo(@PathVariable Long codigo) {

		return service.findByCodigo(codigo);

	}

	@PostMapping(path = "create")
	public int create(@RequestBody PRespuestaSubOpciones pRespuestaSubOpciones) {

		return service.create(pRespuestaSubOpciones);

	}

	@PutMapping(path = "update")
	public int update(@RequestBody PRespuestaSubOpciones pRespuestaSubOpciones) {

		return service.update(pRespuestaSubOpciones);

	}

	@PutMapping(path = "remove/{codigo}")
	public int remove(@PathVariable int codigo) {

		return service.delete(codigo);

	}

}
