package com.usco.edu.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {

	private int id;

	private String nombre_rol;

	@Override
	public String toString() {
		return "Role [id=" + id + ", nombre_rol=" + nombre_rol + "]";
	}

	private static final long serialVersionUID = 1L;

}
