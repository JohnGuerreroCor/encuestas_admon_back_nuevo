package com.usco.edu.entities;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UaaTipo implements Serializable {
	private Long codigo;

	private String nombre;

	public UaaTipo(Long codigo, String nombre) {
		this.codigo = codigo;
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "UaaTipo [codigo=" + codigo + ", nombre=" + nombre + "]";
	}

	private static final long serialVersionUID = 1L;
}