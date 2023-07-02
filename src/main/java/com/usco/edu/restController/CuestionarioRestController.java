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

import com.usco.edu.entities.Cuestionario;
import com.usco.edu.service.ICuestionarioService;

@RestController
@RequestMapping(path = "api/cuestionario")
public class CuestionarioRestController {

	@Autowired
	private ICuestionarioService service;

	@GetMapping(path = "find/{uaa}")
	public List<Cuestionario> find(@PathVariable int uaa) {

		return service.find(uaa);

	}

	@GetMapping(path = "find-codigo/{codigo}")
	public Cuestionario findByCodigo(@PathVariable Long codigo) {

		return service.findByCodigo(codigo);

	}

	@PostMapping(path = "create/{user}")
	public int create(@RequestBody Cuestionario cuestionario, @PathVariable String user) {

		return service.create(cuestionario, user);

	}

	@PutMapping(path = "update/{user}")
	public int update(@RequestBody Cuestionario cuestionario, @PathVariable String user) {

		return service.update(cuestionario, user);
	}

	@GetMapping(path = "remove/{codigo}/{user}")
	public int delete(@PathVariable int codigo, @PathVariable String user) {

		return service.delete(codigo, user);
	}

}
