package com.usco.edu.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usco.edu.entities.Uaa;
import com.usco.edu.entities.UaaTipo;
import com.usco.edu.entities.UsuarioTipo;
import com.usco.edu.entities.Vinculo;
import com.usco.edu.service.IUaaService;

@RestController
@RequestMapping(path = "api/uaa")
public class UaaRestController {

	@Autowired
	private IUaaService service;

	@GetMapping(path = "find/{codigo}")
	public List<Uaa> find(@PathVariable int codigo) {
		return service.find(codigo);

	}

	@GetMapping(path = "find-tipo")
	public List<UaaTipo> findTipo() {

		return service.findUaaTipo();
	}

	@GetMapping(path = "find-codigo/{codigo}")
	public Uaa findByCodigo(@PathVariable int codigo) {

		return service.findByCodigo(codigo);
	}

	@GetMapping("find-tus")
	public List<UsuarioTipo> findTus() {

		return service.findTus();
	}

	@GetMapping("find-uat")
	public List<UaaTipo> findUaaTipoWeb() {

		return service.findUaaTipoWeb();
	}

	@GetMapping("find-vin")
	public List<Vinculo> findVin() {
		return service.findVin();
	}

}
