package com.usco.edu.dao.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.IRespuestaCuestionarioDao;
import com.usco.edu.entities.Cuestionario;
import com.usco.edu.entities.Persona;
import com.usco.edu.entities.RespuestaCuestionario;

@Repository
public class RespuestaCuestionarioDaoImpl implements IRespuestaCuestionarioDao {

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	@Override
	public List<RespuestaCuestionario> find() {

		String sql = "SELECT rcu_codigo, rc.cue_codigo, rc.per_codigo, rcu_estado, rcu_fecha, rcu_vinculacion, rcu_estamento,"
				+ " calendario, p.per_nombre ,p.per_apellido ,c.cue_nombre FROM encuestas.respuestas_cuestionarios rc "
				+ "join dbo.persona p on rc.per_codigo =p.per_codigo join encuestas.cuestionarios c on rc.cue_codigo = "
				+ "c.cue_codigo where rc.rcu_estado =1";

		List<RespuestaCuestionario> lstRespuestaCuestionario = namedJdbcTemplate.query(sql,
				new RowMapper<RespuestaCuestionario>() {

					@Override
					public RespuestaCuestionario mapRow(ResultSet rs, int rowNum) throws SQLException {

						Persona persona = new Persona();
						persona.setNombre(rs.getString("per_nombre"));
						persona.setApellido(rs.getString("per_apellido"));
						persona.setCodigo(rs.getLong("per_codigo"));

						Cuestionario cuestionario = new Cuestionario();
						cuestionario.setCodigo(rs.getLong("cue_codigo"));
						cuestionario.setNombre(rs.getString("cue_nombre"));

						RespuestaCuestionario rcu = new RespuestaCuestionario();
						rcu.setCalendario(rs.getInt("calendario"));
						rcu.setCodigo(rs.getLong("rcu_codigo"));
						rcu.setEstamento(rs.getInt("rcu_estamento"));
						rcu.setFecha(rs.getDate("rcu_fecha"));
						rcu.setVinculacion(rs.getString("rcu_vinculacion"));
						rcu.setPersona(persona);
						rcu.setCuestionario(cuestionario);

						return rcu;
					}

				});

		return lstRespuestaCuestionario;
	}

	@Override
	public RespuestaCuestionario findByCodigo(Long codigo) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", codigo);

		String sql = "SELECT rcu_codigo, rc.cue_codigo, rc.per_codigo, rcu_estado, rcu_fecha, rcu_vinculacion, rcu_estamento,"
				+ " calendario, p.per_nombre ,p.per_apellido ,c.cue_nombre FROM encuestas.respuestas_cuestionarios rc "
				+ "join dbo.persona p on rc.per_codigo =p.per_codigo join encuestas.cuestionarios c on rc.cue_codigo = "
				+ "c.cue_codigo where rc.rcu_estado =1 AND rcu_codigo=:codigo";

		List<RespuestaCuestionario> lstRespuestaCuestionario = namedJdbcTemplate.query(sql, parameter,
				new RowMapper<RespuestaCuestionario>() {

					@Override
					public RespuestaCuestionario mapRow(ResultSet rs, int rowNum) throws SQLException {

						Persona persona = new Persona();
						persona.setNombre(rs.getString("per_nombre"));
						persona.setApellido(rs.getString("per_apellido"));
						persona.setCodigo(rs.getLong("per_codigo"));

						Cuestionario cuestionario = new Cuestionario();
						cuestionario.setCodigo(rs.getLong("cue_codigo"));
						cuestionario.setNombre(rs.getString("cue_nombre"));

						RespuestaCuestionario rcu = new RespuestaCuestionario();
						rcu.setCalendario(rs.getInt("calendario"));
						rcu.setCodigo(rs.getLong("rcu_codigo"));
						rcu.setEstamento(rs.getInt("rcu_estamento"));
						rcu.setFecha(rs.getDate("rcu_fecha"));
						rcu.setVinculacion(rs.getString("rcu_vinculacion"));
						rcu.setPersona(persona);
						rcu.setCuestionario(cuestionario);

						return rcu;
					}

				});

		return lstRespuestaCuestionario.get(0);

	}

	@Override
	public void create(RespuestaCuestionario respuestaCuestionario) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("cues", respuestaCuestionario.getCuestionario().getCodigo());
		parameter.addValue("per", respuestaCuestionario.getPersona().getCodigo());
		parameter.addValue("fecha", respuestaCuestionario.getFecha());
		parameter.addValue("vincu", respuestaCuestionario.getVinculacion());
		parameter.addValue("estamento", respuestaCuestionario.getEstamento());
		parameter.addValue("calendario", respuestaCuestionario.getCalendario());

		String sql = "INSERT INTO encuestas.respuestas_cuestionarios (cue_codigo, per_codigo, rcu_fecha, rcu_vinculacion, rcu_estamento, calendario)"
				+ " VALUES(:cues, :per,  :fecha, :vincu, :estamento, :calendario)";

		namedJdbcTemplate.update(sql, parameter);

	}

	@Override
	public void update(RespuestaCuestionario respuestaCuestionario) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("cues", respuestaCuestionario.getCuestionario().getCodigo());
		parameter.addValue("per", respuestaCuestionario.getPersona().getCodigo());
		parameter.addValue("fecha", respuestaCuestionario.getFecha());
		parameter.addValue("vincu", respuestaCuestionario.getVinculacion());
		parameter.addValue("estamento", respuestaCuestionario.getEstamento());
		parameter.addValue("calendario", respuestaCuestionario.getCalendario());

		String sql = "UPDATE encuestas.respuestas_cuestionarios SET cue_codigo=:cues, per_codigo=:per,  rcu_fecha=:fecha, rcu_vinculacion=:vincu,"
				+ " rcu_estamento=:estamento, calendario=:calendario WHERE rcu_codigo=:codigo";

		namedJdbcTemplate.update(sql, parameter);
	}

	@Override
	public void delete(int codigo) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", codigo);

		String sql = "UPDATE encuestas.respuestas_cuestionarios SET rcu_estado=1  WHERE rcu_codigo=:codigo";

		namedJdbcTemplate.update(sql, parameter);

	}

}
