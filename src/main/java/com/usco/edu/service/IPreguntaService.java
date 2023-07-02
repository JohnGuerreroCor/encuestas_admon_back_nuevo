package com.usco.edu.service;

import java.util.List;

import com.usco.edu.entities.Pregunta;

public interface IPreguntaService {

	List<Pregunta> find();

	List<Pregunta> findCues(int cues);

	Pregunta findByCodigo(Long codigo);

	int create(Pregunta pregunta, String userdb);

	int update(Pregunta pregunta, String userdb);

	int delete(int codigo, String userdb);

	List<Pregunta> findByDependencia(int cues);

	List<Pregunta> findByCue2(int cues);

	List<Pregunta> findByCueAdmin(int cues);

	List<Pregunta> findByCuestAndTipoRespuestaRadiobOrSelect(int cues);

}
