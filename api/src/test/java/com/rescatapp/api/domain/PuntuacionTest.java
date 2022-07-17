package com.rescatapp.api.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;


public class PuntuacionTest {
    @Test
    public void obtenerEstrellasDeUnaPuntuacion() {
        Usuario usuario = new UsuarioTest.DummyUsuario(1L, new Localizacion(-50f, -50f, "prueba"), "prueba", "1234", "test@†est.com",  null);
        Puntuacion puntuacion = new Puntuacion(3.5F, "Buena persona.",usuario);
        float resultado = puntuacion.getEstrellas();

        assertThat(resultado).isEqualTo(3.5F);
    }
}