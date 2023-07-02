package com.usco.edu.service;

import com.usco.edu.entities.Usuario;
import com.usco.edu.entities.Role;

public interface IUsuarioService {

	public Usuario findByUsername(String username);

	public Role roles(String username);
}
