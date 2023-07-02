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

import com.usco.edu.dao.IPreguntaRespuestasDao;
import com.usco.edu.entities.Pregunta;
import com.usco.edu.entities.PreguntaRespuestas;
import com.usco.edu.entities.RespuestaOpciones;
import com.usco.edu.entities.TipoRespuestas;
import com.usco.edu.util.AuditoriaJdbcTemplate;

@Repository
public class PreguntaRespuestaDaoImpl implements IPreguntaRespuestasDao {

	@Autowired
	@Qualifier("JDBCTemplateEncuestasConsulta")
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	@Autowired
	private AuditoriaJdbcTemplate jdbcComponent;

	@Override
	public List<PreguntaRespuestas> find() {

		String sql = "SELECT prr_codigo, pr.rop_codigo, pr.pre_codigo, prr_estado, prr_descripcion_adicional, pr.pre_codigo_depende, pr.tre_codigo,"
				+ "tre_nombre,rop_descripcion,pre_descripcion FROM encuestas.preguntas_respuestas pr join encuestas.preguntas p on"
				+ " pr.pre_codigo =p.pre_codigo join encuestas.respuestas_opciones ro on pr.rop_codigo =ro.rop_codigo join"
				+ " encuestas.tipo_respuestas tr on pr.tre_codigo =tr.tre_codigo where pr.prr_estado =1";

		List<PreguntaRespuestas> lstPreguntaRespuestas = namedJdbcTemplate.query(sql,
				new RowMapper<PreguntaRespuestas>() {

					@Override
					public PreguntaRespuestas mapRow(ResultSet rs, int rowNum) throws SQLException {

						TipoRespuestas tipoRespuestas = new TipoRespuestas();
						tipoRespuestas.setCodigo(rs.getLong("tre_codigo"));
						tipoRespuestas.setNombre(rs.getString("tre_nombre"));

						RespuestaOpciones respuestaOpciones = new RespuestaOpciones();
						respuestaOpciones.setCodigo(rs.getLong("rop_codigo"));
						respuestaOpciones.setDescripcion(rs.getString("rop_descripcion"));

						Pregunta pregunta = new Pregunta();
						pregunta.setCodigo(rs.getLong("pre_codigo"));
						pregunta.setDescripcion(rs.getString("pre_descripcion"));

						PreguntaRespuestas preguntaRespuestas = new PreguntaRespuestas();
						preguntaRespuestas.setCodigo(rs.getLong("prr_codigo"));
						preguntaRespuestas.setDepende(rs.getInt("pre_codigo_depende"));
						preguntaRespuestas.setDescripcionAdicional(rs.getString("prr_descripcion_adicional"));
						preguntaRespuestas.setPregunta(pregunta);
						preguntaRespuestas.setRespuestaOpciones(respuestaOpciones);
						preguntaRespuestas.setTipoRespuestas(tipoRespuestas);
						return preguntaRespuestas;
					}

				});

		return lstPreguntaRespuestas;

	}

	@Override
	public List<PreguntaRespuestas> findByCuestionario(Long codigo) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", codigo);

		String sql = "SELECT prr_codigo, pr.rop_codigo, pr.pre_codigo, prr_estado, prr_descripcion_adicional, pr.pre_codigo_depende,"
				+ "  pr.tre_codigo,tre_nombre,rop_descripcion,pre_descripcion FROM encuestas.preguntas_respuestas pr join encuestas.preguntas p on"
				+ "  pr.pre_codigo =p.pre_codigo full join encuestas.respuestas_opciones ro on pr.rop_codigo =ro.rop_codigo full join encuestas.tipo_respuestas "
				+ " tr on pr.tre_codigo =tr.tre_codigo where pr.prr_estado =1 AND p.cue_codigo = :codigo";

