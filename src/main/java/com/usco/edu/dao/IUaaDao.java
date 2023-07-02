package com.usco.edu.dao;

import java.util.List;

import com.usco.edu.entities.Uaa;
import com.usco.edu.entities.UaaTipo;
import com.usco.edu.entities.UsuarioTipo;
import com.usco.edu.entities.Vinculo;

public interface IUaaDao {

	List<Uaa> find(int codigoTipo);

	List<UaaTipo> findUaaTipo();

	Uaa findByCodigo(int codigo);

	List<UsuarioTipo> findTus();

	List<UaaTipo> findUaaTipoWeb();

	List<Vinculo> findVin();
}
