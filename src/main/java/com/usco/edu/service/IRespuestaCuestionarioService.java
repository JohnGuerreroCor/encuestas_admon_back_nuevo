package com.usco.edu.service;

import java.util.List;

import com.usco.edu.entities.RespuestaCuestionario;

public interface IRespuestaCuestionarioService {

	List<RespuestaCuestionario> find();

	RespuestaCuestionario findByCodigo(Long codigo);

	int create(RespuestaCuestionario respuestaCuestionario);

	int update(RespuestaCuestionario respuestaCuestionario);

	int delete(int codigo);

}
