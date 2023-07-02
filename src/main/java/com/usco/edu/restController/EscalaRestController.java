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

import com.usco.edu.entities.Escala;
import com.usco.edu.service.IEscalaService;

@RestController
@RequestMapping(path = "api/escala")
public class EscalaRestController {

	@Autowired
	private IEscalaService service;

	@GetMapping("find/{codigo}")
	public List<Escala> find(@PathVariable int codigo) {

		return service.find(codigo);
	}

	@PostMapping(path = "create/{user}")
	public int create(@RequestBody Escala escala,@PathVariable String user) {

		return service.insert(escala,user);

	}

	@PutMapping(path = "update/{user}")
	public int update(@RequestBody Escala escala,@PathVariable String user) {

		return service.update(escala,user);

	}

	@GetMapping("remove/{codigo}/{user}")
	public int remove(@PathVariable int codigo,@PathVariable String user) {

		return service.delete(codigo,user);

	}

}
