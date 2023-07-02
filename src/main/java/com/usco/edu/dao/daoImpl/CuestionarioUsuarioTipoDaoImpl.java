package com.usco.edu.dao.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.ICuestionarioUsuarioTipoDao;
import com.usco.edu.entities.Cuestionario;
import com.usco.edu.entities.CuestionarioUsuarioTipo;
import com.usco.edu.entities.UsuarioTipo;

@Repository
public class CuestionarioUsuarioTipoDaoImpl implements ICuestionarioUsuarioTipoDao {

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	@Override
	public List<CuestionarioUsuarioTipo> find() {

		String sql = "SELECT cut_codigo, cut_estado, ca.tus_codigo, ca.cue_codigo, cut_fecha_registro,cue_nombre,tus_nombre FROM "
				+ "encuestas.cuestionarios_usuario_tipo ca join encuestas.cuestionarios c on  ca.cue_codigo =c.cue_codigo join"
				+ " dbo.usuario_tipo u on ca.tus_codigo =u.tus_codigo";

		List<CuestionarioUsuarioTipo> lstCuestionarioUsuarioTipo = namedJdbcTemplate.query(sql,
				new RowMapper<CuestionarioUsuarioTipo>() {

					@Override
					public CuestionarioUsuarioTipo mapRow(ResultSet rs, int rowNum) throws SQLException {

						UsuarioTipo tus = new UsuarioTipo();
						tus.setCodigo(rs.getLong("tus_codigo"));
						tus.setNombre(rs.getString("tus_nombre"));

						Cuestionario cuestionario = new Cuestionario();
						cuestionario.setCodigo(rs.getLong("cue_codigo"));
						cuestionario.setNombre(rs.getString("cue_nombre"));

						CuestionarioUsuarioTipo cuestionarioUsuarioTipo = new CuestionarioUsuarioTipo();
						cuestionarioUsuarioTipo.setCodigo(rs.getLong("cut_codigo"));
						cuestionarioUsuarioTipo.setFechaRegistro(rs.getDate("cut_fecha_registro"));
						cuestionarioUsuarioTipo.setUsuarioTipo(tus);
						cuestionarioUsuarioTipo.setCuestionario(cuestionario);

						return cuestionarioUsuarioTipo;
					}

				});

		return lstCuestionarioUsuarioTipo;
	}

	@Override
	public CuestionarioUsuarioTipo findByCodigo(Long codigo) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", codigo);

		String sql = "SELECT cut_codigo, cut_estado, ca.tus_codigo, ca.cue_codigo, cut_fecha_registro,cue_nombre,tus_nombre FROM "
				+ "encuestas.cuestionarios_usuario_tipo ca join encuestas.cuestionarios c on  ca.cue_codigo =c.cue_codigo join "
				+ " dbo.usuario_tipo u on ca.tus_codigo =u.tus_codigo  where c.cue_codigo=:codigo";

		List<CuestionarioUsuarioTipo> lstCuestionarioUsuarioTipo = namedJdbcTemplate.query(sql, parameter,
				new RowMapper<CuestionarioUsuarioTipo>() {

					@Override
					public CuestionarioUsuarioTipo mapRow(ResultSet rs, int rowNum) throws SQLException {

						UsuarioTipo tus = new UsuarioTipo();
						tus.setCodigo(rs.getLong("tus_codigo"));
						tus.setNombre(rs.getString("tus_nombre"));

						Cuestionario cuestionario = new Cuestionario();
						cuestionario.setCodigo(rs.getLong("cue_codigo"));
						cuestionario.setNombre(rs.getString("cue_nombre"));

						CuestionarioUsuarioTipo cuestionarioUsuarioTipo = new CuestionarioUsuarioTipo();
						cuestionarioUsuarioTipo.setCodigo(rs.getLong("cut_codigo"));
						cuestionarioUsuarioTipo.setFechaRegistro(rs.getDate("cut_fecha_registro"));
						cuestionarioUsuarioTipo.setUsuarioTipo(tus);
						cuestionarioUsuarioTipo.setCuestionario(cuestionario);

						return cuestionarioUsuarioTipo;
					}

				});

		return lstCuestionarioUsuarioTipo.get(0);
	}

	@Override
	public void create(CuestionarioUsuarioTipo cuestionarioUsuarioTipo) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("tus", cuestionarioUsuarioTipo.getUsuarioTipo().getCodigo());
		parameter.addValue("cuestionario", cuestionarioUsuarioTipo.getCuestionario().getCodigo());

		String sql = "INSERT INTO encuestas.cuestionarios_usuario_tipo ( tus_codigo, cue_codigo) VALUES( :tus, :cuestionario )";

		namedJdbcTemplate.update(sql, parameter);

	}

	@Override
	public void update(CuestionarioUsuarioTipo cuestionarioUsuarioTipo) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", cuestionarioUsuarioTipo.getCodigo());
		parameter.addValue("tus", cuestionarioUsuarioTipo.getUsuarioTipo().getCodigo());
		parameter.addValue("cuestionario", cuestionarioUsuarioTipo.getCuestionario().getCodigo());

		String sql = "UPDATE encuestas.cuestionarios_usuario_tipo SET  tus_codigo=:tus, cue_codigo=:cuestionario  WHERE cut_codigo=:codigo";

		namedJdbcTemplate.update(sql, parameter);

	}

	@Override
	public void delete(int codigo) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", codigo);

		String sql = "UPDATE encuestas.cuestionarios_usuario_tipo SET  cut_estado=0  WHERE cut_codigo=:codigo;";

		namedJdbcTemplate.update(sql, parameter);

	}

}
