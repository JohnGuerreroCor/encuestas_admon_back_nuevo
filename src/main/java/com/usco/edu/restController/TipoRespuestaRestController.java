package com.usco.edu.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usco.edu.entities.TipoRespuestas;
import com.usco.edu.service.ITipoRespuestaService;

@RestController
@RequestMapping(path = "api/tipo-respuesta")
public class TipoRespuestaRestController {

	@Autowired
	private ITipoRespuestaService service;

	@GetMapping(path = "find")
	public List<TipoRespuestas> find() {

		return service.find();

	}

	@GetMapping(path = "find-codigo/{codigo}")
	public TipoRespuestas findbyCodigo(@PathVariable int codigo) {

		return service.findByCodigo(codigo);

	}

}
