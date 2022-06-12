package com.rescatapp.api.domain;

import org.junit.Test;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class TransitistaTest {

    @Test
    public void aceptarConPerroYTransititasActivoAgregaMascotaASuCargo() {
        Transitista transitista = new Transitista(1L, new Localizacion(-50f, -50f, "prueba"), "prueba", "1234", "test@†est.com", 2, null);
        transitista.mostrarComoActivo();
        Mascota perro = new Mascota(8L, Mascota.Tipo.PERRO);

        Mascota resultado = transitista.aceptar(perro);

        assertThat(resultado).isEqualTo(perro);
        assertThat(resultado.getIdUsuarioResponsable()).isEqualTo(transitista.getId());
        assertThat(transitista.getMascotasEnTransito()).contains(perro);
    }

    @Test
    public void pasarAAdopcionConPerroPasaMascotaAEnAdopcion() {
        Transitista transitista = new Transitista(1L, new Localizacion(-50f, -50f, "prueba"), "prueba", "1234", "test@†est.com", 2, null);
        transitista.mostrarComoActivo();
        Mascota perro = new Mascota(8L, Mascota.Tipo.PERRO);
        transitista.aceptar(perro);

        transitista.pasarAAdopcion(perro);

        assertThat(perro.estaEnAdopcion()).isEqualTo(true);
    }

    @Test
    public void recibirDonacionConDonacionTransfiereDineroATransitista() {
        ProcesadorPagos procesadorPagos = mock(ProcesadorPagos.class);
        String cbu = "11111111111111111111";
        Transitista transitista = new Transitista(1L, new Localizacion(-50f, -50f, "prueba"), "prueba", "1234", "test@†est.com", 2, procesadorPagos);
        transitista.setCbu(cbu);
        Donacion donacion = new Donacion(1L, new BigDecimal(100), new BigDecimal(1));
        doNothing().when(procesadorPagos).pagar(donacion.getMontoADepositar(), cbu);

        transitista.recibirDonacion(donacion);

        assertThat(transitista.getDonacionesRecibidas()).contains(donacion);
        verify(procesadorPagos).pagar(donacion.getMontoADepositar(), cbu);
    }
}