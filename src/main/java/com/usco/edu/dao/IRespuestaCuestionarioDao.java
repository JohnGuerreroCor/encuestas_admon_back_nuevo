package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.RespuestaCuestionario;

public interface IRespuestaCuestionarioDao {
	
	List<RespuestaCuestionario> find();

	RespuestaCuestionario findByCodigo(Long codigo);

	void create(RespuestaCuestionario respuestaCuestionario);

	void update(RespuestaCuestionario respuestaCuestionario);

	void delete(int codigo);


}
