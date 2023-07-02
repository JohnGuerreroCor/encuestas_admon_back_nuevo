package com.usco.edu.dto;

import java.io.Serializable;

import com.usco.edu.entities.Cuestionario;
import com.usco.edu.entities.Pregunta;
import com.usco.edu.entities.RespuestaOpciones;
import com.usco.edu.entities.UsuarioTipo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReporteRespuesta implements Serializable {

	private RespuestaOpciones rop;
	private Pregunta pregunta;
	private UsuarioTipo tus;
	private Cuestionario cue;

	private static final long serialVersionUID = 1L;

}
