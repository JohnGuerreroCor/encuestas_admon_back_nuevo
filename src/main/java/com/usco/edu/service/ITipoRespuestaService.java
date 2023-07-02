package com.usco.edu.service;

import java.util.List;

import com.usco.edu.entities.TipoRespuestas;

public interface ITipoRespuestaService {

	List<TipoRespuestas> find();

	TipoRespuestas findByCodigo(int codigo);

}
