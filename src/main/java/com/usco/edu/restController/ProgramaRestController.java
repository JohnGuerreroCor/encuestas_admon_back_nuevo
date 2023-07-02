package com.usco.edu.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RestController;

import com.usco.edu.service.IProgramaService;
import com.usco.edu.entities.Programa;

//@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
public class ProgramaRestController {
	
	@Autowired
	IProgramaService programaService;
	
	@GetMapping("/programas/all/{userdb}")
	public List<Programa> programasAll(@PathVariable("userdb") String userdb) {

		List<Programa> programa = programaService.progromasAll(userdb);
		for (Programa pro : programa) {
			pro.setRegistroSnies(pro.getRegistroSnies());
		}
		return programa;
	}

}
