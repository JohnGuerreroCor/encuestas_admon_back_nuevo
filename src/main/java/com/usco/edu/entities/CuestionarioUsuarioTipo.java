package com.usco.edu.entities;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CuestionarioUsuarioTipo implements Serializable {

	private Long codigo;

	private int estado;

	private Date fechaRegistro;

	private UsuarioTipo usuarioTipo;

	private Cuestionario cuestionario;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
