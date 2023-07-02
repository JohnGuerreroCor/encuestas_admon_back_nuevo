package com.usco.edu.service;

import java.util.List;

import com.usco.edu.entities.CuestionarioConfiguracion;

public interface ICuestionarioConfiguracionService {

	List<CuestionarioConfiguracion> find(int uaa);

	List<CuestionarioConfiguracion> findByCodigo(Long codigo);

	int create(CuestionarioConfiguracion cuestionarioConfiguracion, String userdb);

	int update(CuestionarioConfiguracion cuestionarioConfiguracion, String userdb);

	int delete(int codigo, String userdb);
}
