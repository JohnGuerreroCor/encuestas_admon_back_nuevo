package com.usco.edu.dao.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.IUaaDao;
import com.usco.edu.entities.Uaa;
import com.usco.edu.entities.UaaTipo;
import com.usco.edu.entities.UsuarioTipo;
import com.usco.edu.entities.Vinculo;

@Repository
public class UaaDaoImpl implements IUaaDao {

	@Value("${envuesta-variable}")
	private String valorConsulta;
	@Qualifier("JDBCTemplateConsultas")
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	@Qualifier("JDBCTemplateEncuestasConsulta")
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	@Override
	public List<Uaa> find(int codigoTipo) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", codigoTipo);

		String sql = "select * from uaa where uat_codigo=:codigo";

		List<Uaa> lstUaa = namedJdbcTemplate.query(sql, parameter, new RowMapper<Uaa>() {

			@Override
			public Uaa mapRow(ResultSet rs, int rowNum) throws SQLException {

				Uaa uaa = new Uaa();
				uaa.setCodigo(rs.getLong("uaa_codigo"));
				uaa.setNombre(rs.getString("uaa_nombre"));

				return uaa;
			}

		});

		return lstUaa;
	}

	@Override
	public List<UaaTipo> findUaaTipo() {

		String sql = "select * from uaa_tipo";

		List<UaaTipo> lstUaaTipo = namedJdbcTemplate.query(sql, new RowMapper<UaaTipo>() {

			@Override
			public UaaTipo mapRow(ResultSet rs, int rowNum) throws SQLException {

				UaaTipo uaa = new UaaTipo();
				uaa.setCodigo(rs.getLong("uat_codigo"));
				uaa.setNombre(rs.getString("uat_nombre"));

				return uaa;
			}

		});

		return lstUaaTipo;
	}

	List<Integer> filtrarValor() {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("consulta", valorConsulta);

		String sql2 = "select wep_valor from web_parametro where wep_nombre =:consulta";

		List<String> lstparametros = namedJdbcTemplate.query(sql2, parameter, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {

				String valor = rs.getString("wep_valor");

				return valor;
			}

		});

		String[] valores = lstparametros.get(0).split(",");

		List<Integer> valoresInt = new ArrayList<>();

		for (int i = 0; i < valores.length; i++) {

			valoresInt.add(Integer.parseInt(valores[i]));

		}

		return valoresInt;
	}

	@Override
	public List<UaaTipo> findUaaTipoWeb() {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("tiposUaa", filtrarValor());

		String sql = "select * from uaa_tipo where uat_codigo in (:tiposUaa)";

		List<UaaTipo> lstUaaTipo = namedJdbcTemplate.query(sql, parameter, new RowMapper<UaaTipo>() {

			@Override
			public UaaTipo mapRow(ResultSet rs, int rowNum) throws SQLException {

				UaaTipo uaa = new UaaTipo();
				uaa.setCodigo(rs.getLong("uat_codigo"));
				uaa.setNombre(rs.getString("uat_nombre"));

				return uaa;
			}

		});

		return lstUaaTipo;
	}

	@Override
	public Uaa findByCodigo(int codigo) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", codigo);

		String sql = "select * from uaa where uaa_codigo=:codigo";

		List<Uaa> lstUaa = namedJdbcTemplate.query(sql, parameter, new RowMapper<Uaa>() {

			@Override
			public Uaa mapRow(ResultSet rs, int rowNum) throws SQLException {

				UaaTipo uat = new UaaTipo();
				uat.setCodigo(rs.getLong("uat_codigo"));

				Uaa uaa = new Uaa();
				uaa.setCodigo(rs.getLong("uaa_codigo"));
				uaa.setNombre(rs.getString("uaa_nombre"));
				uaa.setUaaTipo(uat);

				return uaa;
			}

		});

		return lstUaa.get(0);
	}

	@Override
	public List<UsuarioTipo> findTus() {

		String sql = "select * from usuario_tipo ut ";
		List<UsuarioTipo> lstTus = namedJdbcTemplate.query(sql, new RowMapper<UsuarioTipo>() {

			@Override
			public UsuarioTipo mapRow(ResultSet rs, int rowNum) throws SQLException {

				UsuarioTipo tus = new UsuarioTipo();
				tus.setCodigo(rs.getLong("tus_codigo"));
				tus.setNombre(rs.getString("tus_nombre"));

				return tus;
			}

		});

		return lstTus;

	}

	String filtrarVin() {

		String sql2 = "select wep_valor from web_parametro where wep_nombre like 'RECURSO_HUMANO_VINCULACION_DOCENTE'";

		List<String> lstparametros = namedJdbcTemplate.query(sql2, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {

				String valor = rs.getString("wep_valor");

				return valor;
			}

		});

		String valores = lstparametros.get(0);

		return valores;
	}

	@Override
	public List<Vinculo> findVin() {

		String vinculaciones = filtrarVin();

		String sql = "select * from vinculacion where vin_codigo in (" + vinculaciones + ")";

		List<Vinculo> listaVinculo = jdbcTemplate.query(sql, new RowMapper<Vinculo>() {

			public Vinculo mapRow(ResultSet rs, int rowNum) throws SQLException {

				Vinculo vinculo = new Vinculo();

				vinculo.setNombre(rs.getString("vin_nombre"));
				vinculo.setCodigo(rs.getLong("vin_codigo"));

				return vinculo;
			}

		});
		return listaVinculo;
	}

}

