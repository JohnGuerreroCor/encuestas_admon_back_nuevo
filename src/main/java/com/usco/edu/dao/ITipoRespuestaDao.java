package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.TipoRespuestas;

public interface ITipoRespuestaDao {

	List<TipoRespuestas> find();
	
	TipoRespuestas findByCodigo(int codigo);
	
}
