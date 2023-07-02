package com.usco.edu.dao;

public interface IAdministradorDao {

	public String getTokenInicioSesion(String atributos , String userdb);
	
	public String urltokenPeticion( String userdb);
}
