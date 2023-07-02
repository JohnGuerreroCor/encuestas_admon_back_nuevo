package com.usco.edu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.usco.edu.dao.IPreguntaRespuestasDao;
import com.usco.edu.entities.PreguntaRespuestas;
import com.usco.edu.service.IPreguntaRespuestaService;

@Service
public class PreguntaRespuestaServiceImpl implements IPreguntaRespuestaService {

	@Autowired
	private IPreguntaRespuestasDao dao;

	@Transactional(readOnly = true)
	@Override
	public List<PreguntaRespuestas> find() {

		return dao.find();
	}

	@Override
	public PreguntaRespuestas findByCodigo(Long codigo) {

		try {

			return dao.findByCodigo(codigo);

		} catch (Exception e) {

			return null;

		}

	}

	@Override
	public int create(PreguntaRespuestas preguntaRespuestas, String userdb) {

		try {

			dao.create(preguntaRespuestas, userdb);
			return 1;

		} catch (Exception e) {
			return 0;
		}

	}

	@Override
	public int update(PreguntaRespuestas preguntaRespuestas, String userdb) {

		try {
			if (preguntaRespuestas.getDescripcionAdicional() == null) {
				System.out.println("or");
				dao.update(preguntaRespuestas, userdb);
				return 1;
			} else {
				dao.updateAgg(preguntaRespuestas, userdb);
				System.out.println("Se Supone que entor");
				return 1;
			}
		} catch (Exception e) {
			return 0;
		}

	}

	@Override
	public int delete(int codigo, String userdb) {

		try {
			dao.delete(codigo, userdb);
			return 1;

		} catch (Exception e) {
			return 0;
		}

	}

	@Override
	public List<PreguntaRespuestas> findByCuestionario(Long codigo) {

		return dao.findByCuestionario(codigo);

	}

	@Override
	public List<PreguntaRespuestas> findByPregunta(int codigo) {

		try {

			return dao.findbyPregunta(codigo);

		} catch (Exception e) {

			System.out.println("mensaje de error" + e);
			return null;
		}
	}

}
