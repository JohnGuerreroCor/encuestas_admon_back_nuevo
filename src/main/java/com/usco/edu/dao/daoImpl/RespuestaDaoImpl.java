package com.usco.edu.dao.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.IRespuestaDao;
import com.usco.edu.entities.Pregunta;
import com.usco.edu.entities.PreguntaRespuestas;
import com.usco.edu.entities.Respuesta;
import com.usco.edu.entities.RespuestaCuestionario;

@Repository
public class RespuestaDaoImpl implements IRespuestaDao {

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	@Override
	public List<Respuesta> find() {

		String sql = "SELECT res_codigo, res_adicional, r.rcu_codigo, r.prr_codigo, res_estado, res_texto, r.pre_codigo,pre_descripcion ,"
				+ " rc.rcu_estamento , pr.prr_descripcion_adicional FROM encuestas.respuestas r join encuestas.preguntas p "
				+ "on r.pre_codigo =p.pre_codigo join encuestas.respuestas_cuestionarios rc on r.rcu_codigo =rc.rcu_codigo "
				+ "join encuestas.preguntas_respuestas pr on r.prr_codigo =pr.prr_codigo where res_estado=1 ";

		List<Respuesta> lstRespuesta = namedJdbcTemplate.query(sql, new RowMapper<Respuesta>() {

			@Override
			public Respuesta mapRow(ResultSet rs, int rowNum) throws SQLException {

				PreguntaRespuestas preguntaRespuestas = new PreguntaRespuestas();
				preguntaRespuestas.setCodigo(rs.getLong("prr_codigo"));
				preguntaRespuestas.setDescripcionAdicional(rs.getString("prr_descripcion_adicional"));

				RespuestaCuestionario rc = new RespuestaCuestionario();
				rc.setCodigo(rs.getLong("rcu_codigo"));
				rc.setEstamento(rs.getInt("rcu_estamento"));

				Pregunta pregunta = new Pregunta();
				pregunta.setCodigo(rs.getLong("pre_codigo"));
				pregunta.setDescripcion(rs.getString("pre_descripcion"));

				Respuesta respuesta = new Respuesta();
				respuesta.setAdicional(rs.getString("res_adicional"));
				respuesta.setCodigo(rs.getLong("res_codigo"));
				respuesta.setTexto(rs.getString("res_texto"));
				respuesta.setPregunta(pregunta);
				respuesta.setPreguntaRespuesta(preguntaRespuestas);
				respuesta.setRespuestaCuestionario(rc);

				return respuesta;
			}

		});

		return lstRespuesta;

	}

	@Override
	public Respuesta findByCodigo(Long codigo) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", codigo);

		String sql = "SELECT res_codigo, res_adicional, r.rcu_codigo, r.prr_codigo, res_estado, res_texto, r.pre_codigo,pre_descripcion ,"
				+ " rc.rcu_estamento , pr.prr_descripcion_adicional FROM encuestas.respuestas r join encuestas.preguntas p "
				+ "on r.pre_codigo =p.pre_codigo join encuestas.respuestas_cuestionarios rc on r.rcu_codigo =rc.rcu_codigo "
				+ "join encuestas.preguntas_respuestas pr on r.prr_codigo =pr.prr_codigo  where res_codigo=:codigo AND res_estado=1";

		List<Respuesta> lstRespuesta = namedJdbcTemplate.query(sql, parameter, new RowMapper<Respuesta>() {

			@Override
			public Respuesta mapRow(ResultSet rs, int rowNum) throws SQLException {

				PreguntaRespuestas preguntaRespuestas = new PreguntaRespuestas();
				preguntaRespuestas.setCodigo(rs.getLong("prr_codigo"));
				preguntaRespuestas.setDescripcionAdicional(rs.getString("prr_descripcion_adicional"));

				RespuestaCuestionario rc = new RespuestaCuestionario();
				rc.setCodigo(rs.getLong("rcu_codigo"));
				rc.setEstamento(rs.getInt("rcu_estamento"));

				Pregunta pregunta = new Pregunta();
				pregunta.setCodigo(rs.getLong("pre_codigo"));
				pregunta.setDescripcion(rs.getString("pre_descripcion"));

				Respuesta respuesta = new Respuesta();
				respuesta.setAdicional(rs.getString("res_adicional"));
				respuesta.setCodigo(rs.getLong("res_codigo"));
				respuesta.setTexto(rs.getString("res_texto"));
				respuesta.setPregunta(pregunta);
				respuesta.setPreguntaRespuesta(preguntaRespuestas);
				respuesta.setRespuestaCuestionario(rc);

				return respuesta;
			}

		});

		return lstRespuesta.get(0);
	}

	@Override
	public void create(Respuesta respuesta) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("adicional", respuesta.getAdicional());
		parameter.addValue("rcu", respuesta.getRespuestaCuestionario().getCodigo());
		parameter.addValue("prr", respuesta.getPreguntaRespuesta().getCodigo());
		parameter.addValue("texto", respuesta.getTexto());
		parameter.addValue("pregunta", respuesta.getPregunta().getCodigo());

		String sql = "INSERT INTO encuestas.respuestas (res_adicional, rcu_codigo, prr_codigo,  res_texto, pre_codigo)"
				+ " VALUES(:adicional, :rcu, :prr, :texto, :pregunta);";

		namedJdbcTemplate.update(sql, parameter);

	}

	@Override
	public void update(Respuesta respuesta) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("adicional", respuesta.getAdicional());
		parameter.addValue("rcu", respuesta.getRespuestaCuestionario().getCodigo());
		parameter.addValue("prr", respuesta.getPreguntaRespuesta().getCodigo());
		parameter.addValue("texto", respuesta.getTexto());
		parameter.addValue("pregunta", respuesta.getPregunta().getCodigo());
		parameter.addValue("codigo", respuesta.getCodigo());

		String sql = "UPDATE encuestas.respuestas SET res_adicional=:adicional, rcu_codigo=:rcu, prr_codigo=:prr, res_texto=:texto, "
				+ "pre_codigo=:pregunta WHERE res_codigo=:codigo";

		namedJdbcTemplate.update(sql, parameter);

	}

	@Override
	public void delete(int codigo) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", codigo);

		String sql = "UPDATE encuestas.respuestas SET res_estado=0 WHERE res_codigo=:codigo;";

		namedJdbcTemplate.update(sql, parameter);

	}

}
