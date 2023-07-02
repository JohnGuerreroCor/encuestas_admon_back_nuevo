package com.usco.edu.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespuestaToken implements Serializable {

	private boolean estado;
	private String mensaje;
	private String consola;

	private static final long serialVersionUID = 1L;

}

