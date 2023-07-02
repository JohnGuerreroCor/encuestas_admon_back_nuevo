package com.usco.edu.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Respuesta {

	private Long codigo;

	private String adicional;

	private int estado;

	private String texto;

	private RespuestaCuestionario respuestaCuestionario;

	private PreguntaRespuestas preguntaRespuesta;

	private Pregunta pregunta;

}
