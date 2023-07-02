package com.usco.edu.entities;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Usuario implements Serializable {

	private int id;

	private String username;

	private String password;

	private String userdb;

	private boolean state;

	private Uaa uaa;

	private Persona persona;

	private String role;

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", username=" + username + ", password=" + password + ", userdb=" + userdb
				+ ", state=" + state + ", uaa=" + uaa + ", persona=" + persona + ", role=" + role + "]";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}

