package com.usco.edu.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.usco.edu.entities.Programa;

public class ProgramaSimplRowMapper implements RowMapper<Programa> {
	
	@Override
	public Programa mapRow(ResultSet rs, int rowNum) throws SQLException {
		Programa programa = new Programa();
		programa.setCodigo(rs.getLong("pro_codigo"));
		programa.setRegistroSnies(rs.getString("pro_registro_snies"));
		programa.setUaa(new UaaSimpleRowMapper().mapRow(rs, rowNum));
		programa.setSede(new SedeRowMapper().mapRow(rs, rowNum));
		return programa;
	}

}
