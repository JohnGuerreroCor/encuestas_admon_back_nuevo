package com.usco.edu.entities;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RespuestaCuestionario implements Serializable {

	private Long codigo;

	private Cuestionario cuestionario;

	private Persona persona;

	private int estado;

	private Date fecha;

	private String vinculacion;

	private int estamento;

	private int calendario;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
