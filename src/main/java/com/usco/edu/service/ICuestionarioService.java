package com.usco.edu.service;

import java.util.List;

import com.usco.edu.entities.Cuestionario;

public interface ICuestionarioService {

	List<Cuestionario> find(int uaa);

	Cuestionario findByCodigo(Long codigo);

	int create(Cuestionario cuestionario, String userdb);

	int update(Cuestionario cuestionario, String userdb);

	int delete(int codigo, String userdb);

}
