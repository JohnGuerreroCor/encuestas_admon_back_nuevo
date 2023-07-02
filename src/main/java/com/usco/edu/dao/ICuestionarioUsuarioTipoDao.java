package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.CuestionarioUsuarioTipo;

public interface ICuestionarioUsuarioTipoDao {
	
	List<CuestionarioUsuarioTipo> find();

	CuestionarioUsuarioTipo findByCodigo(Long codigo);

	void create(CuestionarioUsuarioTipo cuestionarioUsuarioTipo);

	void update(CuestionarioUsuarioTipo cuestionarioUsuarioTipo);

	void delete(int codigo);

}
