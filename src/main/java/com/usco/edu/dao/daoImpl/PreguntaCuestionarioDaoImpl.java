package com.usco.edu.dao.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.IPreguntaCuestionarioDao;
import com.usco.edu.entities.Cuestionario;
import com.usco.edu.entities.Pregunta;
import com.usco.edu.entities.PreguntaCuestionario;

@Repository
public class PreguntaCuestionarioDaoImpl implements IPreguntaCuestionarioDao {

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	@Override
	public List<PreguntaCuestionario> find() {

		String sql = "SELECT pcu_codigo, pc.pre_codigo, pc.cue_codigo, pcu_descripcion,pre_descripcion,c.cue_nombre FROM"
				+ " encuestas.preguntas_custionario pc join encuestas.preguntas p on pc.pre_codigo =p.pre_codigo join "
				+ "encuestas.cuestionarios c on pc.cue_codigo =c.cue_codigo";
		List<PreguntaCuestionario> lstPreguntaCuestionario = namedJdbcTemplate.query(sql,
				new RowMapper<PreguntaCuestionario>() {

					@Override
					public PreguntaCuestionario mapRow(ResultSet rs, int rowNum) throws SQLException {

						Pregunta pregunta = new Pregunta();
						pregunta.setCodigo(rs.getLong("pre_codigo"));
						pregunta.setDescripcion(rs.getString("pre_descripcion"));

						Cuestionario cuestionario = new Cuestionario();
						cuestionario.setCodigo(rs.getLong("cue_codigo"));
						cuestionario.setNombre(rs.getString("cue_nombre"));

						PreguntaCuestionario preguntaCuestionario = new PreguntaCuestionario();
						preguntaCuestionario.setCodigo(rs.getLong("pcu_codigo"));
						preguntaCuestionario.setDescripcion(rs.getString("pcu_descripcion"));
						preguntaCuestionario.setPregunta(pregunta);
						preguntaCuestionario.setCuestionario(cuestionario);

						return preguntaCuestionario;
					}

				});

		return lstPreguntaCuestionario;

	}

	@Override
	public PreguntaCuestionario findByCodigo(Long codigo) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", codigo);

		String sql = "SELECT pcu_codigo, pc.pre_codigo, pc.cue_codigo, pcu_descripcion,pre_descripcion,c.cue_nombre FROM"
				+ " encuestas.preguntas_custionario pc join encuestas.preguntas p on pc.pre_codigo =p.pre_codigo join "
				+ "encuestas.cuestionarios c on pc.cue_codigo =c.cue_codigo";

		List<PreguntaCuestionario> lstPreguntaCuestionario = namedJdbcTemplate.query(sql, parameter,
				new RowMapper<PreguntaCuestionario>() {

					@Override
					public PreguntaCuestionario mapRow(ResultSet rs, int rowNum) throws SQLException {

						Pregunta pregunta = new Pregunta();
						pregunta.setCodigo(rs.getLong("pre_codigo"));
						pregunta.setDescripcion(rs.getString("pre_descripcion"));

						Cuestionario cuestionario = new Cuestionario();
						cuestionario.setCodigo(rs.getLong("cue_codigo"));
						cuestionario.setNombre(rs.getString("cue_nombre"));

						PreguntaCuestionario preguntaCuestionario = new PreguntaCuestionario();
						preguntaCuestionario.setCodigo(rs.getLong("pcu_codigo"));
						preguntaCuestionario.setDescripcion(rs.getString("pcu_descripcion"));
						preguntaCuestionario.setPregunta(pregunta);
						preguntaCuestionario.setCuestionario(cuestionario);

						return preguntaCuestionario;
					}

				});

		return lstPreguntaCuestionario.get(0);

	}

	@Override
	public void create(PreguntaCuestionario preguntaCuestionario) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("pregunta", preguntaCuestionario.getPregunta().getCodigo());
		parameter.addValue("cuestionario", preguntaCuestionario.getCuestionario().getCodigo());
		parameter.addValue("descripcion", preguntaCuestionario.getDescripcion());

		String sql = "INSERT INTO encuestas.preguntas_custionario (pre_codigo, cue_codigo, pcu_descripcion) VALUES(:pregunta, :cuestionario, :descripcion)";

		namedJdbcTemplate.update(sql, parameter);

	}

	@Override
	public void update(PreguntaCuestionario preguntaCuestionario) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", preguntaCuestionario.getCodigo());
		parameter.addValue("pregunta", preguntaCuestionario.getPregunta().getCodigo());
		parameter.addValue("cuestionario", preguntaCuestionario.getCuestionario().getCodigo());
		parameter.addValue("descripcion", preguntaCuestionario.getDescripcion());

		String sql = "UPDATE aencuestas.preguntas_custionario SET pre_codigo=:pregunta, cue_codigo=:cuestionario, pcu_descripcion=:descripcion WHERE pcu_codigo=:codigo";

		namedJdbcTemplate.update(sql, parameter);

	}

}
