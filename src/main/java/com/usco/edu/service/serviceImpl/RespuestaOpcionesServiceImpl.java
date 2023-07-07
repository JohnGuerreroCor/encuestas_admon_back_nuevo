package com.usco.edu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.usco.edu.dao.IRespuestaOpcionesDao;
import com.usco.edu.entities.RespuestaOpciones;
import com.usco.edu.service.IRespuestaOpcionesService;

@Service
public class RespuestaOpcionesServiceImpl implements IRespuestaOpcionesService {

	@Autowired
	private IRespuestaOpcionesDao repo;

	@Transactional(readOnly = true)
	@Override
	public List<RespuestaOpciones> find(int uaa) {

		return repo.find(uaa);
	}

	@Override
	public RespuestaOpciones findByCode(int codigo) {

		return repo.findByCode(codigo);
	}

	@Override
	public void create(RespuestaOpciones respuestaOpciones, String userdb) {

		repo.create(respuestaOpciones, userdb);
	}

	@Override
	public void update(RespuestaOpciones respuestaOpciones, String userdb) {

		repo.update(respuestaOpciones, userdb);

	}

	@Override
	public void remove(int codigo, String userdb) {

		repo.remove(codigo, userdb);

	}

}
