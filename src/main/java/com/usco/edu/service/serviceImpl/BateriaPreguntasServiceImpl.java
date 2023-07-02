package com.usco.edu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.IBateriaPreguntasDao;
import com.usco.edu.entities.BateriaPreguntas;
import com.usco.edu.service.IBateriaPreguntasService;

@Service
public class BateriaPreguntasServiceImpl implements IBateriaPreguntasService {

	@Autowired
	private IBateriaPreguntasDao dao;

	@Override
	public List<BateriaPreguntas> find() {

		return dao.find();
	}

	@Override
	public BateriaPreguntas findByCodigo(Long codigo) {

		try {

			return dao.findByCodigo(codigo);

		} catch (Exception e) {

			return null;
		}
	}

	@Override
	public int create(BateriaPreguntas bp) {

		try {

			dao.create(bp);

			return 1;

		} catch (Exception e) {

			return 0;

		}
	}

	@Override
	public int update(BateriaPreguntas bp) {
		try {

			dao.update(bp);

			return 1;

		} catch (Exception e) {

			return 0;

		}
	}

	@Override
	public int delete(Long codigo) {
		try {

			dao.delete(codigo);

			return 1;

		} catch (Exception e) {

			return 0;

		}
	}

}
