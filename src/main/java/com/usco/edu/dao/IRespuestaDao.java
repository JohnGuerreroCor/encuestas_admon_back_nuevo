package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.Respuesta;

public interface IRespuestaDao {

	List<Respuesta> find();

	Respuesta findByCodigo(Long codigo);

	void create(Respuesta respuesta);

	void update(Respuesta respuesta);

	void delete(int codigo);

}
