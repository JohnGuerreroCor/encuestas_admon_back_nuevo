package com.usco.edu.entities;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PRespuestaSubOpciones implements Serializable {

	private Long codigo;

	private String descripcion;

	private PreguntaRespuestas preguntaRespuesta;

	private int estado;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
