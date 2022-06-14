package com.rescatapp.api.domain;

import org.junit.Test;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class TransitistaTest {

    @Test
    public void aceptarConPerroYTransititasActivoAgregaMascotaASuCargo() {
        Transitista transitista = new Transitista(1L, new Localizacion(-50f, -50f, "prueba"), "prueba", "1234", "test@†est.com", 2, null);
        transitista.mostrarComoActivo();
        Mascota perro = new Mascota(8L, Mascota.Tipo.PERRO);
        SolicitudDeTransito solicitud = new SolicitudDeTransito(3L, new Date(), perro, 10L, 1L);

        Mascota resultado = transitista.aceptar(solicitud);

        assertThat(resultado).isEqualTo(perro);
        assertThat(resultado.getIdUsuarioResponsable()).isEqualTo(transitista.getId());
        assertThat(transitista.getMascotasEnTransito()).contains(perro);
        assertThat(solicitud.tieneEstadoAprobada()).isEqualTo(true);
    }

    @Test
    public void pasarAAdopcionConPerroPasaMascotaAEnAdopcion() {
        Transitista transitista = new Transitista(1L, new Localizacion(-50f, -50f, "prueba"), "prueba", "1234", "test@†est.com", 2, null);
        transitista.mostrarComoActivo();
        Mascota perro = new Mascota(8L, Mascota.Tipo.PERRO);
        SolicitudDeTransito solicitud = new SolicitudDeTransito(3L, new Date(), perro, 10L, 1L);
        transitista.aceptar(solicitud);

        transitista.pasarAAdopcion(perro);

        assertThat(perro.estaEnAdopcion()).isEqualTo(true);
        assertThat(solicitud.estaEnCurso()).isEqualTo(false);
    }

    @Test
    public void agregarSolicitudConPerroLaAgregaAlTransitistaYCambiaSuEstado() {
        Transitista transitista = new Transitista(1L, new Localizacion(-50f, -50f,"prueba"), "prueba", "1234", "test@†est.com", 2, null);
        transitista.mostrarComoActivo();
        Mascota perro = new Mascota(8L, Mascota.Tipo.PERRO);
        SolicitudDeTransito solicitud = new SolicitudDeTransito(3L, new Date(), perro, 10L, 1L);

        transitista.agregar(solicitud);

        assertThat(transitista.getSolicitudes()).contains(solicitud);
        assertThat(solicitud.estaEnCurso()).isEqualTo(true);
    }

    @Test
    public void rechazarSolicitudConPerroLaDejaEnEstadoRechazada() {
        Transitista transitista = new Transitista(1L, new Localizacion(-50f, -50f,"prueba"), "prueba", "1234", "test@†est.com", 2, null);
        transitista.mostrarComoActivo();
        Mascota perro = new Mascota(8L, Mascota.Tipo.PERRO);
        SolicitudDeTransito solicitud = new SolicitudDeTransito(3L, new Date(), perro, 10L, 1L);

        transitista.rechazar(solicitud);

        assertThat(solicitud.tieneEstadoAprobada()).isEqualTo(false);
        assertThat(solicitud.estaEnCurso()).isEqualTo(false);
    }

    @Test
    public void recibirDonacionConDonacionTransfiereDineroATransitista() {
        ProcesadorPagos procesadorPagos = mock(ProcesadorPagos.class);
        String cbu = "11111111111111111111";
        Transitista transitista = new Transitista(1L, new Localizacion(-50f, -50f, "prueba"), "prueba", "1234", "test@†est.com", 2, procesadorPagos);
        transitista.setCbu(cbu);
        Donacion donacion = new Donacion(1L, new BigDecimal(100), new BigDecimal(1));
        doNothing().when(procesadorPagos).pagar(donacion.getMontoADepositar(), cbu);
        doNothing().when(procesadorPagos).recaudar(donacion.getMontoComision());

        transitista.recibirDonacion(donacion);

        assertThat(transitista.getDonacionesRecibidas()).contains(donacion);
        verify(procesadorPagos).pagar(donacion.getMontoADepositar(), cbu);
        verify(procesadorPagos).recaudar(donacion.getMontoComision());

    }
}