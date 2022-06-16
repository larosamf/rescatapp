package com.rescatapp.api.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AdoptistaTest {

    @Test
    public void adoptarConPerroGuardaMascota() {
        Adoptista adoptista = new Adoptista(1L, new Localizacion(-50f, -50f,"prueba"), "prueba", "1234", "test@†est.com", null);
        Mascota perro = new Mascota(8L, Mascota.Tipo.PERRO);
        adoptista.adoptar(perro);

        assertThat(adoptista.getMascotasAdoptadas()).contains(perro);
    }
}