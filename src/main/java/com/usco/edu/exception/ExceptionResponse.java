package com.usco.edu.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionResponse {

	private LocalDateTime fecha;
	private String mensaje;
	private String detalle;

}
