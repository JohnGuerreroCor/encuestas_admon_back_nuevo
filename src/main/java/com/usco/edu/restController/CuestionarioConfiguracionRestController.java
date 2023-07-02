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

import com.usco.edu.entities.CuestionarioConfiguracion;
import com.usco.edu.service.ICuestionarioConfiguracionService;

@RestController
@RequestMapping(path = "api/cuestionario-c")
public class CuestionarioConfiguracionRestController {

	@Autowired
	private ICuestionarioConfiguracionService service;

	@GetMapping(path = "find/{uaa}")
	public List<CuestionarioConfiguracion> find(@PathVariable int uaa) {

		return service.find(uaa);

	}

	@GetMapping(path = "find-codigo/{codigo}")
	public List<CuestionarioConfiguracion> findByCodigo(@PathVariable Long codigo) {

		return service.findByCodigo(codigo);

	}

	@PostMapping(path = "create/{user}")
	public int create(@RequestBody CuestionarioConfiguracion cuestionarioConfiguracion, @PathVariable String user) {

		return service.create(cuestionarioConfiguracion, user);

	}

	@PutMapping(path = "update/{user}")
	public int update(@RequestBody CuestionarioConfiguracion cuestionarioConfiguracion, @PathVariable String user) {

		return service.update(cuestionarioConfiguracion, user);
	}

	@GetMapping(path = "remove/{codigo}/{user}")
	public int remove(@PathVariable int codigo, @PathVariable String user) {

		return service.delete(codigo, user);
	}

}
