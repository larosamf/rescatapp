package com.rescatapp.api.domain.exceptions;

public class UsuarioQuePuntuaYaRealizoUnaPuntuacionAntesException extends Exception {
    public UsuarioQuePuntuaYaRealizoUnaPuntuacionAntesException(String errorMessage) {
        super(errorMessage);
    }
}