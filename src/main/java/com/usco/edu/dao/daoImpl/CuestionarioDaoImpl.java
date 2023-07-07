package com.usco.edu.dao.daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.ICuestionarioDao;
import com.usco.edu.entities.Cuestionario;
import com.usco.edu.entities.Uaa;
import com.usco.edu.util.AuditoriaJdbcTemplate;

@Repository
public class CuestionarioDaoImpl implements ICuestionarioDao {

	@Autowired
	@Qualifier("JDBCTemplateEncuestasConsulta")
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	@Autowired
	private AuditoriaJdbcTemplate jdbcComponent;

	@Override
	public List<Cuestionario> find(int uaa) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("uaa", uaa);

		String sql = "SELECT cue_codigo,cue_estado, cue_nombre,cue_instrucciones,uaa_nombre,cue_fecha_fin,cue_fecha_inicio,c.uaa_codigo"
				+ " from encuestas.cuestionarios c join dbo.uaa u on c.uaa_codigo= u.uaa_codigo where c.cue_estado = 1";
		
		//String sql = "SELECT cue_codigo,cue_estado, cue_nombre,cue_instrucciones,uaa_nombre,cue_fecha_fin,cue_fecha_inicio,c.uaa_codigo"
			//	+ " from encuestas.cuestionarios c join dbo.uaa u on c.uaa_codigo= u.uaa_codigo where c.cue_estado =1 AND c.uaa_codigo=:uaa";

		List<Cuestionario> lstCuestionario = namedJdbcTemplate.query(sql, parameter, new RowMapper<Cuestionario>() {

			@Override
			public Cuestionario mapRow(ResultSet rs, int rowNum) throws SQLException {

				Uaa uaa = new Uaa();
				uaa.setCodigo(rs.getLong("uaa_codigo"));
				uaa.setNombre(rs.getString("uaa_nombre"));

				Cuestionario cuestionario = new Cuestionario();
				cuestionario.setCodigo(rs.getLong("cue_codigo"));
				cuestionario.setNombre(rs.getString("cue_nombre"));
				cuestionario.setInstrucciones(rs.getString("cue_instrucciones"));
				cuestionario.setEstado(rs.getInt("cue_estado"));
				cuestionario.setInicio(rs.getDate("cue_fecha_inicio"));
				cuestionario.setFin(rs.getDate("cue_fecha_fin"));
				cuestionario.setUaa(uaa);

				return cuestionario;
			}

		});

