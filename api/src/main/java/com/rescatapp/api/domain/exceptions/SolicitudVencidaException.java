package com.rescatapp.api.domain.exceptions;

public class SolicitudVencidaException extends RuntimeException {
    public SolicitudVencidaException(String errorMessage) {
        super(errorMessage);
    }
}