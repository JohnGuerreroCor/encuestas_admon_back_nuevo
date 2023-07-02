package com.usco.edu.service;

import java.util.List;

import com.usco.edu.entities.Escala;

public interface IEscalaService {

	List<Escala> find(int codigo);

	int insert(Escala escala, String userdb);

	int update(Escala escala, String userdb);

	int delete(int codigo, String userdb);

}
