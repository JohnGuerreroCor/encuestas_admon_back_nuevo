package com.usco.edu.dao.daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.IRespuestaOpcionesDao;
import com.usco.edu.entities.RespuestaOpciones;
import com.usco.edu.util.AuditoriaJdbcTemplate;

@Repository
public class RespuestaOpcionesDaoImpl implements IRespuestaOpcionesDao {

	@Autowired
	private AuditoriaJdbcTemplate jdbcComponent;

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	@Override
	public List<RespuestaOpciones> find(int uaa) {
		
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("uaa", uaa);

		String sql = "select * from encuestas.respuestas_opciones ro "
				+ "where ro.rop_estado = 1 order by ro.rop_codigo desc";

		List<RespuestaOpciones> lstRespuestaOpciones = namedJdbcTemplate.query(sql, parameter, new RowMapper<RespuestaOpciones>() {

			@Override
			public RespuestaOpciones mapRow(ResultSet rs, int rowNum) throws SQLException {

				RespuestaOpciones respuestaOpciones = new RespuestaOpciones();
				respuestaOpciones.setCodigo(rs.getLong("rop_codigo"));
				respuestaOpciones.setDescripcion(rs.getString("rop_descripcion"));

				return respuestaOpciones;
			}

		});

		return lstRespuestaOpciones;

	}

	@Override
	public RespuestaOpciones findByCode(int codigo) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", codigo);

		String sql = "SELECT * from encuestas.respuestas_opciones ro where ro.rop_estado =1 AND ro.rop_codigo=:codigo";

		List<RespuestaOpciones> lstRespuestaOpciones = namedJdbcTemplate.query(sql, parameter,
				new RowMapper<RespuestaOpciones>() {

					@Override
					public RespuestaOpciones mapRow(ResultSet rs, int rowNum) throws SQLException {

						RespuestaOpciones respuestaOpciones = new RespuestaOpciones();
						respuestaOpciones.setCodigo(rs.getLong("rop_codigo"));
						respuestaOpciones.setDescripcion(rs.getString("rop_descripcion"));

						return respuestaOpciones;
					}

				});

		return lstRespuestaOpciones.get(0);
	}

	@Override
	public void create(RespuestaOpciones respuestaOpciones, String userdb) {

		String sql = "INSERT INTO encuestas.respuestas_opciones (rop_descripcion) VALUES(:descripcion)";

		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		try {
			MapSqlParameterSource parameter = new MapSqlParameterSource();
			parameter.addValue("descripcion", respuestaOpciones.getDescripcion());

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

	@Override
	public void update(RespuestaOpciones respuestaOpciones, String userdb) {

		String sql = "UPDATE encuestas.respuestas_opciones SET rop_descripcion=:descripcion WHERE rop_codigo=:codigo";
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();
			parameter.addValue("codigo", respuestaOpciones.getCodigo());
			parameter.addValue("descripcion", respuestaOpciones.getDescripcion());

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
	public void remove(int codigo, String userdb) {

		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", codigo);

		String sql = "UPDATE encuestas.respuestas_opciones SET  rop_estado=0 WHERE rop_codigo=:codigo";

		jdbc.update(sql, parameter);

	}

	@Override
	public List<RespuestaOpciones> findByPregunta(int pregunta) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("pregunta", pregunta);

		String sql = "SELECT ro.rop_codigo,rop_descripcion from encuestas.preguntas_respuestas pr"
				+ " join encuestas.respuestas_opciones ro on pr.rop_codigo =ro.rop_codigo "
				+ "where pr.pre_codigo =:pregunta and pr.prr_estado =1";

		List<RespuestaOpciones> lstRespuestaOpciones = namedJdbcTemplate.query(sql, parameter,
				new RowMapper<RespuestaOpciones>() {

					@Override
					public RespuestaOpciones mapRow(ResultSet rs, int rowNum) throws SQLException {

						RespuestaOpciones respuestaOpciones = new RespuestaOpciones();
						respuestaOpciones.setCodigo(rs.getLong("rop_codigo"));
						respuestaOpciones.setDescripcion(rs.getString("rop_descripcion"));

						return respuestaOpciones;
					}

				});

		return lstRespuestaOpciones;
	}

}
