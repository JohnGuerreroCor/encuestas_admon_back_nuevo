package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.CuestionarioConfiguracion;

public interface ICuestionarioConfiguracionDao {

	List<CuestionarioConfiguracion> find(int uaa);

	List<CuestionarioConfiguracion> findByCodigo(Long codigo);

	void create(CuestionarioConfiguracion cuestionarioConfiguracion, String userdb);

	void update(CuestionarioConfiguracion cuestionarioConfiguracion, String userdb);

	void delete(int codigo, String userdb);

}
