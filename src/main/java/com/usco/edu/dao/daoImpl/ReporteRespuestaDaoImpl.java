package com.usco.edu.dao.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.IReporteRespuestasDao;
import com.usco.edu.dto.ReporteAgrupado;
import com.usco.edu.dto.ReporteRespuesta;
import com.usco.edu.dto.ReporteRespuestaDetallado;
import com.usco.edu.entities.Cuestionario;
import com.usco.edu.entities.Pregunta;
import com.usco.edu.entities.RespuestaOpciones;
import com.usco.edu.entities.Resultado;
import com.usco.edu.entities.UsuarioTipo;
import com.usco.edu.entities.Persona;
import com.usco.edu.entities.Estudiante;
import com.usco.edu.entities.Uaa;
import com.usco.edu.entities.Sede;

@Repository
public class ReporteRespuestaDaoImpl implements IReporteRespuestasDao {

	@Autowired
	@Qualifier("JDBCTemplateEncuestasConsulta")
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	
	// GENERAL ACREDITACION
	

	@Override
	public List<ReporteRespuesta> generarDatosReporteTodos(int uaa) {
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("uaa", uaa);

		String sql = "select ro.rop_descripcion,p.pre_descripcion,p.pre_identificador,c.cue_nombre,tu.tus_nombre from encuestas.respuestas r"
				+ " join encuestas.respuestas_cuestionarios rc on r.rcu_codigo = rc.rcu_codigo "
				+ "join encuestas.preguntas_respuestas pr on r.prr_codigo = pr.prr_codigo "
				+ "join encuestas.respuestas_opciones ro on pr.rop_codigo = ro.rop_codigo "
				+ "join encuestas.preguntas p on r.pre_codigo =p.pre_codigo "
				+ "join encuestas.cuestionarios c on rc.cue_codigo = c.cue_codigo "
				+ "join encuestas.cuestionario_configuracion cc on cc.cue_codigo = c.cue_codigo "
				+ "join dbo.usuario_tipo tu  on cc.tus_codigo = tu.tus_codigo  where c.uaa_codigo =:uaa";

		List<ReporteRespuesta> lstReporteRespuesta = namedJdbcTemplate.query(sql, parameter,
				new RowMapper<ReporteRespuesta>() {

					@Override
					public ReporteRespuesta mapRow(ResultSet rs, int rowNum) throws SQLException {

						RespuestaOpciones rop = new RespuestaOpciones();
						rop.setDescripcion(rs.getString("rop_descripcion"));

						Cuestionario cuestionario = new Cuestionario();
						cuestionario.setNombre(rs.getString("cue_nombre"));

						Pregunta pregunta = new Pregunta();
						pregunta.setDescripcion(rs.getString("pre_descripcion"));
						pregunta.setIdentificador(rs.getString("pre_identificador"));

						UsuarioTipo tus = new UsuarioTipo();
						tus.setNombre(rs.getString("tus_nombre"));

						ReporteRespuesta rr = new ReporteRespuesta();
						rr.setCue(cuestionario);
						rr.setPregunta(pregunta);
						rr.setRop(rop);
						rr.setTus(tus);

						return rr;
					}

				});

		return lstReporteRespuesta;
	}
	
	@Override
	public List<ReporteRespuesta> generarDatosReportePrograma(int programa, int uaa) {
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("uaa", uaa);
		parameter.addValue("programa", programa);

		String sql = "select ro.rop_descripcion,p.pre_descripcion,p.pre_identificador,c.cue_nombre,tu.tus_nombre,per.per_nombre,u.uaa_nombre from encuestas.respuestas r "
				+ "join encuestas.respuestas_cuestionarios rc on r.rcu_codigo = rc.rcu_codigo "
				+ "join dbo.persona per on rc.per_codigo =per.per_codigo "
				+ "join dbo.estudiante e on e.per_codigo =per.per_codigo "
				+ "join dbo.programa pro on e.pro_codigo =pro.pro_codigo "
				+ "join dbo.uaa u on pro.uaa_codigo =u.uaa_codigo "
				+ "join encuestas.preguntas_respuestas pr on r.prr_codigo = pr.prr_codigo "
				+ "join encuestas.respuestas_opciones ro on pr.rop_codigo = ro.rop_codigo "
				+ "join encuestas.preguntas p on r.pre_codigo =p.pre_codigo "
				+ "join encuestas.cuestionarios c on rc.cue_codigo = c.cue_codigo "
				+ "join encuestas.cuestionario_configuracion cc on cc.cue_codigo = c.cue_codigo "
				+ "join dbo.usuario_tipo tu on cc.tus_codigo = tu.tus_codigo where (tu.tus_codigo = 2 and pro.pro_codigo=:programa ) and c.uaa_codigo =:uaa";

		List<ReporteRespuesta> lstReporteRespuesta = namedJdbcTemplate.query(sql, parameter,
				new RowMapper<ReporteRespuesta>() {

					@Override
					public ReporteRespuesta mapRow(ResultSet rs, int rowNum) throws SQLException {

						RespuestaOpciones rop = new RespuestaOpciones();
						rop.setDescripcion(rs.getString("rop_descripcion"));

						Cuestionario cuestionario = new Cuestionario();
						cuestionario.setNombre(rs.getString("cue_nombre"));

						Pregunta pregunta = new Pregunta();
						pregunta.setDescripcion(rs.getString("pre_descripcion"));
						pregunta.setIdentificador(rs.getString("pre_identificador"));

						UsuarioTipo tus = new UsuarioTipo();
						tus.setNombre(rs.getString("tus_nombre"));

						ReporteRespuesta rr = new ReporteRespuesta();
						rr.setCue(cuestionario);
						rr.setPregunta(pregunta);
						rr.setRop(rop);
						rr.setTus(tus);

						return rr;
					}

				});

		return lstReporteRespuesta;
	}

