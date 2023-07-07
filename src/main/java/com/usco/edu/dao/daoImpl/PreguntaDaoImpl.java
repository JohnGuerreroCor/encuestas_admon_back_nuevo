package com.usco.edu.dao.daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.IPreguntaDao;
import com.usco.edu.entities.Cuestionario;
import com.usco.edu.entities.GrupoEscala;
import com.usco.edu.entities.Pregunta;
import com.usco.edu.entities.TipoRespuestas;
import com.usco.edu.util.AuditoriaJdbcTemplate;

@Repository
public class PreguntaDaoImpl implements IPreguntaDao {

	@Autowired
	@Qualifier("JDBCTemplateEncuestasConsulta")
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	@Autowired
	private AuditoriaJdbcTemplate jdbcComponent;

	@Override
	public List<Pregunta> find() {

		String sql = "SELECT * from encuestas.preguntas p join encuestas.tipo_respuestas tr on p.tre_codigo =tr.tre_codigo "
				+ "join encuestas.cuestionarios c on p.cue_codigo=c.cue_codigo left join encuestas.grupo_escala g on p.gre_codigo=g.gre_codigo"
				+ "   where pre_estado=1";

		List<Pregunta> lstPregunta = namedJdbcTemplate.query(sql, new RowMapper<Pregunta>() {

			@Override
			public Pregunta mapRow(ResultSet rs, int rowNum) throws SQLException {

				TipoRespuestas trespuesta = new TipoRespuestas();
				trespuesta.setCodigo(rs.getLong("tre_codigo"));
				trespuesta.setNombre(rs.getString("tre_nombre"));

				GrupoEscala gre = new GrupoEscala();
				gre.setCodigo(rs.getLong("gre_codigo"));
				gre.setNombre(rs.getString("gre_nombre"));

				Cuestionario cue = new Cuestionario();
				cue.setCodigo(rs.getLong("cue_codigo"));
				cue.setNombre(rs.getString("cue_nombre"));

				Pregunta pregunta = new Pregunta();
				pregunta.setCodigo(rs.getLong("pre_codigo"));
				pregunta.setDescripcion(rs.getString("pre_descripcion"));
				pregunta.setTextoAdicional(rs.getString("pre_texto_adicional"));
				pregunta.setTipo(rs.getInt("pre_tipo"));
				pregunta.setObligatorio(rs.getInt("pre_obligatorio"));
				pregunta.setDepende(rs.getInt("pre_codigo_depende"));
				pregunta.setIdentificador(rs.getString("pre_identificador"));
				pregunta.setCuestionario(cue);
				pregunta.setTipoRespuesta(trespuesta);
				pregunta.setGre(gre);

				return pregunta;
			}

		});

		return lstPregunta;
	}

	@Override
	public Pregunta findByCodigo(Long codigo) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", codigo);

		String sql = "SELECT * from encuestas.preguntas p join encuestas.tipo_respuestas tr on p.tre_codigo =tr.tre_codigo"
				+ " join encuestas.cuestionarios c on p.cue_codigo=c.cue_codigo left join encuestas.grupo_escala g on p.gre_codigo= g.gre_codigo"
				+ "  where pre_codigo=:codigo";

		List<Pregunta> lstPregunta = namedJdbcTemplate.query(sql, parameter, new RowMapper<Pregunta>() {

			@Override
			public Pregunta mapRow(ResultSet rs, int rowNum) throws SQLException {

				TipoRespuestas trespuesta = new TipoRespuestas();
				trespuesta.setCodigo(rs.getLong("tre_codigo"));
				trespuesta.setNombre(rs.getString("tre_nombre"));

				Cuestionario cue = new Cuestionario();
				cue.setCodigo(rs.getLong("cue_codigo"));
				cue.setNombre(rs.getString("cue_nombre"));

				GrupoEscala gre = new GrupoEscala();
				gre.setCodigo(rs.getLong("gre_codigo"));
				gre.setNombre(rs.getString("gre_nombre"));

				Pregunta pregunta = new Pregunta();
				pregunta.setCodigo(rs.getLong("pre_codigo"));
				pregunta.setDescripcion(rs.getString("pre_descripcion"));
				pregunta.setTextoAdicional(rs.getString("pre_texto_adicional"));
				pregunta.setTipo(rs.getInt("pre_tipo"));
				pregunta.setObligatorio(rs.getInt("pre_obligatorio"));
				pregunta.setDepende(rs.getInt("pre_codigo_depende"));
				pregunta.setIdentificador(rs.getString("pre_identificador"));
				pregunta.setCuestionario(cue);
				pregunta.setTipoRespuesta(trespuesta);
				pregunta.setGre(gre);

				return pregunta;
			}

		});

