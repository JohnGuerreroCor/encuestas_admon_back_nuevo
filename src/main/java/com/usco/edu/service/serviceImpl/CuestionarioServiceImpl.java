package com.usco.edu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.usco.edu.dao.ICuestionarioDao;
import com.usco.edu.entities.Cuestionario;
import com.usco.edu.service.ICuestionarioService;

@Service
public class CuestionarioServiceImpl implements ICuestionarioService {

	@Autowired
	private ICuestionarioDao dao;

	@Transactional(readOnly = true)
	@Override
	public List<Cuestionario> find(int uaa) {

		return dao.find(uaa);
	}

	@Override
	public Cuestionario findByCodigo(Long codigo) {

		try {
			return dao.findByCodigo(codigo);

		} catch (Exception e) {

			return null;

		}

	}

	@Override
	public int create(Cuestionario cuestionario, String userdb) {

		try {

			if (cuestionario.getInicio() == null && cuestionario.getFin() == null) {

				dao.crearNotNull(cuestionario, userdb);
				return 1;
			} else {
				if (cuestionario.getFin() == null) {
					dao.createInicio(cuestionario, userdb);
					return 1;
				} else {
					if (cuestionario.getInicio() == null) {
						dao.createFin(cuestionario, userdb);
						return 1;
					} else {
						dao.createCompleto(cuestionario, userdb);
						return 1;
					}
				}
			}

		} catch (Exception e) {
			return 0;
		}

	}

	@Override
	public int update(Cuestionario cuestionario, String userdb) {

		try {
			dao.update(cuestionario, userdb);
			return 1;
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
}
