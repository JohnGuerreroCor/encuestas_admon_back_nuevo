package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.PreguntaRespuestas;

public interface IPreguntaRespuestasDao {

	List<PreguntaRespuestas> find();

	PreguntaRespuestas findByCodigo(Long codigo);

	void create(PreguntaRespuestas preguntaRespuestas, String userdb);

	void update(PreguntaRespuestas preguntaRespuestas, String userdb);

	void delete(int codigo, String userdb);

	List<PreguntaRespuestas> findByCuestionario(Long codigo);

	void updateAgg(PreguntaRespuestas preguntaRespuestas, String userdb);

	List<PreguntaRespuestas> findbyPregunta(int codigo);
	

}
