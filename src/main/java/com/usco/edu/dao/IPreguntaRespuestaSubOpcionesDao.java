package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.PRespuestaSubOpciones;

public interface IPreguntaRespuestaSubOpcionesDao {

	List<PRespuestaSubOpciones> find();

	PRespuestaSubOpciones findByCodigo(Long codigo);

	void create(PRespuestaSubOpciones pRespuestaSubOpciones);

	void update(PRespuestaSubOpciones pRespuestaSubOpciones);

	void delete(int codigo);

}
