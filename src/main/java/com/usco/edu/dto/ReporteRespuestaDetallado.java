package com.usco.edu.dto;

import java.io.Serializable;

import com.usco.edu.entities.Cuestionario;
import com.usco.edu.entities.Pregunta;
import com.usco.edu.entities.RespuestaOpciones;
import com.usco.edu.entities.UsuarioTipo;
import com.usco.edu.entities.Persona;
import com.usco.edu.entities.Estudiante;
import com.usco.edu.entities.Uaa;
import com.usco.edu.entities.Sede;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReporteRespuestaDetallado implements Serializable {
	
	private RespuestaOpciones rop;
	private Pregunta pregunta;
	private UsuarioTipo tus;
	private Cuestionario cue;
	private Persona per;
	private Estudiante est;
	private Uaa uaa;
	private Sede sede;

	private static final long serialVersionUID = 1L;

}
