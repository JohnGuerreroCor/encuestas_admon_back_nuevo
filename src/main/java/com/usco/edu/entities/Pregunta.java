package com.usco.edu.entities;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Pregunta implements Serializable {

	private Long codigo;

	private String descripcion;

	private int estado;

	private String textoAdicional;

	private int tipo;

	private TipoRespuestas tipoRespuesta;

	private Cuestionario cuestionario;

	private int obligatorio;

	private GrupoEscala gre;

	private int depende;

	private String identificador;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
