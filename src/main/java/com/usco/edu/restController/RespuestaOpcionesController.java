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

import com.usco.edu.entities.RespuestaOpciones;
import com.usco.edu.service.IRespuestaOpcionesService;

@RestController
@RequestMapping(path = "api/respuesta-opciones")
public class RespuestaOpcionesController {

	@Autowired
	private IRespuestaOpcionesService service;

	@GetMapping(path = "find/{uaa}")
	public List<RespuestaOpciones> find(@PathVariable int uaa) {

		return service.find(uaa);
	}

	@GetMapping(path = "find-codigo/{codigo}")
	public RespuestaOpciones findByCodigo(@PathVariable int codigo) {

		return service.findByCode(codigo);

	}

	@PostMapping(path = "create/{user}")
	public void create(@RequestBody RespuestaOpciones respuestaOpciones, @PathVariable String user) {

		service.create(respuestaOpciones, user);

	}

	@PutMapping(path = "update/{user}")
	public void update(@RequestBody RespuestaOpciones respuestaOpciones, @PathVariable String user) {

		service.update(respuestaOpciones, user);

	}

	@GetMapping(path = "remove/{codigo}/{user}")
	public void remove(@PathVariable int codigo, @PathVariable String user) {
		service.remove(codigo, user);

	}

}
