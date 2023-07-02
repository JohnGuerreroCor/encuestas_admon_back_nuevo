package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.Cuestionario;

public interface ICuestionarioDao {

	List<Cuestionario> find(int uaa);

	Cuestionario findByCodigo(Long long1);

	void update(Cuestionario cuestionario, String userdb);

	void delete(int codigo, String userdb);

	void createInicio(Cuestionario cuestionario, String userdb);

	void createFin(Cuestionario cuestionario, String userdb);

	void createCompleto(Cuestionario cuestionario, String userdb);

	void crearNotNull(Cuestionario cuestionario, String userdb);

}
