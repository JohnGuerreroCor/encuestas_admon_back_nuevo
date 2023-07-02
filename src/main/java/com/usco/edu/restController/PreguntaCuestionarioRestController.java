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

import com.usco.edu.entities.PreguntaCuestionario;
import com.usco.edu.service.IPreguntaCuestionarioService;

@RestController
@RequestMapping(path = "api/pregunta-cuestionario")
public class PreguntaCuestionarioRestController {

	@Autowired
	private IPreguntaCuestionarioService service;

	@GetMapping(path = "find")
	public List<PreguntaCuestionario> find() {

		return service.find();
	}

	@GetMapping(path = "find-codigo/{codigo}")
	public PreguntaCuestionario findByCodigo(@PathVariable Long codigo) {

		return service.findByCodigo(codigo);

	}

	@PostMapping(path = "create")
	public void create(@RequestBody PreguntaCuestionario preguntaCuestionario) {

		service.create(preguntaCuestionario);

	}

	@PutMapping(path = "update")
	public void update(@RequestBody PreguntaCuestionario preguntaCuestionario) {

		service.update(preguntaCuestionario);

	}

}
