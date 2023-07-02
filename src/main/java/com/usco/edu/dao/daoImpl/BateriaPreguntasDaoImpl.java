package com.usco.edu.dao.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.IBateriaPreguntasDao;
import com.usco.edu.entities.BateriaPreguntas;
import com.usco.edu.entities.Pregunta;

@Repository
public class BateriaPreguntasDaoImpl implements IBateriaPreguntasDao {

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	@Override
	public List<BateriaPreguntas> find() {

		String sql = "SELECT  bpr_codigo,bpr_descripcion,pre_descripcion from "
				+ "encuestas.bateria_preguntas bp join encuestas.preguntas p on bp.pre_codigo =p.pre_codigo where bpr_estado=1";

		List<BateriaPreguntas> lstBateriaPreguntas = namedJdbcTemplate.query(sql, new RowMapper<BateriaPreguntas>() {

			@Override
			public BateriaPreguntas mapRow(ResultSet rs, int rowNum) throws SQLException {

				Pregunta pregunta = new Pregunta();

				pregunta.setDescripcion(rs.getString("pre_descripcion"));

				BateriaPreguntas bPreguntas = new BateriaPreguntas();
				bPreguntas.setCodigo(rs.getLong("bpr_codigo"));
				bPreguntas.setDescripcion(rs.getString("bpr_descripcion"));
				bPreguntas.setPregunta(pregunta);

				return bPreguntas;
			}

		});

		return lstBateriaPreguntas;
	}

	@Override
	public BateriaPreguntas findByCodigo(Long codigo) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", codigo);

		String sql = "SELECT  bpr_codigo,bpr_descripcion,pre_descripcion from "
				+ "encuestas.bateria_preguntas bp join encuestas.preguntas p on bp.pre_codigo =p.pre_codigo where bpr_estado=1 AND bpr_codigo=:codigo";

		List<BateriaPreguntas> lstBateriaPreguntas = namedJdbcTemplate.query(sql, parameter,
				new RowMapper<BateriaPreguntas>() {

					@Override
					public BateriaPreguntas mapRow(ResultSet rs, int rowNum) throws SQLException {

						Pregunta pregunta = new Pregunta();

						pregunta.setDescripcion(rs.getString("pre_descripcion"));

						BateriaPreguntas bPreguntas = new BateriaPreguntas();
						bPreguntas.setCodigo(rs.getLong("bpr_codigo"));
						bPreguntas.setDescripcion(rs.getString("bpr_descripcion"));
						bPreguntas.setPregunta(pregunta);

						return bPreguntas;
					}

				});

		return lstBateriaPreguntas.get(0);
	}

	@Override
	public void create(BateriaPreguntas bp) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("pregunta", bp.getPregunta().getCodigo());
		parameter.addValue("descripcion", bp.getDescripcion());

		String sql = "INSERT INTO encuestas.bateria_preguntas (pre_codigo,  bpr_descripcion) VALUES(:pregunta, :descripcion)";

		namedJdbcTemplate.update(sql, parameter);

	}

	@Override
	public void update(BateriaPreguntas bp) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("pregunta", bp.getPregunta().getCodigo());
		parameter.addValue("descripcion", bp.getDescripcion());
		parameter.addValue("codigo", bp.getCodigo());

		String sql = "UPDATE encuestas.bateria_preguntas SET pre_codigo=:pregunta, bpr_descripcion=:descripcion WHERE bpr_codigo=:codigo";

		namedJdbcTemplate.update(sql, parameter);

	}

	@Override
	public void delete(Long codigo) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", codigo);

		String sql = "UPDATE encuestas.bateria_preguntas SET bpr_estado WHERE bpr_codigo=:codigo";

		namedJdbcTemplate.update(sql, parameter);

	}

}
