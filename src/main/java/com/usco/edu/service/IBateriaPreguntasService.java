package com.usco.edu.service;

import java.util.List;

import com.usco.edu.entities.BateriaPreguntas;

public interface IBateriaPreguntasService {

	List<BateriaPreguntas> find();

	BateriaPreguntas findByCodigo(Long codigo);

	int create(BateriaPreguntas bp);

	int update(BateriaPreguntas bp);

	int delete(Long codigo);
}
