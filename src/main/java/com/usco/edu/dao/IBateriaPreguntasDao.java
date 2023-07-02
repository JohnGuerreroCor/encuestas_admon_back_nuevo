package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.BateriaPreguntas;

public interface IBateriaPreguntasDao {

	List<BateriaPreguntas> find();

	BateriaPreguntas findByCodigo(Long codigo);

	void create(BateriaPreguntas bp);

	void update(BateriaPreguntas bp);

	void delete(Long codigo);
}
