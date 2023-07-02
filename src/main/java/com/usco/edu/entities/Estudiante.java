package com.usco.edu.entities;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Estudiante implements Serializable  {
	
	private String codigo;

	private String perCodigo;

	private String fechaIngreso;

	private static final long serialVersionUID = 1L;

}
