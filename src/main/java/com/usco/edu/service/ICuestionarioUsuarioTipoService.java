package com.usco.edu.service;

import java.util.List;

import com.usco.edu.entities.CuestionarioUsuarioTipo;

public interface ICuestionarioUsuarioTipoService {

	List<CuestionarioUsuarioTipo> find();

	CuestionarioUsuarioTipo findByCodigo(Long codigo);

	int create(CuestionarioUsuarioTipo cuestionarioUsuarioTipo);

	int update(CuestionarioUsuarioTipo cuestionarioUsuarioTipo);

	int delete(int codigo);
}
