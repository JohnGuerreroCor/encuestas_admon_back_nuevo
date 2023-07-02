package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.GrupoEscala;

public interface IGrupoEscalaDao {

	List<GrupoEscala> find();

	void insert(GrupoEscala gre, String userdb);

	void update(GrupoEscala gre, String userdb);

	void delete(int codigo, String userdb);

}
