package com.usco.edu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.usco.edu.dao.IRespuestaCuestionarioDao;
import com.usco.edu.entities.RespuestaCuestionario;
import com.usco.edu.service.IRespuestaCuestionarioService;

@Service
public class RespuestaCuestionarioServiceImpl implements IRespuestaCuestionarioService {

	@Autowired
	private IRespuestaCuestionarioDao dao;

	@Transactional(readOnly = true)
	@Override
	public List<RespuestaCuestionario> find() {

		return dao.find();
	}

	@Override
	public RespuestaCuestionario findByCodigo(Long codigo) {

		try {

			return dao.findByCodigo(codigo);

		} catch (Exception e) {

			return null;

		}

	}

	@Override
	public int create(RespuestaCuestionario respuestaCuestionario) {

		try {

			dao.create(respuestaCuestionario);
			return 1;

		} catch (Exception e) {
			return 0;
		}

	}

	@Override
	public int update(RespuestaCuestionario respuestaCuestionario) {

		try {
			dao.update(respuestaCuestionario);
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
