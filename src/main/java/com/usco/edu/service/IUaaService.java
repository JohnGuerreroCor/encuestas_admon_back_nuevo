package com.usco.edu.service;

import java.util.List;

import com.usco.edu.entities.Uaa;
import com.usco.edu.entities.UaaTipo;
import com.usco.edu.entities.UsuarioTipo;
import com.usco.edu.entities.Vinculo;

public interface IUaaService {

	List<Uaa> find(int codigoTipo);

	List<UaaTipo> findUaaTipo();

	Uaa findByCodigo(int codigo);

	List<UsuarioTipo> findTus();

	List<UaaTipo> findUaaTipoWeb();

	List<Vinculo> findVin();
}