		List<PreguntaRespuestas> lstPreguntaRespuestas = namedJdbcTemplate.query(sql, parameter,
				new RowMapper<PreguntaRespuestas>() {

					@Override
					public PreguntaRespuestas mapRow(ResultSet rs, int rowNum) throws SQLException {

						TipoRespuestas tipoRespuestas = new TipoRespuestas();
						tipoRespuestas.setCodigo(rs.getLong("tre_codigo"));
						tipoRespuestas.setNombre(rs.getString("tre_nombre"));

						RespuestaOpciones respuestaOpciones = new RespuestaOpciones();
						respuestaOpciones.setCodigo(rs.getLong("rop_codigo"));
						respuestaOpciones.setDescripcion(rs.getString("rop_descripcion"));

						Pregunta pregunta = new Pregunta();
						pregunta.setCodigo(rs.getLong("pre_codigo"));
						pregunta.setDescripcion(rs.getString("pre_descripcion"));

						PreguntaRespuestas preguntaRespuestas = new PreguntaRespuestas();
						preguntaRespuestas.setCodigo(rs.getLong("prr_codigo"));
						preguntaRespuestas.setDepende(rs.getInt("pre_codigo_depende"));
						preguntaRespuestas.setDescripcionAdicional(rs.getString("prr_descripcion_adicional"));
						preguntaRespuestas.setPregunta(pregunta);
						preguntaRespuestas.setRespuestaOpciones(respuestaOpciones);
						preguntaRespuestas.setTipoRespuestas(tipoRespuestas);
						return preguntaRespuestas;
					}

				});

