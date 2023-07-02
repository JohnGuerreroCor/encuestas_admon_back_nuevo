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

import com.usco.edu.entities.BateriaPreguntas;
import com.usco.edu.service.IBateriaPreguntasService;

@RestController
@RequestMapping(path = "api/bateria-preguntas")
public class BateriaPreguntasRestController {

	@Autowired
	private IBateriaPreguntasService service;

	@GetMapping(path = "find")
	public List<BateriaPreguntas> find() {

		return service.find();
	}

	@GetMapping(path = "find-codigo/{codigo}")
	public BateriaPreguntas findByCodigo(@PathVariable Long codigo) {

		return service.findByCodigo(codigo);
	}

	@PostMapping(path = "create")
	public int create(@RequestBody BateriaPreguntas bp) {

		return service.create(bp);

	}

	@PutMapping(path = "update")
	public int update(@RequestBody BateriaPreguntas bp) {

		return service.update(bp);
	}

}
