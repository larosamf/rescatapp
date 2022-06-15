package com.rescatapp.api.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class PuntuacionTest {
    @Test
    public void obtenerEstrellasDeUnaPuntuacion() {
        Puntuacion puntuacion = new Puntuacion(3.5F, "Buena persona.");
        float resultado = puntuacion.getEstrellas();

        assertThat(resultado).isEqualTo(3.5F);
    }
}