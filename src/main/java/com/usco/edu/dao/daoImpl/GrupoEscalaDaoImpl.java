package com.usco.edu.dao.daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.IGrupoEscalaDao;
import com.usco.edu.entities.GrupoEscala;
import com.usco.edu.util.AuditoriaJdbcTemplate;

@Repository
public class GrupoEscalaDaoImpl implements IGrupoEscalaDao {

	@Autowired
	@Qualifier("JDBCTemplateEncuestasConsulta")
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	@Autowired
	private AuditoriaJdbcTemplate jdbcComponent;

	@Override
	public List<GrupoEscala> find() {

		String sql = "select * from encuestas.grupo_escala where gre_estado=1";

		List<GrupoEscala> lstGrupoEscala = namedJdbcTemplate.query(sql, new RowMapper<GrupoEscala>() {

			@Override
			public GrupoEscala mapRow(ResultSet rs, int rowNum) throws SQLException {

				GrupoEscala gre = new GrupoEscala();
				gre.setCodigo(rs.getLong("gre_codigo"));
				gre.setNombre(rs.getString("gre_nombre"));

				return gre;
			}

		});

		return lstGrupoEscala;
	}

	@Override
	public void insert(GrupoEscala gre, String userdb) {

		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		String sql = "INSERT INTO encuestas.grupo_escala (gre_nombre) VALUES(:nombre) ";

		try {
			MapSqlParameterSource parameter = new MapSqlParameterSource();

			parameter.addValue("nombre", gre.getNombre());

			jdbc.update(sql, parameter);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				cerrarConexion(dataSource.getConnection());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override

	public void update(GrupoEscala gre, String userdb) {

		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		String sql = "UPDATE encuestas.grupo_escala SET gre_nombre=:nombre WHERE gre_codigo=:codigo";

		try {
			MapSqlParameterSource parameter = new MapSqlParameterSource();

			parameter.addValue("nombre", gre.getNombre());
			parameter.addValue("codigo", gre.getCodigo());

			jdbc.update(sql, parameter);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				cerrarConexion(dataSource.getConnection());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void delete(int codigo, String userdb) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		String sql = "UPDATE encuestas.grupo_escala SET gre_estado=0 WHERE gre_codigo=:codigo";

		try {
			MapSqlParameterSource parameter = new MapSqlParameterSource();
			parameter.addValue("codigo", codigo);

			jdbc.update(sql, parameter);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				cerrarConexion(dataSource.getConnection());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private void cerrarConexion(Connection con) {

		if (con == null)
			return;

		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
