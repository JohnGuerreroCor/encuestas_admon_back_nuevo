package com.usco.edu.dao.daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.IEscalaDao;
import com.usco.edu.entities.Escala;
import com.usco.edu.entities.GrupoEscala;
import com.usco.edu.util.AuditoriaJdbcTemplate;

@Repository
public class EscalaDaoImpl implements IEscalaDao {

	@Autowired
	@Qualifier("JDBCTemplateEncuestasConsulta")
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	@Autowired
	private AuditoriaJdbcTemplate jdbcComponent;

	@Override
	public List<Escala> find(int codigo) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();

		parameter.addValue("codigo", codigo);
		// TODO Auto-generated method stub
		String sql = "select * from encuestas.escala where esc_estado=1 AND gre_codigo=:codigo ";

		List<Escala> lstEscala = namedJdbcTemplate.query(sql, parameter, new RowMapper<Escala>() {

			@Override
			public Escala mapRow(ResultSet rs, int rowNum) throws SQLException {

				GrupoEscala gre = new GrupoEscala();
				gre.setCodigo(rs.getLong("gre_codigo"));

				Escala escala = new Escala();
				escala.setCodigo(rs.getLong("esc_codigo"));
				escala.setNombre(rs.getString("esc_nombre"));
				escala.setValor(rs.getInt("esc_valor"));
				escala.setGre(gre);

				return escala;
			}

		});

		return lstEscala;
	}

	@Override
	public void insert(Escala escala, String userdb) {
		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		String sql = "INSERT INTO encuestas.escala (esc_nombre, esc_valor, gre_codigo) VALUES(:nombre, :valor, :gre) ";

		try {
			MapSqlParameterSource parameter = new MapSqlParameterSource();

			parameter.addValue("nombre", escala.getNombre());
			parameter.addValue("valor", escala.getValor());
			parameter.addValue("gre", escala.getGre().getCodigo());

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
	public void update(Escala escala, String userdb) {

		DataSource dataSource = jdbcComponent.construirDataSourceDeUsuario(userdb);
		NamedParameterJdbcTemplate jdbc = jdbcComponent.construirTemplatenew(dataSource);

		String sql = "UPDATE encuestas.escala SET esc_nombre=:nombre, esc_valor=:valor, gre_codigo=:gre WHERE esc_codigo=:codigo";

		try {
			MapSqlParameterSource parameter = new MapSqlParameterSource();

			parameter.addValue("nombre", escala.getNombre());
			parameter.addValue("valor", escala.getValor());
			parameter.addValue("gre", escala.getGre().getCodigo());
			parameter.addValue("codigo", escala.getCodigo());

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

		String sql = "UPDATE encuestas.escala SET esc_estado=0 WHERE esc_codigo=:codigo";

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
