package com.usco.edu.entities;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PreguntaCuestionario implements Serializable {

	private Long codigo;

	private int estado;

	private Pregunta pregunta;

	private String descripcion;

	private Cuestionario cuestionario;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
