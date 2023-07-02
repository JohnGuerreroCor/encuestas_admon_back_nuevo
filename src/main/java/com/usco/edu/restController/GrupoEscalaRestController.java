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

import com.usco.edu.entities.GrupoEscala;
import com.usco.edu.service.IGrupoEscalaService;

@RestController
@RequestMapping(path = "api/grupo-escala")
public class GrupoEscalaRestController {

	@Autowired
	private IGrupoEscalaService service;

	@GetMapping("find")
	public List<GrupoEscala> find() {

		return service.find();
	}

	@PostMapping(path = "create/{user}")
	public int create(@RequestBody GrupoEscala grupoEscala, @PathVariable String user) {

		return service.insert(grupoEscala, user);

	}

	@PutMapping(path = "update/{user}")
	public int update(@RequestBody GrupoEscala grupoEscala, @PathVariable String user) {

		return service.update(grupoEscala, user);

	}

	@GetMapping("remove/{codigo}/{user}")
	public int remove(@PathVariable int codigo, @PathVariable String user) {

		return service.delete(codigo, user);

	}
}
