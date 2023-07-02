package com.usco.edu.dto;

import java.io.Serializable;

import com.usco.edu.entities.Pregunta;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DatosGraficaPreguntaPrincipal implements Serializable {

	private String[] opciones;
	private int[] valores;
	private Pregunta pregunta;
	private int[] val;

	private static final long serialVersionUID = 1L;

}
