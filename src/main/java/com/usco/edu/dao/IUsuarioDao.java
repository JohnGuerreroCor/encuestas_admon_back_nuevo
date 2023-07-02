package com.usco.edu.dao;

import com.usco.edu.entities.Usuario;
import com.usco.edu.entities.Role;

public interface IUsuarioDao {

	public Usuario findByUsername(String username);

	public Role roles(String username);

	public boolean validarUser(String username);

}