	@Override
	public List<ReporteRespuesta> generarDatosReportePorTipoUsuario(int tus, int uaa) {
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("uaa", uaa);
		parameter.addValue("codigo", tus);

		String sql = "select ro.rop_descripcion,p.pre_descripcion,p.pre_identificador,c.cue_nombre,tu.tus_nombre from encuestas.respuestas r"
				+ " join encuestas.respuestas_cuestionarios rc on r.rcu_codigo = rc.rcu_codigo "
				+ "join encuestas.preguntas_respuestas pr on r.prr_codigo = pr.prr_codigo "
				+ "join encuestas.respuestas_opciones ro on pr.rop_codigo = ro.rop_codigo "
				+ "join encuestas.preguntas p on r.pre_codigo =p.pre_codigo "
				+ "join encuestas.cuestionarios c on rc.cue_codigo = c.cue_codigo "
				+ "join encuestas.cuestionario_configuracion cc on cc.cue_codigo = c.cue_codigo "
				+ "join dbo.usuario_tipo tu  on cc.tus_codigo = tu.tus_codigo  where c.uaa_codigo =:uaa AND tu.tus_codigo=:codigo";

		List<ReporteRespuesta> lstReporteRespuesta = namedJdbcTemplate.query(sql, parameter,
				new RowMapper<ReporteRespuesta>() {

					@Override
					public ReporteRespuesta mapRow(ResultSet rs, int rowNum) throws SQLException {

						RespuestaOpciones rop = new RespuestaOpciones();
						rop.setDescripcion(rs.getString("rop_descripcion"));

						Cuestionario cuestionario = new Cuestionario();
						cuestionario.setNombre(rs.getString("cue_nombre"));

						Pregunta pregunta = new Pregunta();
						pregunta.setDescripcion(rs.getString("pre_descripcion"));
						pregunta.setIdentificador(rs.getString("pre_identificador"));

						UsuarioTipo tus = new UsuarioTipo();
						tus.setNombre(rs.getString("tus_nombre"));

						ReporteRespuesta rr = new ReporteRespuesta();
						rr.setCue(cuestionario);
						rr.setPregunta(pregunta);
						rr.setRop(rop);
						rr.setTus(tus);

						return rr;
					}

				});

		return lstReporteRespuesta;
	}

	@Override
	public List<ReporteRespuesta> generarDatosReportePorCuestionario(int cuestionario) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", cuestionario);

		String sql = "select ro.rop_descripcion,p.pre_descripcion,p.pre_identificador,c.cue_nombre,tu.tus_nombre from encuestas.respuestas r"
				+ " join encuestas.respuestas_cuestionarios rc on r.rcu_codigo = rc.rcu_codigo "
				+ "join encuestas.preguntas_respuestas pr on r.prr_codigo = pr.prr_codigo "
				+ "join encuestas.respuestas_opciones ro on pr.rop_codigo = ro.rop_codigo "
				+ "join encuestas.preguntas p on r.pre_codigo =p.pre_codigo "
				+ "join encuestas.cuestionarios c on rc.cue_codigo = c.cue_codigo "
				+ "join encuestas.cuestionario_configuracion cc on cc.cue_codigo = c.cue_codigo "
				+ "join dbo.usuario_tipo tu  on cc.tus_codigo = tu.tus_codigo  where c.cue_codigo=:codigo";

