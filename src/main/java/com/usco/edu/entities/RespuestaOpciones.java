package com.usco.edu.entities;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RespuestaOpciones implements Serializable {

	private Long codigo;

	private String descripcion;
	
	private int estado;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
