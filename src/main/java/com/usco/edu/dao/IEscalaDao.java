package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.Escala;

public interface IEscalaDao {

	List<Escala> find(int codigo);

	void insert(Escala escala, String userdb);

	void update(Escala escala, String userdb);

	void delete(int codigo, String userdb);

}
