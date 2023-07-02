package com.usco.edu.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioTipo implements Serializable {

	private Long codigo;

	private String nombre;

	@Override
	public String toString() {
		return "UaaTipo [codigo=" + codigo + ", nombre=" + nombre + "]";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
