package com.usco.edu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.usco.edu.dao.IPreguntaDao;
import com.usco.edu.entities.Pregunta;
import com.usco.edu.service.IPreguntaService;

@Service
public class PreguntaServiceImpl implements IPreguntaService {

	@Autowired
	private IPreguntaDao dao;

	@Transactional(readOnly = true)
	@Override
	public List<Pregunta> find() {

		return dao.find();
	}

	@Override
	public Pregunta findByCodigo(Long codigo) {

//		try {
//
//			return dao.findByCodigo(codigo);
//
//		} catch (Exception e) {
//
//			return null;
//
//		}
		Pregunta pregunta = dao.findByCodigo(codigo);
		return pregunta;

	}

	@Override
	public int create(Pregunta pregunta, String userdb) {

		try {

			dao.create(pregunta, userdb);
			return 1;

		} catch (Exception e) {
			System.out.println(e);
			return 0;
		}

	}

	@Override
	public int update(Pregunta pregunta, String userdb) {

		dao.update(pregunta, userdb);
		return 1;

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
	public List<Pregunta> findCues(int cues) {

		return dao.findByCue(cues);
	}

	@Override
	public List<Pregunta> findByDependencia(int codigo) {

		return dao.findByDependencia(codigo);
	}

	@Override
	public List<Pregunta> findByCue2(int cues) {

		return dao.findByCue2(cues);
	}

	@Override
	public List<Pregunta> findByCueAdmin(int cues) {

		return dao.findByCueAdmin(cues);
	}

	@Override
	public List<Pregunta> findByCuestAndTipoRespuestaRadiobOrSelect(int cues) {

		return dao.findByCueandSelectOrRadioB(cues);
	}

}
