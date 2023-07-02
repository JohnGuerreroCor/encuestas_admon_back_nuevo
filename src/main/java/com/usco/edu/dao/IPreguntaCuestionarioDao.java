package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.PreguntaCuestionario;

public interface IPreguntaCuestionarioDao {

	List<PreguntaCuestionario> find();

	PreguntaCuestionario findByCodigo(Long codigo);

	void create(PreguntaCuestionario preguntaCuestionario);

	void update(PreguntaCuestionario preguntaCuestionario);

}
