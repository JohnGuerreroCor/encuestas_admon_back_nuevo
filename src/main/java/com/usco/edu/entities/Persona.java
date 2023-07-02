package com.usco.edu.entities;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Persona implements Serializable {

	private Long codigo;

	private String nombre;

	private String apellido;

	private String identificacion;
	
	private String expedicion;
	
	private String tipoDocumento;
	
	private String edad;
	
	private static final long serialVersionUID = 1L;
}
