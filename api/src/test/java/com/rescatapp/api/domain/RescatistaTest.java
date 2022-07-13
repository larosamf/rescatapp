package com.rescatapp.api.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RescatistaTest {

    @Test
    public void registrarConPerroGuardaMascota() {
        Rescatista rescatista = new Rescatista(1L, new Localizacion(-50f, -50f,"prueba"), "prueba", "1234", "test@†est.com", null);
        Mascota perro = new Mascota(8L, Mascota.Tipo.PERRO);

        Mascota resultado = rescatista.registrar(perro);

        assertThat(resultado).isEqualTo(perro);
        assertThat(resultado.getUsuarioResponsable()).isEqualTo(rescatista);
        assertThat(rescatista.getMascotasRescatadas()).contains(perro);
    }
}