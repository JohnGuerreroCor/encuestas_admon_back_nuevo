package com.usco.edu.service;

import java.util.List;

import com.usco.edu.entities.PRespuestaSubOpciones;

public interface IPreguntaRespuestaSubOpcionesService {

	List<PRespuestaSubOpciones> find();

	PRespuestaSubOpciones findByCodigo(Long codigo);

	int create(PRespuestaSubOpciones pRespuestaSubOpciones);

	int update(PRespuestaSubOpciones pRespuestaSubOpciones);

	int delete(int codigo);

}
