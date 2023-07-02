package com.usco.edu.dao.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import org.springframework.stereotype.Repository;

import com.usco.edu.dao.ITipoRespuestaDao;
import com.usco.edu.entities.TipoRespuestas;

@Repository
public class TipoRespuestaDaoImpl implements ITipoRespuestaDao {

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	@Override
	public List<TipoRespuestas> find() {

		String sql = "select * from encuestas.tipo_respuestas where tre_estado=1";

		List<TipoRespuestas> lstTipoRespuesta = namedJdbcTemplate.query(sql, new RowMapper<TipoRespuestas>() {

			@Override
			public TipoRespuestas mapRow(ResultSet rs, int rowNum) throws SQLException {

				TipoRespuestas tipoRespuesta = new TipoRespuestas();
				tipoRespuesta.setCodigo(rs.getLong("tre_codigo"));
				tipoRespuesta.setNombre(rs.getString("tre_nombre"));

				return tipoRespuesta;
			}

		});

		return lstTipoRespuesta;
	}

	@Override
	public TipoRespuestas findByCodigo(int codigo) {

		String sql = "SELECT * from academia3000_jankarlos.encuestas.tipo_respuestas tr where tr.tre_estado =1 AND  tr.tre_codigo =:codigo";

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", codigo);

		List<TipoRespuestas> lstTipoRespuesta = namedJdbcTemplate.query(sql, parameter,
				new RowMapper<TipoRespuestas>() {

					@Override
					public TipoRespuestas mapRow(ResultSet rs, int rowNum) throws SQLException {

						TipoRespuestas tipoRespuesta = new TipoRespuestas();
						tipoRespuesta.setCodigo(rs.getLong("tre_codigo"));
						tipoRespuesta.setNombre(rs.getString("tre_nombre"));
						tipoRespuesta.setEstado(rs.getInt("tre_estado"));

						return tipoRespuesta;
					}

				});

		return lstTipoRespuesta.get(0);
	}

}
