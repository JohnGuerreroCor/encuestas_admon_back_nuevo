package com.usco.edu.entities;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PreguntaRespuestas implements Serializable {

	private Long codigo;

	private int estado;

	private String descripcionAdicional;

	private RespuestaOpciones respuestaOpciones;

	private Pregunta pregunta;

	private TipoRespuestas tipoRespuestas;

	private int depende;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
