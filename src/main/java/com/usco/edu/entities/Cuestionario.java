package com.usco.edu.entities;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Cuestionario implements Serializable {

	private Long codigo;

	private String nombre;

	private String instrucciones;

	private int estado;

	private Uaa uaa;

	private Date fin;

	private Date inicio;

	private static final long serialVersionUID = 1L;
}
