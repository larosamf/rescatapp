package com.rescatapp.api.domain.exceptions;

public class NoSeCompletoRegistroDeMascotaException extends RuntimeException {
    public NoSeCompletoRegistroDeMascotaException(String errorMessage) {
        super(errorMessage);
    }
}