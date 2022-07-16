package com.rescatapp.api.domain.exceptions;

public class InstitucionCerradaException extends RuntimeException {
    public InstitucionCerradaException(String errorMessage) {
        super(errorMessage);
    }
}