		return lstPregunta.get(0);
	}

	@Override
	public void delete(int codigo, String userdb) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		String sql = "UPDATE encuestas.preguntas SET pre_estado=0 WHERE pre_codigo=:codigo;";

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

	@Override
	public void update(Pregunta pregunta, String userdb) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		if (pregunta.getGre().getCodigo() == 0) {
			GrupoEscala gre = new GrupoEscala();
			gre.setCodigo(null);
			pregunta.setGre(gre);
		}

		String sql = "update encuestas.preguntas SET pre_descripcion= :descripcion,"
				+ "pre_texto_adicional= :texto, pre_tipo= :tipo,pre_identificador=:identificador, tre_codigo= :trespuestas,"
				+ " pre_obligatorio=:ob,pre_codigo_depende=:depende, gre_codigo=:gre,"
				+ " cue_codigo=:cue  WHERE pre_codigo= :codigo";

		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();
			parameter.addValue("codigo", pregunta.getCodigo());
			parameter.addValue("descripcion", pregunta.getDescripcion());
			parameter.addValue("texto", pregunta.getTextoAdicional(), Types.VARCHAR);
			parameter.addValue("tipo", pregunta.getTipo());
			parameter.addValue("trespuestas", pregunta.getTipoRespuesta().getCodigo());
			parameter.addValue("cue", pregunta.getCuestionario().getCodigo());
			parameter.addValue("ob", pregunta.getObligatorio());
			parameter.addValue("gre", pregunta.getGre().getCodigo(), Types.INTEGER);
			parameter.addValue("identificador", pregunta.getIdentificador(), Types.VARCHAR);
			if (pregunta.getDepende() > 1) {
				parameter.addValue("depende", pregunta.getDepende(), Types.INTEGER);
			} else {
				parameter.addValue("depende", null, Types.NULL);
			}

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
	public void create(Pregunta pregunta, String userdb) {

		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		System.out.println(pregunta.getGre().getCodigo());

		String sql = "INSERT INTO encuestas.preguntas (pre_descripcion, pre_texto_adicional, pre_tipo, tre_codigo,pre_identificador,cue_codigo,"
				+ "pre_obligatorio, gre_codigo, pre_codigo_depende) "
				+ "VALUES(:descripcion,  :texto, :tipo, :trespuestas,:identificador,:cue,:ob, :gre , :depende)";

		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();
			parameter.addValue("descripcion", pregunta.getDescripcion());
			parameter.addValue("texto", pregunta.getTextoAdicional(), Types.VARCHAR);
			parameter.addValue("tipo", pregunta.getTipo());
			parameter.addValue("trespuestas", pregunta.getTipoRespuesta().getCodigo());
			parameter.addValue("cue", pregunta.getCuestionario().getCodigo());
			parameter.addValue("ob", pregunta.getObligatorio());
			parameter.addValue("gre", pregunta.getGre().getCodigo(), Types.INTEGER);
			if (pregunta.getDepende() > 1) {
				parameter.addValue("depende", pregunta.getDepende(), Types.INTEGER);
			} else {
				parameter.addValue("depende", null, Types.NULL);
			}

			parameter.addValue("identificador", pregunta.getIdentificador(), Types.VARCHAR);

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
	public List<Pregunta> findByCue(int cues) {
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", cues);

		String sql = "SELECT * from encuestas.preguntas p join encuestas.tipo_respuestas tr on p.tre_codigo =tr.tre_codigo"
				+ " join encuestas.cuestionarios c on p.cue_codigo=c.cue_codigo left join encuestas.grupo_escala g on p.gre_codigo= g.gre_codigo"
				+ "  where c.cue_codigo=:codigo AND (p.pre_tipo=0 OR p.pre_tipo=2) AND p.pre_estado=1";

		List<Pregunta> lstPregunta = namedJdbcTemplate.query(sql, parameter, new RowMapper<Pregunta>() {

			@Override
			public Pregunta mapRow(ResultSet rs, int rowNum) throws SQLException {

				TipoRespuestas trespuesta = new TipoRespuestas();
				trespuesta.setCodigo(rs.getLong("tre_codigo"));
				trespuesta.setNombre(rs.getString("tre_nombre"));

				Cuestionario cue = new Cuestionario();
				cue.setCodigo(rs.getLong("cue_codigo"));
				cue.setNombre(rs.getString("cue_nombre"));

				GrupoEscala gre = new GrupoEscala();
				gre.setCodigo(rs.getLong("gre_codigo"));
				gre.setNombre(rs.getString("gre_nombre"));

				Pregunta pregunta = new Pregunta();
				pregunta.setCodigo(rs.getLong("pre_codigo"));
				pregunta.setDescripcion(rs.getString("pre_descripcion"));
				pregunta.setTextoAdicional(rs.getString("pre_texto_adicional"));
				pregunta.setTipo(rs.getInt("pre_tipo"));
				pregunta.setObligatorio(rs.getInt("pre_obligatorio"));
				pregunta.setDepende(rs.getInt("pre_codigo_depende"));
				pregunta.setIdentificador(rs.getString("pre_identificador"));
				pregunta.setCuestionario(cue);
				pregunta.setTipoRespuesta(trespuesta);
				pregunta.setGre(gre);

				return pregunta;
			}

		});

		return lstPregunta;

	}
	
	@Override
	public List<Pregunta> findByCueandSelectOrRadioB(int cues) {
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", cues);

		String sql = "SELECT * from encuestas.preguntas p join encuestas.tipo_respuestas tr on p.tre_codigo =tr.tre_codigo"
				+ " join encuestas.cuestionarios c on p.cue_codigo=c.cue_codigo left join encuestas.grupo_escala g on p.gre_codigo= g.gre_codigo"
				+ "  where c.cue_codigo=:codigo AND (p.pre_tipo=0 OR p.pre_tipo=2) AND p.pre_estado=1 AND (p.tre_codigo=1 OR p.tre_codigo=4)";

		List<Pregunta> lstPregunta = namedJdbcTemplate.query(sql, parameter, new RowMapper<Pregunta>() {

			@Override
			public Pregunta mapRow(ResultSet rs, int rowNum) throws SQLException {

				TipoRespuestas trespuesta = new TipoRespuestas();
				trespuesta.setCodigo(rs.getLong("tre_codigo"));
				trespuesta.setNombre(rs.getString("tre_nombre"));

				Cuestionario cue = new Cuestionario();
				cue.setCodigo(rs.getLong("cue_codigo"));
				cue.setNombre(rs.getString("cue_nombre"));

				GrupoEscala gre = new GrupoEscala();
				gre.setCodigo(rs.getLong("gre_codigo"));
				gre.setNombre(rs.getString("gre_nombre"));

				Pregunta pregunta = new Pregunta();
				pregunta.setCodigo(rs.getLong("pre_codigo"));
				pregunta.setDescripcion(rs.getString("pre_descripcion"));
				pregunta.setTextoAdicional(rs.getString("pre_texto_adicional"));
				pregunta.setTipo(rs.getInt("pre_tipo"));
				pregunta.setObligatorio(rs.getInt("pre_obligatorio"));
				pregunta.setDepende(rs.getInt("pre_codigo_depende"));
				pregunta.setIdentificador(rs.getString("pre_identificador"));
				pregunta.setCuestionario(cue);
				pregunta.setTipoRespuesta(trespuesta);
				pregunta.setGre(gre);

				return pregunta;
			}

		});

		return lstPregunta;

	}

	@Override
	public List<Pregunta> findByCue2(int cues) {
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", cues);

		String sql = "SELECT * from encuestas.preguntas p join encuestas.tipo_respuestas tr on p.tre_codigo =tr.tre_codigo"
				+ " join encuestas.cuestionarios c on p.cue_codigo=c.cue_codigo left join encuestas.grupo_escala g on p.gre_codigo= g.gre_codigo"
				+ "  where c.cue_codigo=:codigo AND p.pre_tipo=2  AND p.pre_estado=1";

		List<Pregunta> lstPregunta = namedJdbcTemplate.query(sql, parameter, new RowMapper<Pregunta>() {

			@Override
			public Pregunta mapRow(ResultSet rs, int rowNum) throws SQLException {

				TipoRespuestas trespuesta = new TipoRespuestas();
				trespuesta.setCodigo(rs.getLong("tre_codigo"));
				trespuesta.setNombre(rs.getString("tre_nombre"));

				Cuestionario cue = new Cuestionario();
				cue.setCodigo(rs.getLong("cue_codigo"));
				cue.setNombre(rs.getString("cue_nombre"));

				GrupoEscala gre = new GrupoEscala();
				gre.setCodigo(rs.getLong("gre_codigo"));
				gre.setNombre(rs.getString("gre_nombre"));

				Pregunta pregunta = new Pregunta();
				pregunta.setCodigo(rs.getLong("pre_codigo"));
				pregunta.setDescripcion(rs.getString("pre_descripcion"));
				pregunta.setTextoAdicional(rs.getString("pre_texto_adicional"));
				pregunta.setTipo(rs.getInt("pre_tipo"));
				pregunta.setObligatorio(rs.getInt("pre_obligatorio"));
				pregunta.setDepende(rs.getInt("pre_codigo_depende"));
				pregunta.setIdentificador(rs.getString("pre_identificador"));
				pregunta.setCuestionario(cue);
				pregunta.setTipoRespuesta(trespuesta);
				pregunta.setGre(gre);

				return pregunta;
			}

		});

		return lstPregunta;

	}

	@Override
	public List<Pregunta> findByDependencia(int cues) {
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", cues);

		String sql = "SELECT * from encuestas.preguntas p join encuestas.tipo_respuestas tr on p.tre_codigo =tr.tre_codigo"
				+ " join encuestas.cuestionarios c on p.cue_codigo=c.cue_codigo left join encuestas.grupo_escala g on p.gre_codigo= g.gre_codigo"
				+ "  where  pre_codigo_depende=:codigo AND p.pre_estado=1";

		List<Pregunta> lstPregunta = namedJdbcTemplate.query(sql, parameter, new RowMapper<Pregunta>() {

			@Override
			public Pregunta mapRow(ResultSet rs, int rowNum) throws SQLException {

				TipoRespuestas trespuesta = new TipoRespuestas();
				trespuesta.setCodigo(rs.getLong("tre_codigo"));
				trespuesta.setNombre(rs.getString("tre_nombre"));

				Cuestionario cue = new Cuestionario();
				cue.setCodigo(rs.getLong("cue_codigo"));
				cue.setNombre(rs.getString("cue_nombre"));

				GrupoEscala gre = new GrupoEscala();
				gre.setCodigo(rs.getLong("gre_codigo"));
				gre.setNombre(rs.getString("gre_nombre"));

				Pregunta pregunta = new Pregunta();
				pregunta.setCodigo(rs.getLong("pre_codigo"));
				pregunta.setDescripcion(rs.getString("pre_descripcion"));
				pregunta.setTextoAdicional(rs.getString("pre_texto_adicional"));
				pregunta.setTipo(rs.getInt("pre_tipo"));
				pregunta.setObligatorio(rs.getInt("pre_obligatorio"));
				pregunta.setDepende(rs.getInt("pre_codigo_depende"));
				pregunta.setIdentificador(rs.getString("pre_identificador"));
				pregunta.setCuestionario(cue);
				pregunta.setTipoRespuesta(trespuesta);
				pregunta.setGre(gre);

				return pregunta;
			}

		});

		return lstPregunta;

	}

	@Override
	public List<Pregunta> findByCueAdmin(int cues) {
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", cues);

		String sql = "SELECT * from encuestas.preguntas p join encuestas.tipo_respuestas tr on p.tre_codigo =tr.tre_codigo"
				+ " join encuestas.cuestionarios c on p.cue_codigo=c.cue_codigo left join encuestas.grupo_escala g on p.gre_codigo= g.gre_codigo"
				+ "  where c.cue_codigo=:codigo  AND p.pre_estado=1";

		List<Pregunta> lstPregunta = namedJdbcTemplate.query(sql, parameter, new RowMapper<Pregunta>() {

			@Override
			public Pregunta mapRow(ResultSet rs, int rowNum) throws SQLException {

				TipoRespuestas trespuesta = new TipoRespuestas();
				trespuesta.setCodigo(rs.getLong("tre_codigo"));
				trespuesta.setNombre(rs.getString("tre_nombre"));

				Cuestionario cue = new Cuestionario();
				cue.setCodigo(rs.getLong("cue_codigo"));
				cue.setNombre(rs.getString("cue_nombre"));

				GrupoEscala gre = new GrupoEscala();
				gre.setCodigo(rs.getLong("gre_codigo"));
				gre.setNombre(rs.getString("gre_nombre"));

				Pregunta pregunta = new Pregunta();
				pregunta.setCodigo(rs.getLong("pre_codigo"));
				pregunta.setDescripcion(rs.getString("pre_descripcion"));
				pregunta.setTextoAdicional(rs.getString("pre_texto_adicional"));
				pregunta.setTipo(rs.getInt("pre_tipo"));
				pregunta.setObligatorio(rs.getInt("pre_obligatorio"));
				pregunta.setDepende(rs.getInt("pre_codigo_depende"));
				pregunta.setIdentificador(rs.getString("pre_identificador"));
				pregunta.setCuestionario(cue);
				pregunta.setTipoRespuesta(trespuesta);
				pregunta.setGre(gre);

				return pregunta;
			}

		});

		return lstPregunta;

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
	public List<Pregunta> findByCuestionarioTipoPrincipal(int cuest) {
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", cuest);

		String sql = "select pre_codigo,pre_descripcion from encuestas.preguntas p where (cue_codigo =:codigo and pre_tipo = 0) and pre_estado = 1 and p.tre_codigo != 2";

		List<Pregunta> lstPregunta = namedJdbcTemplate.query(sql, parameter, new RowMapper<Pregunta>() {

			@Override
			public Pregunta mapRow(ResultSet rs, int rowNum) throws SQLException {

				Pregunta pregunta = new Pregunta();
				pregunta.setCodigo(rs.getLong("pre_codigo"));
				pregunta.setDescripcion(rs.getString("pre_descripcion"));

				return pregunta;
			}

		});

		return lstPregunta;
	}

}
