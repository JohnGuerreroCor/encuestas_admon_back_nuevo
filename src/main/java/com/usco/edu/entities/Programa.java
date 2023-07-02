package com.usco.edu.entities;
import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class Programa implements Serializable {
	
	private Long codigo;

	private Uaa uaa;
	
	private String registroSnies;
	
	private Sede sede;



	public Programa(Long codigo, Uaa uaa, String registroSnies, Sede sede) {
		this.codigo = codigo;
		this.uaa = uaa;
		this.registroSnies = registroSnies;
		this.sede = sede;
	}

	@Override
	public String toString() {
		return "Programa [codigo=" + codigo + ", uaa=" + uaa  + ", registroSnies=" + registroSnies + ", sede=" + sede + "]";
	}

	private static final long serialVersionUID = 1L;
}
