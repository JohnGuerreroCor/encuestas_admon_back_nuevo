package com.usco.edu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.usco.edu.dao.ITipoRespuestaDao;
import com.usco.edu.entities.TipoRespuestas;
import com.usco.edu.service.ITipoRespuestaService;

@Service
public class TipoRespuestaServiceImpl implements ITipoRespuestaService {

	@Autowired
	private ITipoRespuestaDao tipoRespuesta;

	@Transactional(readOnly = true)

	@Override
	public List<TipoRespuestas> find() {

		return tipoRespuesta.find();
	}

	@Override
	public TipoRespuestas findByCodigo(int codigo) {

		return tipoRespuesta.findByCodigo(codigo);
	}

}
