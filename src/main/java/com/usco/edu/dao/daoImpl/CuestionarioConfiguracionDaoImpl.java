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

import com.usco.edu.dao.ICuestionarioConfiguracionDao;
import com.usco.edu.entities.Cuestionario;
import com.usco.edu.entities.CuestionarioConfiguracion;
import com.usco.edu.entities.Uaa;
import com.usco.edu.entities.UsuarioTipo;
import com.usco.edu.entities.Vinculo;
import com.usco.edu.util.AuditoriaJdbcTemplate;

@Repository
public class CuestionarioConfiguracionDaoImpl implements ICuestionarioConfiguracionDao {

	@Autowired
	@Qualifier("JDBCTemplateEncuestasConsulta")
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	@Autowired
	private AuditoriaJdbcTemplate jdbcComponent;

	@Override
	public List<CuestionarioConfiguracion> find(int uaa) {
		
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("uaa", uaa);

		String sql = "SELECT cuc_codigo, cuc_estado, ca.uaa_codigo, ca.cue_codigo,us.tus_codigo,us.tus_nombre, fecha_registro,cue_nombre,uaa_nombre,v.vin_codigo,"
				+ "vin_nombre FROM " + "encuestas.cuestionario_configuracion ca   join encuestas.cuestionarios c on "
				+ " ca.cue_codigo =c.cue_codigo full join dbo.uaa u on ca.uaa_codigo =u.uaa_codigo   join"
				+ "	 dbo.usuario_tipo us on ca.tus_codigo =us.tus_codigo left join vinculacion v on v.vin_codigo=ca.vin_codigo  where cuc_estado=1 AND c.uaa_codigo=:uaa";

		List<CuestionarioConfiguracion> lstCuestionario = namedJdbcTemplate.query(sql,parameter,
				new RowMapper<CuestionarioConfiguracion>() {

					@Override
					public CuestionarioConfiguracion mapRow(ResultSet rs, int rowNum) throws SQLException {

						Vinculo vin = new Vinculo();
						vin.setCodigo(rs.getLong("vin_codigo"));
						vin.setNombre(rs.getString("vin_nombre"));

						Uaa uaa = new Uaa();
						uaa.setCodigo(rs.getLong("uaa_codigo"));
						uaa.setNombre(rs.getString("uaa_nombre"));

						UsuarioTipo tus = new UsuarioTipo();
						tus.setNombre(rs.getString("tus_nombre"));
						tus.setCodigo(rs.getLong("tus_codigo"));

						Cuestionario cuestionario = new Cuestionario();
						cuestionario.setCodigo(rs.getLong("cue_codigo"));
						cuestionario.setNombre(rs.getString("cue_nombre"));

						CuestionarioConfiguracion cuestionarioConfiguracion = new CuestionarioConfiguracion();
						cuestionarioConfiguracion.setCodigo(rs.getLong("cuc_codigo"));
						cuestionarioConfiguracion.setFechaRegistro(rs.getDate("fecha_registro"));
						cuestionarioConfiguracion.setUsuarioTipo(tus);
						cuestionarioConfiguracion.setUaa(uaa);
						cuestionarioConfiguracion.setCuestionario(cuestionario);
						cuestionarioConfiguracion.setVinculo(vin);

						return cuestionarioConfiguracion;
					}

				});

		return lstCuestionario;
	}

	@Override
	public List<CuestionarioConfiguracion> findByCodigo(Long codigo) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", codigo);

		String sql = "SELECT cuc_codigo, cuc_estado, ca.uaa_codigo, ca.cue_codigo,us.tus_codigo,us.tus_nombre, fecha_registro,cue_nombre,uaa_nombre FROM "
				+ "encuestas.cuestionario_configuracion ca   join encuestas.cuestionarios c on "
				+ " ca.cue_codigo =c.cue_codigo full join dbo.uaa u on ca.uaa_codigo =u.uaa_codigo   join"
				+ "	 dbo.usuario_tipo us on ca.tus_codigo =us.tus_codigo  where cuc_estado=1 AND c.cue_codigo=:codigo";

		List<CuestionarioConfiguracion> lstCuestionario = namedJdbcTemplate.query(sql, parameter,
				new RowMapper<CuestionarioConfiguracion>() {

					@Override
					public CuestionarioConfiguracion mapRow(ResultSet rs, int rowNum) throws SQLException {

						Uaa uaa = new Uaa();
						uaa.setCodigo(rs.getLong("uaa_codigo"));
						uaa.setNombre(rs.getString("uaa_nombre"));

						UsuarioTipo tus = new UsuarioTipo();
						tus.setNombre(rs.getString("tus_nombre"));
						tus.setCodigo(rs.getLong("tus_codigo"));

						Cuestionario cuestionario = new Cuestionario();
						cuestionario.setCodigo(rs.getLong("cue_codigo"));
						cuestionario.setNombre(rs.getString("cue_nombre"));

						CuestionarioConfiguracion cuestionarioConfiguracion = new CuestionarioConfiguracion();
						cuestionarioConfiguracion.setCodigo(rs.getLong("cuc_codigo"));
						cuestionarioConfiguracion.setFechaRegistro(rs.getDate("fecha_registro"));
						cuestionarioConfiguracion.setUsuarioTipo(tus);
						cuestionarioConfiguracion.setUaa(uaa);
						cuestionarioConfiguracion.setCuestionario(cuestionario);

						return cuestionarioConfiguracion;
					}

				});

		return lstCuestionario;

	}

	@Override
	public void create(CuestionarioConfiguracion cuestionarioConfiguracion, String userdb) {

		String sql = "INSERT INTO encuestas.cuestionario_configuracion ( uaa_codigo, cue_codigo, tus_codigo,vin_codigo) VALUES( :uaa, :cuestionario,:tus,:vin )";
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		try {
			MapSqlParameterSource parameter = new MapSqlParameterSource();

			parameter.addValue("uaa", cuestionarioConfiguracion.getUaa().getCodigo(), Types.INTEGER);
			parameter.addValue("cuestionario", cuestionarioConfiguracion.getCuestionario().getCodigo());
			parameter.addValue("tus", cuestionarioConfiguracion.getUsuarioTipo().getCodigo());
			parameter.addValue("vin", cuestionarioConfiguracion.getVinculo().getCodigo(), Types.INTEGER);

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
	public void update(CuestionarioConfiguracion cuestionarioConfiguracion, String userdb) {

		String sql = "UPDATE encuestas.cuestionario_configuracion SET  uaa_codigo=:uaa, cue_codigo=:cuestionario,vin_codigo=:vin,"
				+ " tus_codigo=:tus WHERE cuc_codigo=:codigo";

		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);
		try {
			MapSqlParameterSource parameter = new MapSqlParameterSource();
			parameter.addValue("codigo", cuestionarioConfiguracion.getCodigo());
			parameter.addValue("uaa", cuestionarioConfiguracion.getUaa().getCodigo(), Types.INTEGER);
			parameter.addValue("cuestionario", cuestionarioConfiguracion.getCuestionario().getCodigo());
			parameter.addValue("tus", cuestionarioConfiguracion.getUsuarioTipo().getCodigo());
			if (cuestionarioConfiguracion.getVinculo().getCodigo() > 0) {
				parameter.addValue("vin", cuestionarioConfiguracion.getVinculo().getCodigo(), Types.INTEGER);
			} else {
				parameter.addValue("vin", null, Types.NULL);
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
	public void delete(int codigo, String userdb) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		String sql = "UPDATE encuestas.cuestionario_configuracion SET  cuc_estado=0  WHERE cuc_codigo=:codigo";

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
