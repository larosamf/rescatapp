package com.rescatapp.api.domain.exceptions;

public class UsuarioNoTieneEsaMascotaException extends RuntimeException {
    public UsuarioNoTieneEsaMascotaException(String errorMessage) {
        super(errorMessage);
    }
}