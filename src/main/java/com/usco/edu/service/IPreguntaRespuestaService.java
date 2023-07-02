package com.usco.edu.service;

import java.util.List;

import com.usco.edu.entities.PreguntaRespuestas;

public interface IPreguntaRespuestaService {

	List<PreguntaRespuestas> find();

	PreguntaRespuestas findByCodigo(Long codigo);

	int create(PreguntaRespuestas preguntaRespuestas, String userdb);

	int update(PreguntaRespuestas preguntaRespuestas, String userdb);

	int delete(int codigo, String userdb);

	List<PreguntaRespuestas> findByCuestionario(Long codigo);

	List<PreguntaRespuestas> findByPregunta(int codigo);

}
