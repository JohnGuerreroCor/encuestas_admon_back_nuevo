package com.usco.edu.service;

import java.util.List;

import com.usco.edu.entities.PreguntaCuestionario;

public interface IPreguntaCuestionarioService {

	List<PreguntaCuestionario> find();

	PreguntaCuestionario findByCodigo(Long codigo);

	int create(PreguntaCuestionario preguntaCuestionario);

	int update(PreguntaCuestionario preguntaCuestionario);

}