		return lstCuestionario;
	}

	@Override
	public Cuestionario findByCodigo(Long codigo) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", codigo);

		String sql = "SELECT cue_codigo,cue_estado, cue_nombre,cue_instrucciones,uaa_nombre,cue_fecha_fin,cue_fecha_inicio,c.uaa_codigo"
				+ " from encuestas.cuestionarios c join dbo.uaa u on c.uaa_codigo= u.uaa_codigo where  c.cue_codigo=:codigo";

		List<Cuestionario> lstCuestionario = namedJdbcTemplate.query(sql, parameter, new RowMapper<Cuestionario>() {

			@Override
			public Cuestionario mapRow(ResultSet rs, int rowNum) throws SQLException {

				Uaa uaa = new Uaa();
				uaa.setCodigo(rs.getLong("uaa_codigo"));
				uaa.setNombre(rs.getString("uaa_nombre"));

				Cuestionario cuestionario = new Cuestionario();
				cuestionario.setCodigo(rs.getLong("cue_codigo"));
				cuestionario.setNombre(rs.getString("cue_nombre"));
				cuestionario.setInstrucciones(rs.getString("cue_instrucciones"));
				cuestionario.setEstado(rs.getInt("cue_estado"));
				cuestionario.setInicio(rs.getDate("cue_fecha_inicio"));
				cuestionario.setFin(rs.getDate("cue_fecha_fin"));
				cuestionario.setUaa(uaa);

				return cuestionario;
			}

		});

		return lstCuestionario.get(0);
	}

	@Override
	public void createCompleto(Cuestionario cuestionario, String userdb) {

		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(cuestionario.getFin());
		calendar.set(calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);

		Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());

		String sql = "INSERT INTO encuestas.cuestionarios (cue_nombre, cue_instrucciones, uaa_codigo, cue_fecha_inicio, cue_fecha_fin)"
				+ " VALUES(:nombre, :instrucciones, :uaa, :inicio, :fin)";

		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();
			parameter.addValue("nombre", cuestionario.getNombre());
			parameter.addValue("instrucciones", cuestionario.getInstrucciones());
			parameter.addValue("uaa", cuestionario.getUaa().getCodigo());

			parameter.addValue("inicio", cuestionario.getInicio());
			parameter.addValue("fin", timestamp);

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
	public void createFin(Cuestionario cuestionario, String userdb) {

		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(cuestionario.getFin());
		calendar.set(calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);

		Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());

		String sql = "INSERT INTO encuestas.cuestionarios (cue_nombre, cue_instrucciones, uaa_codigo,  cue_fecha_fin)"
				+ " VALUES(:nombre, :instrucciones, :uaa,  :fin)";

		try {

			MapSqlParameterSource parameter = new MapSqlParameterSource();
			parameter.addValue("nombre", cuestionario.getNombre());
			parameter.addValue("instrucciones", cuestionario.getInstrucciones());
			parameter.addValue("uaa", cuestionario.getUaa().getCodigo());

			parameter.addValue("fin", timestamp);

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
	public void crearNotNull(Cuestionario cuestionario, String userdb) {

		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		String sql = "INSERT INTO encuestas.cuestionarios (cue_nombre, cue_instrucciones, uaa_codigo)"
				+ " VALUES(:nombre, :instrucciones, :uaa)";

		try {
			MapSqlParameterSource parameter = new MapSqlParameterSource();
			parameter.addValue("nombre", cuestionario.getNombre());
			parameter.addValue("instrucciones", cuestionario.getInstrucciones());
			parameter.addValue("uaa", cuestionario.getUaa().getCodigo());

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
	public void createInicio(Cuestionario cuestionario, String userdb) {

		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		String sql = "INSERT INTO encuestas.cuestionarios (cue_nombre, cue_instrucciones, uaa_codigo,cue_fecha_inicio)"
				+ " VALUES(:nombre, :instrucciones, :uaa, :inicio)";

		try {
			MapSqlParameterSource parameter = new MapSqlParameterSource();
			parameter.addValue("nombre", cuestionario.getNombre());
			parameter.addValue("instrucciones", cuestionario.getInstrucciones());
			parameter.addValue("uaa", cuestionario.getUaa().getCodigo());

			parameter.addValue("inicio", cuestionario.getInicio());
			parameter.addValue("fin", cuestionario.getFin());

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
	public void update(Cuestionario cuestionario, String userdb) {

		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(cuestionario.getFin());
		calendar.set(calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);

		Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());

		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		String sql = "UPDATE encuestas.cuestionarios SET cue_nombre=:nombre, cue_instrucciones=:instrucciones,"
				+ " uaa_codigo=:uaa, cue_fecha_inicio=:inicio, cue_fecha_fin=:fin WHERE cue_codigo=:codigo";

		try {
			MapSqlParameterSource parameter = new MapSqlParameterSource();
			parameter.addValue("codigo", cuestionario.getCodigo());
			parameter.addValue("nombre", cuestionario.getNombre());
			parameter.addValue("instrucciones", cuestionario.getInstrucciones());
			parameter.addValue("uaa", cuestionario.getUaa().getCodigo());
			parameter.addValue("inicio", cuestionario.getInicio());
			parameter.addValue("fin", timestamp);

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

		String sql = "UPDATE encuestas.cuestionarios SET cue_estado = 0 WHERE cue_codigo=:codigo";

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
