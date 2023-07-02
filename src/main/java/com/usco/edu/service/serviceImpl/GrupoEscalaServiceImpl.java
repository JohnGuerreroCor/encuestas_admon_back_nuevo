package com.usco.edu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.IGrupoEscalaDao;
import com.usco.edu.entities.GrupoEscala;
import com.usco.edu.service.IGrupoEscalaService;

@Service
public class GrupoEscalaServiceImpl implements IGrupoEscalaService {

	@Autowired
	private IGrupoEscalaDao dao;

	@Override
	public List<GrupoEscala> find() {

		return dao.find();
	}

	@Override
	public int insert(GrupoEscala grupoEscala, String userdb) {

		try {
			dao.insert(grupoEscala, userdb);
			return 1;
		} catch (Exception e) {

			System.out.println(e);
			return 0;
		}
	}

	@Override
	public int update(GrupoEscala grupoEscala, String userdb) {
		try {
			dao.update(grupoEscala, userdb);
			return 1;
		} catch (Exception e) {

			System.out.println(e);
			return 0;
		}
	}

	@Override
	public int delete(int codigo, String userdb) {
		try {
			
			dao.delete(codigo, userdb);
			return 1;
		} catch (Exception e) {

			System.out.println(e);
			return 0;
		}
	}
}
