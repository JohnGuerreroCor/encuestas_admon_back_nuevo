package com.usco.edu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.IProgramaDao;
import com.usco.edu.entities.Programa;
import com.usco.edu.service.IProgramaService;

@Service
public class ProgramaServiceImpl implements IProgramaService {
	
	@Autowired
	IProgramaDao programaDao;

	@Override
	public List<Programa> progromasAll(String userdb) {
		return programaDao.progromasAll(userdb);
		}
	
}
