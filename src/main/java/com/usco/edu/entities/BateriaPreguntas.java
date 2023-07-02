package com.usco.edu.entities;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BateriaPreguntas implements Serializable {

	private long codigo;

	private int estado;

	private String descripcion;

	private Pregunta pregunta;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
