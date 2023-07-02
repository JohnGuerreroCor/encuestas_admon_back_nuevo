package com.usco.edu.dao.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.usco.edu.entities.Programa;
import com.usco.edu.resultSetExtractor.ProgramSimplSetExtractor;
import com.usco.edu.dao.IProgramaDao;

@Repository
public class ProgramaDaoImpl implements IProgramaDao {

	@Autowired
	@Qualifier("JDBCTemplateEncuestasConsulta")
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	@Override
	public List<Programa> progromasAll(String userdb) {

		String sql = "SELECT * FROM programa p "
				+ "INNER JOIN uaa ua on p.uaa_codigo = ua.uaa_codigo "
				+ "INNER JOIN sede s on s.sed_codigo = p.sed_codigo "
				+ "WHERE uaa_codigo_unificado IS NOT NULL "
				+ "order by ua.uaa_nombre asc";
		
		List<Programa> programas = null;
		try {
			programas = namedJdbcTemplate.query(sql, new ProgramSimplSetExtractor());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return programas;
	}
	
}
