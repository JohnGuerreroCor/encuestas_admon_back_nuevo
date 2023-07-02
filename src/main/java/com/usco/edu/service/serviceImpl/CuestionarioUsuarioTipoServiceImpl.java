package com.usco.edu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.usco.edu.dao.ICuestionarioUsuarioTipoDao;
import com.usco.edu.entities.CuestionarioUsuarioTipo;
import com.usco.edu.service.ICuestionarioUsuarioTipoService;

@Service
public class CuestionarioUsuarioTipoServiceImpl implements ICuestionarioUsuarioTipoService {

	@Autowired
	private ICuestionarioUsuarioTipoDao dao;

	@Transactional(readOnly = true)
	@Override
	public List<CuestionarioUsuarioTipo> find() {

		return dao.find();
	}

	@Override
	public CuestionarioUsuarioTipo findByCodigo(Long codigo) {

		try {

			return dao.findByCodigo(codigo);

		} catch (Exception e) {

			return null;

		}

	}

	@Override
	public int create(CuestionarioUsuarioTipo cuestionarioUsuarioTipo) {

		try {

			dao.create(cuestionarioUsuarioTipo);
			return 1;

		} catch (Exception e) {
			return 0;
		}

	}

	@Override
	public int update(CuestionarioUsuarioTipo cuestionarioUsuarioTipo) {

		try {
			dao.update(cuestionarioUsuarioTipo);
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
