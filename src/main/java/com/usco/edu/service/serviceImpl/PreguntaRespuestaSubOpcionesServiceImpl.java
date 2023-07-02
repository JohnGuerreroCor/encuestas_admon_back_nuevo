package com.usco.edu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.usco.edu.dao.IPreguntaRespuestaSubOpcionesDao;
import com.usco.edu.entities.PRespuestaSubOpciones;
import com.usco.edu.service.IPreguntaRespuestaSubOpcionesService;

@Service
public class PreguntaRespuestaSubOpcionesServiceImpl implements IPreguntaRespuestaSubOpcionesService {

	@Autowired
	private IPreguntaRespuestaSubOpcionesDao dao;

	@Transactional(readOnly = true)
	@Override
	public List<PRespuestaSubOpciones> find() {

		return dao.find();
	}

	@Override
	public PRespuestaSubOpciones findByCodigo(Long codigo) {

		try {

			return dao.findByCodigo(codigo);

		} catch (Exception e) {

			return null;

		}

	}

	@Override
	public int create(PRespuestaSubOpciones pRespuestaSubOpciones) {

		try {

			dao.create(pRespuestaSubOpciones);
			return 1;

		} catch (Exception e) {
			return 0;
		}

	}

	@Override
	public int update(PRespuestaSubOpciones pRespuestaSubOpciones) {

		try {
			dao.update(pRespuestaSubOpciones);
			return 1;
		} catch (Exception e) {
			return 0;
		}

	}

	@Override
	public int delete(int codigo) {

		try {
			dao.delete(codigo);
			return 1;

		} catch (Exception e) {
			return 0;
		}

	}

}