		return lstPreguntaRespuestas;

	}

	@Override
	public PreguntaRespuestas findByCodigo(Long codigo) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", codigo);

		String sql = "SELECT prr_codigo, pr.rop_codigo, pr.pre_codigo, prr_estado, prr_descripcion_adicional, pr.pre_codigo_depende, pr.tre_codigo,"
				+ "tre_nombre,rop_descripcion,pre_descripcion FROM encuestas.preguntas_respuestas pr join encuestas.preguntas p on"
				+ " pr.pre_codigo =p.pre_codigo full join encuestas.respuestas_opciones ro on pr.rop_codigo =ro.rop_codigo join"
				+ " encuestas.tipo_respuestas tr on pr.tre_codigo =tr.tre_codigo where pr.prr_estado =1 AND prr_codigo=:codigo";

		List<PreguntaRespuestas> lstPreguntaRespuestas = namedJdbcTemplate.query(sql, parameter,
				new RowMapper<PreguntaRespuestas>() {

					@Override
					public PreguntaRespuestas mapRow(ResultSet rs, int rowNum) throws SQLException {

						TipoRespuestas tipoRespuestas = new TipoRespuestas();
						tipoRespuestas.setCodigo(rs.getLong("tre_codigo"));
						tipoRespuestas.setNombre(rs.getString("tre_nombre"));

						RespuestaOpciones respuestaOpciones = new RespuestaOpciones();
						respuestaOpciones.setCodigo(rs.getLong("rop_codigo"));
						respuestaOpciones.setDescripcion(rs.getString("rop_descripcion"));

						Pregunta pregunta = new Pregunta();
						pregunta.setCodigo(rs.getLong("pre_codigo"));
						pregunta.setDescripcion(rs.getString("pre_descripcion"));

						PreguntaRespuestas preguntaRespuestas = new PreguntaRespuestas();
						preguntaRespuestas.setCodigo(rs.getLong("prr_codigo"));
						preguntaRespuestas.setDepende(rs.getInt("pre_codigo_depende"));
						preguntaRespuestas.setDescripcionAdicional(rs.getString("prr_descripcion_adicional"));
						preguntaRespuestas.setPregunta(pregunta);
						preguntaRespuestas.setRespuestaOpciones(respuestaOpciones);
						preguntaRespuestas.setTipoRespuestas(tipoRespuestas);
						return preguntaRespuestas;
					}

				});

		return lstPreguntaRespuestas.get(0);
	}

	@Override
	public void create(PreguntaRespuestas preguntaRespuestas, String userdb) {

		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		System.out.print(preguntaRespuestas.getRespuestaOpciones().getCodigo() + ", "
				+ preguntaRespuestas.getTipoRespuestas().getCodigo() + "\n");

		String sql = "INSERT INTO encuestas.preguntas_respuestas (rop_codigo, pre_codigo, tre_codigo)"
				+ " VALUES(:rop, :pregunta, :tre)";

		try {
			MapSqlParameterSource parameter = new MapSqlParameterSource();

			parameter.addValue("pregunta", preguntaRespuestas.getPregunta().getCodigo());

			parameter.addValue("rop", preguntaRespuestas.getRespuestaOpciones().getCodigo(), Types.INTEGER);

			// parameter.addValue("descripcion",
			// preguntaRespuestas.getDescripcionAdicional(), Types.VARCHAR);
			// parameter.addValue("depende", preguntaRespuestas.getDepende(),
			// Types.INTEGER);
			parameter.addValue("tre", preguntaRespuestas.getTipoRespuestas().getCodigo());

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
	public void update(PreguntaRespuestas preguntaRespuestas, String userdb) {

		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		String sql = "UPDATE encuestas.preguntas_respuestas SET rop_codigo=:rop, pre_codigo=:pregunta,"
				+ "  tre_codigo=:tre WHERE prr_codigo=:codigo";

		try {
			MapSqlParameterSource parameter = new MapSqlParameterSource();
			parameter.addValue("codigo", preguntaRespuestas.getCodigo());
			parameter.addValue("rop", preguntaRespuestas.getRespuestaOpciones().getCodigo());
			parameter.addValue("pregunta", preguntaRespuestas.getPregunta().getCodigo());
			parameter.addValue("tre", preguntaRespuestas.getTipoRespuestas().getCodigo());
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
	public void updateAgg(PreguntaRespuestas preguntaRespuestas, String userdb) {
		System.out.println("Se Supone que entor");
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		String sql = "UPDATE encuestas.preguntas_respuestas SET rop_codigo=:rop, pre_codigo=:pregunta, prr_descripcion_adicional=:descripcion,"
				+ " pre_codigo_depende=:depende, tre_codigo=:tre WHERE prr_codigo=:codigo";

		try {
			MapSqlParameterSource parameter = new MapSqlParameterSource();
			parameter.addValue("codigo", preguntaRespuestas.getCodigo());
			parameter.addValue("rop", preguntaRespuestas.getRespuestaOpciones().getCodigo());
			parameter.addValue("pregunta", preguntaRespuestas.getPregunta().getCodigo());
			parameter.addValue("descripcion", preguntaRespuestas.getDescripcionAdicional());
			parameter.addValue("depende", preguntaRespuestas.getDepende());
			parameter.addValue("tre", preguntaRespuestas.getTipoRespuestas().getCodigo());
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

		String sql = "UPDATE encuestas.preguntas_respuestas SET  prr_estado=0  WHERE prr_codigo=:codigo";

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
	public List<PreguntaRespuestas> findbyPregunta(int codigo) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", codigo);

		String sql = "SELECT prr_codigo, pr.rop_codigo, pr.pre_codigo, prr_estado, prr_descripcion_adicional, pr.pre_codigo_depende,"
				+ " rop_descripcion,pre_descripcion FROM encuestas.preguntas_respuestas pr right join encuestas.preguntas p on pr.pre_codigo =p.pre_codigo "
				+ "full join encuestas.respuestas_opciones ro on pr.rop_codigo =ro.rop_codigo  where  p.pre_codigo =:codigo AND pr.prr_estado =1";

		List<PreguntaRespuestas> lstPreguntaRespuestas = namedJdbcTemplate.query(sql, parameter,
				new RowMapper<PreguntaRespuestas>() {

					@Override
					public PreguntaRespuestas mapRow(ResultSet rs, int rowNum) throws SQLException {

						RespuestaOpciones respuestaOpciones = new RespuestaOpciones();
						respuestaOpciones.setCodigo(rs.getLong("rop_codigo"));
						respuestaOpciones.setDescripcion(rs.getString("rop_descripcion"));

						Pregunta pregunta = new Pregunta();
						pregunta.setCodigo(rs.getLong("pre_codigo"));
						pregunta.setDescripcion(rs.getString("pre_descripcion"));

						PreguntaRespuestas preguntaRespuestas = new PreguntaRespuestas();
						preguntaRespuestas.setCodigo(rs.getLong("prr_codigo"));
						preguntaRespuestas.setDepende(rs.getInt("pre_codigo_depende"));
						preguntaRespuestas.setDescripcionAdicional(rs.getString("prr_descripcion_adicional"));
						preguntaRespuestas.setPregunta(pregunta);
						preguntaRespuestas.setRespuestaOpciones(respuestaOpciones);

						return preguntaRespuestas;
					}

				});

		return lstPreguntaRespuestas;

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
