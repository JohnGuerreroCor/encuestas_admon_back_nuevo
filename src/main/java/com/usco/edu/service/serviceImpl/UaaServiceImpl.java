package com.usco.edu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.usco.edu.dao.IUaaDao;
import com.usco.edu.entities.Uaa;
import com.usco.edu.entities.UaaTipo;
import com.usco.edu.entities.UsuarioTipo;
import com.usco.edu.entities.Vinculo;
import com.usco.edu.service.IUaaService;

@Service
public class UaaServiceImpl implements IUaaService {

	@Autowired
	private IUaaDao dao;

	@Transactional(readOnly = true)
	@Override
	public List<Uaa> find(int codigoTipo) {

		return dao.find(codigoTipo);
	}

	@Override
	public List<UaaTipo> findUaaTipo() {

		return dao.findUaaTipo();
	}

	@Override
	public Uaa findByCodigo(int codigo) {

		return dao.findByCodigo(codigo);
	}

	@Override
	public List<UsuarioTipo> findTus() {

		return dao.findTus();
	}

	@Override
	public List<UaaTipo> findUaaTipoWeb() {

		return dao.findUaaTipoWeb();
	}

	@Override
	public List<Vinculo> findVin() {

		return dao.findVin();
	}

}
