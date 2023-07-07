package com.usco.edu.entities;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Resultado implements Serializable  {
	
	private String estamentos;
	
	private int resultados;

	private static final long serialVersionUID = 1L;

}