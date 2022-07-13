package com.rescatapp.api.domain.exceptions;

public class UsuarioSinCapacidadException extends RuntimeException {
    public UsuarioSinCapacidadException(String errorMessage) {
        super(errorMessage);
    }
}