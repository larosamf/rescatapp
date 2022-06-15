package com.rescatapp.api.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Puntuacion {
    private final float estrellas;

    private final String comentario;

    private boolean isAdoptista = false;

    public Puntuacion(float estrellas, String comentario) {
        this.estrellas = estrellas;
        this.comentario = comentario;
    }

    public float getEstrellas(){ return this.estrellas; }

    public boolean getIsAdoptista(){ return this.isAdoptista; }
}
