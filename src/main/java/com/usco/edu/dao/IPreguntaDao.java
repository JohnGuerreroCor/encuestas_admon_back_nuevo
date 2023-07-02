package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.Pregunta;

public interface IPreguntaDao {

	List<Pregunta> find();

	Pregunta findByCodigo(Long long1);

	List<Pregunta> findByCue(int cues);

	void create(Pregunta pregunta, String userdb);

	void update(Pregunta pregunta, String userdb);

	void delete(int codigo, String userdb);

	List<Pregunta> findByDependencia(int cues);

	List<Pregunta> findByCue2(int cues);

	List<Pregunta> findByCueAdmin(int cues);

	List<Pregunta> findByCuestionarioTipoPrincipal(int cuest);

	List<Pregunta> findByCueandSelectOrRadioB(int cues);

}
