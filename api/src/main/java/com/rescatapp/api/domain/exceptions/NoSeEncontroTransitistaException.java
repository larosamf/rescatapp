package com.rescatapp.api.domain.exceptions;

public class NoSeEncontroTransitistaException extends RuntimeException {
    public NoSeEncontroTransitistaException(String errorMessage) {
        super(errorMessage);
    }
}
