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

import com.usco.edu.entities.RespuestaCuestionario;
import com.usco.edu.service.IRespuestaCuestionarioService;

@RestController
@RequestMapping(path = "api/respuesta-cuestionario")
public class RespuestaCuestionarioController {

	@Autowired
	private IRespuestaCuestionarioService service;

	@GetMapping(path = "find")
	public List<RespuestaCuestionario> find() {

		return service.find();
	}

	@GetMapping(path = "find-codigo/{codigo}")
	public RespuestaCuestionario findByCodigo(@PathVariable Long codigo) {

		return service.findByCodigo(codigo);

	}

	@PostMapping(path = "create")
	public int create(@RequestBody RespuestaCuestionario respuestaCuestionario) {

		return service.create(respuestaCuestionario);

	}

	@PutMapping(path = "update")
	public int update(@RequestBody RespuestaCuestionario respuestaCuestionario) {

		return service.update(respuestaCuestionario);

	}

	@PutMapping(path = "remove/{codigo}")
	public int remove(@PathVariable int codigo) {

		return service.delete(codigo);

	}

}
