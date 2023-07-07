package com.usco.edu.auth;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/api").permitAll().antMatchers("/login").permitAll()
				.antMatchers("/inicio").permitAll().antMatchers("/logo").permitAll().antMatchers("/assets/**").permitAll()
				.antMatchers("/**.png").permitAll()
				.antMatchers("/**.js").permitAll()
				.antMatchers("/**.css").permitAll().antMatchers("/token").permitAll()
				.antMatchers("/**.js").permitAll().antMatchers("/**.css").permitAll().antMatchers("/**.jasper")
				.permitAll().antMatchers("/obtenerFoto/{codigo}").permitAll().antMatchers("/cuestionario").permitAll()
				.antMatchers("/pregunta-cuestionario").permitAll().antMatchers("/mirar-cuestionario").permitAll()
				.antMatchers("/cuest-u").permitAll().antMatchers("/preguntas").permitAll().antMatchers("/escala")
				.permitAll().antMatchers("/static/**").permitAll().antMatchers("/grupo-escala").permitAll()
				.antMatchers("respuesta").permitAll().antMatchers("/opciones").permitAll().anyRequest().authenticated()
				.and().formLogin().loginPage("/").permitAll().and().cors()
				.configurationSource(corsConfigurationSource());
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("*"));
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		// config.setAllowCredentials(true);
		config.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}

	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(
				new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}

}
