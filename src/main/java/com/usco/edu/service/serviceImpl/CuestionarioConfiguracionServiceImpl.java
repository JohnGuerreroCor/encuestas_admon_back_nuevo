package com.usco.edu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.usco.edu.dao.ICuestionarioConfiguracionDao;
import com.usco.edu.entities.CuestionarioConfiguracion;
import com.usco.edu.service.ICuestionarioConfiguracionService;

@Service
public class CuestionarioConfiguracionServiceImpl implements ICuestionarioConfiguracionService {

	@Autowired
	private ICuestionarioConfiguracionDao dao;

	@Transactional(readOnly = true)
	@Override
	public List<CuestionarioConfiguracion> find(int uaa) {

		return dao.find(uaa);
	}

	@Override
	public List<CuestionarioConfiguracion> findByCodigo(Long codigo) {

		return dao.findByCodigo(codigo);

	}

	@Override
	public int create(CuestionarioConfiguracion cuestionarioConfiguracion, String userdb) {

		try {

			dao.create(cuestionarioConfiguracion, userdb);
			return 1;

		} catch (Exception e) {
			System.out.println(e);
			return 0;
		}

	}

	@Override
	public int update(CuestionarioConfiguracion cuestionarioConfiguracion, String userdb) {

		try {
			dao.update(cuestionarioConfiguracion, userdb);
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
