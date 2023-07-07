package com.usco.edu.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReporteAgrupado implements Serializable {

	private Date fecha;
	
	private String estamento;
	
	private Map<String, String> columnas;

	private static final long serialVersionUID = 1L;

}
