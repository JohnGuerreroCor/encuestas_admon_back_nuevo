package com.usco.edu.dao.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.IUsuarioDao;
import com.usco.edu.entities.Usuario;
import com.usco.edu.rowMapper.UsuarioRowMapper;

@Repository
public class UsuarioDaoImpl implements IUsuarioDao{
	
	@Autowired
	@Qualifier("JDBCTemplatePlanesLogin")
	public JdbcTemplate jdbcTemplate;

	@Override
	public Usuario findByUsername(String username) {
		String sql = "select * from carnetizacion.usuario_carnetizacion_admon_login uca "
				+ "inner join uaa u on u.uaa_codigo = uca.usg_uaa "
				+ "inner join sede s on s.sed_codigo = u.sed_codigo "
				+ "inner join persona p on p.per_codigo = uca.up "
				+ "where  us = ? ";
		return jdbcTemplate.queryForObject(sql, new Object[] { username }, new UsuarioRowMapper());
	}


	@Override
	public boolean validarUser(String username) {
		int result = 0;
		String sql = "select COUNT(us.us) from carnetizacion.usuario_carnetizacion_admon_login us "
				+ "inner join uaa u on u.uaa_codigo = us.usg_uaa "
				+ "inner join sede s on s.sed_codigo = u.sed_codigo "
				+ "inner join persona p on p.per_codigo = us.up "
				+ "where  us = ? ";
		result =  jdbcTemplate.queryForObject(sql, new Object[] { username }, Integer.class);
		return result > 0 ? true : false;
	}

}
