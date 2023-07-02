package com.usco.edu.entities;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CuestionarioConfiguracion implements Serializable {

	private Long codigo;

	private int estado;

	private Date fechaRegistro;

	private Uaa uaa;

	private Cuestionario cuestionario;

	private UsuarioTipo usuarioTipo;

	private Vinculo vinculo;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
