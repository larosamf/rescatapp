package com.rescatapp.api.domain.exceptions;

public class UsuarioReportadoException extends RuntimeException {
    public UsuarioReportadoException(String errorMessage) {
        super(errorMessage);
    }
}