		List<ReporteRespuesta> lstReporteRespuesta = namedJdbcTemplate.query(sql, parameter,
				new RowMapper<ReporteRespuesta>() {

					@Override
					public ReporteRespuesta mapRow(ResultSet rs, int rowNum) throws SQLException {

						RespuestaOpciones rop = new RespuestaOpciones();
						rop.setDescripcion(rs.getString("rop_descripcion"));

						Cuestionario cuestionario = new Cuestionario();
						cuestionario.setNombre(rs.getString("cue_nombre"));

						Pregunta pregunta = new Pregunta();
						pregunta.setDescripcion(rs.getString("pre_descripcion"));
						pregunta.setIdentificador(rs.getString("pre_identificador"));

						UsuarioTipo tus = new UsuarioTipo();
						tus.setNombre(rs.getString("tus_nombre"));

						ReporteRespuesta rr = new ReporteRespuesta();
						rr.setCue(cuestionario);
						rr.setPregunta(pregunta);
						rr.setRop(rop);
						rr.setTus(tus);

						return rr;
					}

				});

		return lstReporteRespuesta;
	}
	
	
	// DETALLADO
	
	@Override
	public List<ReporteRespuestaDetallado> generarDatosReporteTodosDetallado(int uaa) {
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("uaa", uaa);

		String sql = "select e.est_codigo, s.sed_nombre, u.uaa_nombre, floor((cast(convert(varchar(8),getdate(),112) as int) - cast(convert(varchar(8), pe.per_fecha_nacimiento ,112) as int) ) / 10000) as edad, ro.rop_descripcion, p.pre_descripcion, p.pre_identificador, c.cue_nombre, tu.tus_nombre, "
				+ "ti.tii_nombre_corto, pe.per_identificacion, pe.per_fecha_expedicion, pe.per_apellido, pe.per_nombre from encuestas.respuestas r "
				+ "join encuestas.respuestas_cuestionarios rc on r.rcu_codigo = rc.rcu_codigo "
				+ "join dbo.estudiante e on rc.per_codigo = e.per_codigo "
				+ "join dbo.persona pe on e.per_codigo = pe.per_codigo "
				+ "join dbo.programa pm on e.pro_codigo = pm.pro_codigo "
				+ "join dbo.uaa u on pm.uaa_codigo = u.uaa_codigo "
				+ "join dbo.sede s on u.sed_codigo = s.sed_codigo "
				+ "join dbo.tipo_id ti on pe.tii_codigo = ti.tii_codigo "
				+ "join encuestas.preguntas_respuestas pr on r.prr_codigo = pr.prr_codigo "
				+ "join encuestas.respuestas_opciones ro on pr.rop_codigo = ro.rop_codigo "
				+ "join encuestas.preguntas p on r.pre_codigo =p.pre_codigo "
				+ "join encuestas.cuestionarios c on rc.cue_codigo = c.cue_codigo "
				+ "join encuestas.cuestionario_configuracion cc on cc.cue_codigo = c.cue_codigo "
				+ "join dbo.usuario_tipo tu  on cc.tus_codigo = tu.tus_codigo  where c.uaa_codigo =:uaa";

		List<ReporteRespuestaDetallado> lstReporteRespuesta = namedJdbcTemplate.query(sql, parameter,
				new RowMapper<ReporteRespuestaDetallado>() {

					@Override
					public ReporteRespuestaDetallado mapRow(ResultSet rs, int rowNum) throws SQLException {

						RespuestaOpciones rop = new RespuestaOpciones();
						rop.setDescripcion(rs.getString("rop_descripcion"));

						Cuestionario cuestionario = new Cuestionario();
						cuestionario.setNombre(rs.getString("cue_nombre"));

						Pregunta pregunta = new Pregunta();
						pregunta.setDescripcion(rs.getString("pre_descripcion"));
						pregunta.setIdentificador(rs.getString("pre_identificador"));

						UsuarioTipo tus = new UsuarioTipo();
						tus.setNombre(rs.getString("tus_nombre"));
						
						Persona per = new Persona();
						per.setNombre(rs.getString("per_nombre"));
						per.setApellido(rs.getString("per_apellido"));
						per.setTipoDocumento(rs.getString("tii_nombre_corto"));
						per.setIdentificacion(rs.getString("per_identificacion"));
						per.setExpedicion(rs.getString("per_fecha_expedicion"));
						per.setEdad(rs.getString("edad"));
						
						Estudiante est = new Estudiante();
						est.setCodigo(rs.getString("est_codigo"));
						
						Uaa u = new Uaa();
						u.setNombre(rs.getString("uaa_nombre"));
						
						Sede sed = new Sede();
						sed.setNombre(rs.getString("sed_nombre"));

						ReporteRespuestaDetallado rr = new ReporteRespuestaDetallado();
						rr.setCue(cuestionario);
						rr.setPregunta(pregunta);
						rr.setRop(rop);
						rr.setTus(tus);
						rr.setPer(per);
						rr.setEst(est);
						rr.setUaa(u);
						rr.setSede(sed);

						return rr;
					}

				});

		return lstReporteRespuesta;
	}
	
	@Override
	public List<ReporteRespuestaDetallado> generarDatosReporteProgramaDetallado(int programa, int uaa) {
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("uaa", uaa);
		parameter.addValue("programa", programa);

		String sql = "select tu.tus_nombre, e.est_codigo, per.per_apellido, per.per_nombre, "
				+ "ti.tii_nombre_corto, per.per_identificacion, per.per_fecha_expedicion, floor((cast(convert(varchar(8),getdate(),112) as int) - cast(convert(varchar(8), per.per_fecha_nacimiento ,112) as int) ) / 10000) as edad,"
				+ "s.sed_nombre, u.uaa_nombre,p.pre_identificador, p.pre_descripcion, CASE WHEN r.prr_codigo is null THEN r.res_texto ELSE ro.rop_descripcion END AS respuesta, c.cue_nombre from encuestas.respuestas r "
				+ "join encuestas.respuestas_cuestionarios rc on r.rcu_codigo = rc.rcu_codigo "
				+ "join dbo.persona per on rc.per_codigo = per.per_codigo "
				+ "join dbo.estudiante e on e.per_codigo = per.per_codigo "
				+ "join dbo.programa pro on e.pro_codigo = pro.pro_codigo "
				+ "join dbo.uaa u on pro.uaa_codigo = u.uaa_codigo "
				+ "join dbo.sede s on u.sed_codigo = s.sed_codigo "
				+ "join dbo.tipo_id ti on per.tii_codigo = ti.tii_codigo "
				+ "left join encuestas.preguntas_respuestas pr on r.prr_codigo = pr.prr_codigo "
				+ "left join encuestas.respuestas_opciones ro on pr.rop_codigo = ro.rop_codigo "
				+ "left join encuestas.preguntas p on r.pre_codigo = p.pre_codigo "
				+ "left join encuestas.cuestionarios c on rc.cue_codigo = c.cue_codigo "
				+ "left join encuestas.cuestionario_configuracion cc on cc.cue_codigo = c.cue_codigo "
				+ "join dbo.usuario_tipo tu on cc.tus_codigo = tu.tus_codigo "
				+ "where p.pre_estado = 1 and (tu.tus_codigo = 2 and pro.pro_codigo=:programa ) and c.uaa_codigo =:uaa";

		List<ReporteRespuestaDetallado> lstReporteRespuesta = namedJdbcTemplate.query(sql, parameter,
				new RowMapper<ReporteRespuestaDetallado>() {

					@Override
					public ReporteRespuestaDetallado mapRow(ResultSet rs, int rowNum) throws SQLException {

						RespuestaOpciones rop = new RespuestaOpciones();
						rop.setDescripcion(rs.getString("respuesta"));

						Cuestionario cuestionario = new Cuestionario();
						cuestionario.setNombre(rs.getString("cue_nombre")); 

						Pregunta pregunta = new Pregunta();
						pregunta.setDescripcion(rs.getString("pre_descripcion"));
						pregunta.setIdentificador(rs.getString("pre_identificador"));

						UsuarioTipo tus = new UsuarioTipo();
						tus.setNombre(rs.getString("tus_nombre"));
						
						Persona per = new Persona();
						per.setNombre(rs.getString("per_nombre"));
						per.setApellido(rs.getString("per_apellido"));
						per.setTipoDocumento(rs.getString("tii_nombre_corto"));
						per.setIdentificacion(rs.getString("per_identificacion"));
						per.setExpedicion(rs.getString("per_fecha_expedicion"));
						per.setEdad(rs.getString("edad"));
						
						Estudiante est = new Estudiante();
						est.setCodigo(rs.getString("est_codigo"));
						
						Uaa u = new Uaa();
						u.setNombre(rs.getString("uaa_nombre"));
						
						Sede sed = new Sede();
						sed.setNombre(rs.getString("sed_nombre"));

						ReporteRespuestaDetallado rr = new ReporteRespuestaDetallado();
						rr.setCue(cuestionario);
						rr.setPregunta(pregunta);
						rr.setRop(rop);
						rr.setTus(tus);
						rr.setPer(per);
						rr.setEst(est);
						rr.setUaa(u);
						rr.setSede(sed);

						return rr;
					}

				});

		return lstReporteRespuesta;
	}

	@Override
	public List<ReporteRespuestaDetallado> generarDatosReportePorTipoUsuarioDetallado(int tus, int uaa) {
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("uaa", uaa);
		parameter.addValue("codigo", tus);

		String sql = "select e.est_codigo, s.sed_nombre, u.uaa_nombre, floor((cast(convert(varchar(8),getdate(),112) as int) - cast(convert(varchar(8), pe.per_fecha_nacimiento ,112) as int) ) / 10000) as edad, ro.rop_descripcion, p.pre_descripcion, p.pre_identificador, c.cue_nombre, tu.tus_nombre, "
				+ "ti.tii_nombre_corto, pe.per_identificacion, pe.per_fecha_expedicion, pe.per_apellido, pe.per_nombre from encuestas.respuestas r "
				+ "join encuestas.respuestas_cuestionarios rc on r.rcu_codigo = rc.rcu_codigo "
				+ "join dbo.estudiante e on rc.per_codigo = e.per_codigo "
				+ "join dbo.persona pe on e.per_codigo = pe.per_codigo "
				+ "join dbo.programa pm on e.pro_codigo = pm.pro_codigo "
				+ "join dbo.uaa u on pm.uaa_codigo = u.uaa_codigo "
				+ "join dbo.sede s on u.sed_codigo = s.sed_codigo "
				+ "join dbo.tipo_id ti on pe.tii_codigo = ti.tii_codigo "
				+ "join encuestas.preguntas_respuestas pr on r.prr_codigo = pr.prr_codigo "
				+ "join encuestas.respuestas_opciones ro on pr.rop_codigo = ro.rop_codigo "
				+ "join encuestas.preguntas p on r.pre_codigo =p.pre_codigo "
				+ "join encuestas.cuestionarios c on rc.cue_codigo = c.cue_codigo "
				+ "join encuestas.cuestionario_configuracion cc on cc.cue_codigo = c.cue_codigo "
				+ "join dbo.usuario_tipo tu  on cc.tus_codigo = tu.tus_codigo  where c.uaa_codigo =:uaa AND tu.tus_codigo=:codigo";

		List<ReporteRespuestaDetallado> lstReporteRespuesta = namedJdbcTemplate.query(sql, parameter,
				new RowMapper<ReporteRespuestaDetallado>() {

					@Override
					public ReporteRespuestaDetallado mapRow(ResultSet rs, int rowNum) throws SQLException {

						RespuestaOpciones rop = new RespuestaOpciones();
						rop.setDescripcion(rs.getString("rop_descripcion"));

						Cuestionario cuestionario = new Cuestionario();
						cuestionario.setNombre(rs.getString("cue_nombre"));

						Pregunta pregunta = new Pregunta();
						pregunta.setDescripcion(rs.getString("pre_descripcion"));
						pregunta.setIdentificador(rs.getString("pre_identificador"));

						UsuarioTipo tus = new UsuarioTipo();
						tus.setNombre(rs.getString("tus_nombre"));
						
						Persona per = new Persona();
						per.setNombre(rs.getString("per_nombre"));
						per.setApellido(rs.getString("per_apellido"));
						per.setTipoDocumento(rs.getString("tii_nombre_corto"));
						per.setIdentificacion(rs.getString("per_identificacion"));
						per.setExpedicion(rs.getString("per_fecha_expedicion"));
						per.setEdad(rs.getString("edad"));
						
						Estudiante est = new Estudiante();
						est.setCodigo(rs.getString("est_codigo"));
						
						Uaa u = new Uaa();
						u.setNombre(rs.getString("uaa_nombre"));
						
						Sede sed = new Sede();
						sed.setNombre(rs.getString("sed_nombre"));

						ReporteRespuestaDetallado rr = new ReporteRespuestaDetallado();
						rr.setCue(cuestionario);
						rr.setPregunta(pregunta);
						rr.setRop(rop);
						rr.setTus(tus);
						rr.setPer(per);
						rr.setEst(est);
						rr.setUaa(u);
						rr.setSede(sed);

						return rr;
					}

				});

		return lstReporteRespuesta;
	}

	@Override
	public List<ReporteRespuestaDetallado> generarDatosReportePorCuestionarioDetallado(int cuestionario) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", cuestionario);

		String sql = "select e.est_codigo, s.sed_nombre, u.uaa_nombre, floor((cast(convert(varchar(8),getdate(),112) as int) - cast(convert(varchar(8), pe.per_fecha_nacimiento ,112) as int) ) / 10000) as edad, ro.rop_descripcion, p.pre_descripcion, p.pre_identificador, c.cue_nombre, tu.tus_nombre, "
				+ "ti.tii_nombre_corto, pe.per_identificacion, pe.per_fecha_expedicion, pe.per_apellido, pe.per_nombre from encuestas.respuestas r "
				+ "join encuestas.respuestas_cuestionarios rc on r.rcu_codigo = rc.rcu_codigo "
				+ "join dbo.estudiante e on rc.per_codigo = e.per_codigo "
				+ "join dbo.persona pe on e.per_codigo = pe.per_codigo "
				+ "join dbo.programa pm on e.pro_codigo = pm.pro_codigo "
				+ "join dbo.uaa u on pm.uaa_codigo = u.uaa_codigo "
				+ "join dbo.sede s on u.sed_codigo = s.sed_codigo "
				+ "join dbo.tipo_id ti on pe.tii_codigo = ti.tii_codigo "
				+ "join encuestas.preguntas_respuestas pr on r.prr_codigo = pr.prr_codigo "
				+ "join encuestas.respuestas_opciones ro on pr.rop_codigo = ro.rop_codigo "
				+ "join encuestas.preguntas p on r.pre_codigo = p.pre_codigo "
				+ "join encuestas.cuestionarios c on rc.cue_codigo = c.cue_codigo "
				+ "join encuestas.cuestionario_configuracion cc on cc.cue_codigo = c.cue_codigo "
				+ "join dbo.usuario_tipo tu  on cc.tus_codigo = tu.tus_codigo where c.cue_codigo=:codigo and s.sed_codigo != 20";
		

		List<ReporteRespuestaDetallado> lstReporteRespuesta = namedJdbcTemplate.query(sql, parameter,
				new RowMapper<ReporteRespuestaDetallado>() {

					@Override
					public ReporteRespuestaDetallado mapRow(ResultSet rs, int rowNum) throws SQLException {

						RespuestaOpciones rop = new RespuestaOpciones();
						rop.setDescripcion(rs.getString("rop_descripcion"));

						Cuestionario cuestionario = new Cuestionario();
						cuestionario.setNombre(rs.getString("cue_nombre"));

						Pregunta pregunta = new Pregunta();
						pregunta.setDescripcion(rs.getString("pre_descripcion"));
						pregunta.setIdentificador(rs.getString("pre_identificador"));

						UsuarioTipo tus = new UsuarioTipo();
						tus.setNombre(rs.getString("tus_nombre"));
						
						Estudiante est = new Estudiante();
						est.setCodigo(rs.getString("est_codigo"));
						
						Persona per = new Persona();
						per.setNombre(rs.getString("per_nombre"));
						per.setApellido(rs.getString("per_apellido"));
						per.setTipoDocumento(rs.getString("tii_nombre_corto"));
						per.setIdentificacion(rs.getString("per_identificacion"));
						per.setExpedicion(rs.getString("per_fecha_expedicion"));
						per.setEdad(rs.getString("edad"));
						
						Uaa u = new Uaa();
						u.setNombre(rs.getString("uaa_nombre"));
						
						Sede sed = new Sede();
						sed.setNombre(rs.getString("sed_nombre"));

						ReporteRespuestaDetallado rr = new ReporteRespuestaDetallado();
						rr.setCue(cuestionario);
						rr.setPregunta(pregunta);
						rr.setRop(rop);
						rr.setTus(tus);
						rr.setPer(per);
						rr.setEst(est);
						rr.setUaa(u);
						rr.setSede(sed);

						return rr;
					}

				});

		return lstReporteRespuesta;
	}

	@Override
	public int valoresGraficas(int pregunta, int rop) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();

		parameter.addValue("pregunta", pregunta);
		parameter.addValue("rop", rop);

		String sql = "select count(*) as valor from encuestas.respuestas r "
				+ "join encuestas.respuestas_cuestionarios rc on r.rcu_codigo = rc.rcu_codigo "
				+ "join encuestas.preguntas_respuestas pr on r.prr_codigo = pr.prr_codigo "
				+ "join encuestas.respuestas_opciones ro on pr.rop_codigo = ro.rop_codigo "
				+ "join encuestas.preguntas p on r.pre_codigo =p.pre_codigo "
				+ "join encuestas.cuestionarios c on rc.cue_codigo = c.cue_codigo "
				+ " where (p.pre_codigo=:pregunta AND ro.rop_codigo=:rop);";

		List<Integer> valores = namedJdbcTemplate.query(sql, parameter, new RowMapper<Integer>() {

			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {

				int valor = rs.getInt("valor");

				return valor;
			}

		});

		return valores.get(0);
	}
	
	// REPORTE AGRUPADO TEXTO Y OPCIONES DE RESPUESTA

	@Override
	public List<Pregunta> obtenerPreguntasOpcionesCuestionario(int cuestionario) {
		
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", cuestionario);

		String sql = "select * from encuestas.preguntas p where p.cue_codigo =:codigo and p.pre_estado = 1 and p.tre_codigo != 2";

		List<Pregunta> lstPregunta = namedJdbcTemplate.query(sql, parameter, new RowMapper<Pregunta>() {

			@Override
			public Pregunta mapRow(ResultSet rs, int rowNum) throws SQLException {


				Pregunta pregunta = new Pregunta();
				pregunta.setCodigo(rs.getLong("pre_codigo"));
				pregunta.setDescripcion(rs.getString("pre_descripcion"));
				pregunta.setTextoAdicional(rs.getString("pre_texto_adicional"));
				pregunta.setTipo(rs.getInt("pre_tipo"));
				pregunta.setObligatorio(rs.getInt("pre_obligatorio"));
				pregunta.setDepende(rs.getInt("pre_codigo_depende"));
				pregunta.setIdentificador(rs.getString("pre_identificador"));

				return pregunta;
			}

		});

		return lstPregunta;

	}

	@Override
	public List<Pregunta> obtenerPreguntasTextoCuestionario(int cuestionario) {
		
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", cuestionario);

		String sql = "select * from encuestas.preguntas p where p.cue_codigo =:codigo and p.pre_estado = 1 and p.tre_codigo = 2";

		List<Pregunta> lstPregunta = namedJdbcTemplate.query(sql, parameter, new RowMapper<Pregunta>() {

			@Override
			public Pregunta mapRow(ResultSet rs, int rowNum) throws SQLException {


				Pregunta pregunta = new Pregunta();
				pregunta.setCodigo(rs.getLong("pre_codigo"));
				pregunta.setDescripcion(rs.getString("pre_descripcion"));
				pregunta.setTextoAdicional(rs.getString("pre_texto_adicional"));
				pregunta.setTipo(rs.getInt("pre_tipo"));
				pregunta.setObligatorio(rs.getInt("pre_obligatorio"));
				pregunta.setDepende(rs.getInt("pre_codigo_depende"));
				pregunta.setIdentificador(rs.getString("pre_identificador"));

				return pregunta;
			}

		});

		return lstPregunta;
	}

	@Override
	public List<ReporteAgrupado> generarDatosReporteAgrupadoTexto(int cuestionario, String preguntas) {
		
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", cuestionario);
		
		
		String input = preguntas;

        // Eliminar los corchetes y separar por ","
        String[] parts = input.replaceAll("[\\[\\]]", "").split(",");

        // Crear un nuevo array para almacenar los valores numéricos
        int[] numericValues = new int[parts.length];

        // Convertir cada parte en un valor numérico y almacenarlo en el array
        for (int i = 0; i < parts.length; i++) {
            numericValues[i] = Integer.parseInt(parts[i]);
        }

		String sql = "with resultado("
				+ "  rcu_codigo,"
				+ "  rcu_fecha,"
				+ "  tus_nombre,"
				+ "  pre_codigo,"
				+ "  res_texto"
				+ ") as("
				+ "  select"
				+ "    rc.rcu_codigo,"
				+ "    rc.rcu_fecha,"
				+ "    tu.tus_nombre,"
				+ "    p.pre_codigo,"
				+ "    r.res_texto"
				+ "  from"
				+ "    encuestas.respuestas_cuestionarios rc"
				+ "    inner JOIN encuestas.respuestas r on r.rcu_codigo = rc.rcu_codigo"
				+ "    inner join encuestas.preguntas p on r.pre_codigo = p.pre_codigo"
				+ "    inner join dbo.usuario_tipo tu on rc.rcu_estamento = tu.tus_codigo"
				+ "  where"
				+ "    rc.cue_codigo =:codigo"
				+ ")"
				+ "SELECT"
				+ "  *"
				+ "from"
				+ "  resultado pivot(max(res_texto) FOR pre_codigo in (" + preguntas + ")) as pvt";

		List<ReporteAgrupado> lstReporteAgrupado = namedJdbcTemplate.query(sql, parameter, new RowMapper<ReporteAgrupado>() {

			@Override
			public ReporteAgrupado mapRow(ResultSet rs, int rowNum) throws SQLException {


				ReporteAgrupado ReporteAgrupado = new ReporteAgrupado();
				
				
				ReporteAgrupado.setFecha(rs.getDate("rcu_fecha"));
				ReporteAgrupado.setEstamento(rs.getString("tus_nombre"));
				
				 Map<String, String> columnas = new HashMap<>();

	                int columnCount = numericValues.length;
	                
	                for (int i = 0; i < columnCount; i++) {
	                    String columnName = "columna" + i;
	                    String columnValue = rs.getString(numericValues[i]+"");
	                    columnas.put(columnName, columnValue);
	                }

	                ReporteAgrupado.setColumnas(columnas);


				

				return ReporteAgrupado;
			}

		});

		return lstReporteAgrupado;
	}

	@Override
	public List<ReporteAgrupado> generarDatosReporteAgrupadoOpciones(int cuestionario, String preguntas) {
		
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", cuestionario);
		
		
		String input = preguntas;

        // Eliminar los corchetes y separar por ","
        String[] parts = input.replaceAll("[\\[\\]]", "").split(",");

        // Crear un nuevo array para almacenar los valores numéricos
        int[] numericValues = new int[parts.length];

        // Convertir cada parte en un valor numérico y almacenarlo en el array
        for (int i = 0; i < parts.length; i++) {
            numericValues[i] = Integer.parseInt(parts[i]);
        }

		String sql = "with resultado("
				+ "  rcu_codigo,"
				+ "  rcu_fecha,"
				+ "  tus_nombre,"
				+ "  pre_codigo,"
				+ "  rop_descripcion"
				+ ") as("
				+ "  select"
				+ "    rc.rcu_codigo,"
				+ "    rc.rcu_fecha,"
				+ "    tu.tus_nombre,"
				+ "    pr.pre_codigo,"
				+ "    convert(varchar(40), ro.rop_descripcion, 112)"
				+ "  from"
				+ "    encuestas.respuestas_cuestionarios rc"
				+ "    inner JOIN encuestas.respuestas r on r.rcu_codigo = rc.rcu_codigo"
				+ "    inner JOIN encuestas.preguntas_respuestas pr on r.prr_codigo = pr.prr_codigo"
				+ "    inner join encuestas.preguntas p on pr.pre_codigo = p.pre_codigo"
				+ "    inner JOIN encuestas.respuestas_opciones ro on pr.rop_codigo = ro.rop_codigo"
				+ "    inner join dbo.usuario_tipo tu on rc.rcu_estamento = tu.tus_codigo"
				+ "  where"
				+ "    rc.cue_codigo =:codigo"
				+ ")"
				+ "SELECT"
				+ "  *"
				+ "from"
				+ "  resultado pivot(max(rop_descripcion) FOR pre_codigo in (" + preguntas + ")) as pvt";

		List<ReporteAgrupado> lstReporteAgrupado = namedJdbcTemplate.query(sql, parameter, new RowMapper<ReporteAgrupado>() {

			@Override
			public ReporteAgrupado mapRow(ResultSet rs, int rowNum) throws SQLException {


				ReporteAgrupado ReporteAgrupado = new ReporteAgrupado();
				
				
				ReporteAgrupado.setFecha(rs.getDate("rcu_fecha"));
				ReporteAgrupado.setEstamento(rs.getString("tus_nombre"));
				
				 Map<String, String> columnas = new HashMap<>();

	                int columnCount = numericValues.length;
	                
	                for (int i = 0; i < columnCount; i++) {
	                    String columnName = "columna" + i;
	                    String columnValue = rs.getString(numericValues[i]+"");
	                    columnas.put(columnName, columnValue);
	                }

	                ReporteAgrupado.setColumnas(columnas);


				

				return ReporteAgrupado;
			}

		});

		return lstReporteAgrupado;
	}

	@Override
	public List<Resultado> obtenerResultados(int cuestionario) {
		
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", cuestionario);

		String sql = "select ut.tus_nombre as estamento, count(rc.rcu_codigo) as encuestados from encuestas.respuestas_cuestionarios rc "
				+ "inner join usuario_tipo ut on rc.rcu_estamento = ut.tus_codigo "
				+ "where rc.cue_codigo = :codigo "
				+ "group by ut.tus_nombre ";

		List<Resultado> lstResultado = namedJdbcTemplate.query(sql, parameter, new RowMapper<Resultado>() {

			@Override
			public Resultado mapRow(ResultSet rs, int rowNum) throws SQLException {


				Resultado resultado = new Resultado();
				resultado.setEstamentos(rs.getString("estamento"));
				resultado.setResultados(rs.getInt("encuestados"));

				return resultado;
			}

		});

		return lstResultado;
	}

}
