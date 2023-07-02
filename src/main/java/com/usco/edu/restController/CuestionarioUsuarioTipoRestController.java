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

import com.usco.edu.entities.CuestionarioUsuarioTipo;
import com.usco.edu.service.ICuestionarioUsuarioTipoService;

@RestController
@RequestMapping(path = "api/cuestionario-tus")
public class CuestionarioUsuarioTipoRestController {

	@Autowired
	private ICuestionarioUsuarioTipoService service;

	@GetMapping(path = "find")
	public List<CuestionarioUsuarioTipo> find() {

		return service.find();

	}

	@GetMapping(path = "find-codigo/{codigo}")
	public CuestionarioUsuarioTipo findByCodigo(@PathVariable Long codigo) {

		
		
		
		return service.findByCodigo(codigo);

	}

	@PostMapping(path = "create")
	public int create(@RequestBody CuestionarioUsuarioTipo cuestionarioUsuarioTipo) {

		return service.create(cuestionarioUsuarioTipo);

	}

	@PutMapping(path = "update")
	public int update(@RequestBody CuestionarioUsuarioTipo cuestionarioUsuarioTipo) {

		return service.update(cuestionarioUsuarioTipo);
	}

	@GetMapping("remove/{codigo}")
	public int remove(@PathVariable int codigo) {
		return service.delete(codigo);

	}

}
