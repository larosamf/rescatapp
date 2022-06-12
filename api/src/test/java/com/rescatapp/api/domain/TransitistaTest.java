package com.rescatapp.api.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TransitistaTest {

    @Test
    public void aceptarConPerroYTransititasActivoAgregaMascotaASuCargo() {
        Transitista transitista = new Transitista(1L, new Localizacion(-50f, -50f,"prueba"), "prueba", "1234", "test@†est.com", 2);
        transitista.mostrarComoActivo();
        Mascota perro = new Mascota(8L, Mascota.Tipo.PERRO);

        Mascota resultado = transitista.aceptar(perro);

        assertThat(resultado).isEqualTo(perro);
        assertThat(resultado.getIdUsuarioResponsable()).isEqualTo(transitista.getId());
        assertThat(transitista.getMascotasEnTransito()).contains(perro);
    }

    @Test
    public void pasarAAdopcionConPerroPasaMascotaAEnAdopcion() {
        Transitista transitista = new Transitista(1L, new Localizacion(-50f, -50f,"prueba"), "prueba", "1234", "test@†est.com", 2);
        transitista.mostrarComoActivo();
        Mascota perro = new Mascota(8L, Mascota.Tipo.PERRO);
        transitista.aceptar(perro);

        transitista.pasarAAdopcion(perro);

        assertThat(perro.estaEnAdopcion()).isEqualTo(true);
    }
}