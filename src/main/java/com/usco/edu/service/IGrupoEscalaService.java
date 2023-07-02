package com.usco.edu.service;

import java.util.List;

import com.usco.edu.entities.GrupoEscala;

public interface IGrupoEscalaService {

	List<GrupoEscala> find();

	int insert(GrupoEscala gre, String userdb);

	int update(GrupoEscala gre, String userdb);

	int delete(int codigo, String userdb);
}
