package com.usco.edu.dao.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.IPreguntaRespuestaSubOpcionesDao;
import com.usco.edu.entities.PRespuestaSubOpciones;
import com.usco.edu.entities.PreguntaRespuestas;

@Repository
public class PreguntaRespuestaSubOpcionesDaoImpl implements IPreguntaRespuestaSubOpcionesDao {

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	@Override
	public List<PRespuestaSubOpciones> find() {

		String sql = "SELECT prs_codigo, prs_descripcion, ps.prr_codigo, prs_estado, prr_descripcion_adicional  "
				+ "FROM encuestas.preguntas_respuestas_subopciones ps join encuestas.preguntas_respuestas pr on ps.prr_codigo =pr.prr_codigo"
				+ " where ps.prs_estado =1;";

		List<PRespuestaSubOpciones> lstPRespuestaSubOpciones = namedJdbcTemplate.query(sql,
				new RowMapper<PRespuestaSubOpciones>() {

					@Override
					public PRespuestaSubOpciones mapRow(ResultSet rs, int rowNum) throws SQLException {

						PreguntaRespuestas preguntaRespuestas = new PreguntaRespuestas();
						preguntaRespuestas.setCodigo(rs.getLong("prr_codigo"));
						preguntaRespuestas.setDescripcionAdicional(rs.getString("prr_descripcion_adicional"));

						PRespuestaSubOpciones prs = new PRespuestaSubOpciones();
						prs.setCodigo(rs.getLong("prs_codigo"));
						prs.setDescripcion(rs.getString("prs_descripcion"));
						prs.setPreguntaRespuesta(preguntaRespuestas);

						return prs;
					}

				});

		return lstPRespuestaSubOpciones;
	}

	@Override
	public PRespuestaSubOpciones findByCodigo(Long codigo) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", codigo);

		String sql = "SELECT prs_codigo, prs_descripcion, ps.prr_codigo, prs_estado, prr_descripcion_adicional  "
				+ "FROM encuestas.preguntas_respuestas_subopciones ps join encuestas.preguntas_respuestas pr on ps.prr_codigo =pr.prr_codigo"
				+ " where ps.prs_estado =1 AND prs_codigo=:codigo";

		List<PRespuestaSubOpciones> lstPRespuestaSubOpciones = namedJdbcTemplate.query(sql, parameter,
				new RowMapper<PRespuestaSubOpciones>() {

					@Override
					public PRespuestaSubOpciones mapRow(ResultSet rs, int rowNum) throws SQLException {

						PreguntaRespuestas preguntaRespuestas = new PreguntaRespuestas();
						preguntaRespuestas.setCodigo(rs.getLong("prr_codigo"));
						preguntaRespuestas.setDescripcionAdicional(rs.getString("prr_descripcion_adicional"));

						PRespuestaSubOpciones prs = new PRespuestaSubOpciones();
						prs.setCodigo(rs.getLong("prs_codigo"));
						prs.setDescripcion(rs.getString("prs_descripcion"));
						prs.setPreguntaRespuesta(preguntaRespuestas);

						return prs;
					}

				});

		return lstPRespuestaSubOpciones.get(0);
	}

	@Override
	public void create(PRespuestaSubOpciones pRespuestaSubOpciones) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("prr", pRespuestaSubOpciones.getPreguntaRespuesta().getCodigo());
		parameter.addValue("descripcion", pRespuestaSubOpciones.getDescripcion());

		String sql = "INSERT INTO encuestas.preguntas_respuestas_subopciones (prs_descripcion, prr_codigo) VALUES(:descripcion, :prr)";

		namedJdbcTemplate.update(sql, parameter);

	}

	@Override
	public void update(PRespuestaSubOpciones pRespuestaSubOpciones) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("descripcion", pRespuestaSubOpciones.getCodigo());
		parameter.addValue("prr", pRespuestaSubOpciones.getPreguntaRespuesta().getCodigo());
		parameter.addValue("descripcion", pRespuestaSubOpciones.getDescripcion());
		parameter.addValue("codigo", pRespuestaSubOpciones.getCodigo());

		String sql = "UPDATE encuestas.preguntas_respuestas_subopciones SET prs_descripcion=:descripcion, prr_codigo=:prr, WHERE prs_codigo=:codigo";

		namedJdbcTemplate.update(sql, parameter);

	}

	@Override
	public void delete(int codigo) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", codigo);

		String sql = "UPDATE encuestas.preguntas_respuestas_subopciones SET prs_estado=0 WHERE prs_codigo=:codigo";

		namedJdbcTemplate.update(sql, parameter);

	}

}
