package com.usco.edu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.IEscalaDao;
import com.usco.edu.entities.Escala;
import com.usco.edu.service.IEscalaService;

@Service
public class EscalaServiceImpl implements IEscalaService {

	@Autowired
	private IEscalaDao dao;

	@Override
	public List<Escala> find(int codigo) {
		System.out.println(dao.find(codigo) + "\n" + codigo);
		return dao.find(codigo);
	}

	@Override
	public int insert(Escala escala, String userdb) {

		try {
			dao.insert(escala, userdb);
			return 1;
		} catch (Exception e) {

			System.out.println(e);
			return 0;
		}
	}

	@Override
	public int update(Escala escala, String userdb) {
		try {
			dao.update(escala, userdb);
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
