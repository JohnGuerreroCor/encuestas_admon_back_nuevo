package com.usco.edu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.usco.edu.dao.IPreguntaCuestionarioDao;
import com.usco.edu.entities.PreguntaCuestionario;
import com.usco.edu.service.IPreguntaCuestionarioService;

@Service

public class PreguntaCuestionarioServiceImpl implements IPreguntaCuestionarioService {

	@Autowired
	private IPreguntaCuestionarioDao dao;

	@Transactional(readOnly = true)
	@Override
	public List<PreguntaCuestionario> find() {

		return dao.find();
	}

	@Override
	public PreguntaCuestionario findByCodigo(Long codigo) {

		try {

			return dao.findByCodigo(codigo);

		} catch (Exception e) {

			return null;

		}

	}

	@Override
	public int create(PreguntaCuestionario preguntaCuestionario) {

		try {

			dao.create(preguntaCuestionario);
			return 1;

		} catch (Exception e) {
			return 0;
		}

	}

	@Override
	public int update(PreguntaCuestionario preguntaCuestionario) {

		try {
			dao.update(preguntaCuestionario);
			return 1;
		} catch (Exception e) {
			return 0;
		}

	}

}
