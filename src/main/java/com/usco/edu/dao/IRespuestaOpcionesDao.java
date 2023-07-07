package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.RespuestaOpciones;

public interface IRespuestaOpcionesDao {

	List<RespuestaOpciones> find(int uaa);

	RespuestaOpciones findByCode(int codigo);

	void create(RespuestaOpciones respuestaOpciones, String userdb);

	void update(RespuestaOpciones respuestaOpciones, String userdb);

	void remove(int codigo, String userdb);

	List<RespuestaOpciones> findByPregunta(int pregunta);

}
