package com.usco.edu.entities;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TipoRespuestas implements Serializable {

	private Long codigo;

	private String nombre;

	private int estado;

	private static final long serialVersionUID = 1L;

}
