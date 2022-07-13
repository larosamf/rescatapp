package com.rescatapp.api.domain;

import org.junit.Test;

import static org.mockito.Mockito.*;

import java.math.BigDecimal;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class TransitistaTest {

    @Test
    public void aceptarConSolicitudDeTransitoYTransititasActivoAgregaMascotaASuCargo() {
        Transitista transitista = TransitistaBuilder.transitistaActivo().build();
        Rescatista rescatista = RescatistaBuilder.rescatista().build();
        Mascota perro = new Mascota(8L, Mascota.Tipo.PERRO);
        SolicitudDeTransito solicitud = new SolicitudDeTransito(3L, new Date(), perro, rescatista, transitista);

        Mascota resultado = transitista.aceptar(solicitud);

        assertThat(resultado).isEqualTo(perro);
        assertThat(resultado.getUsuarioResponsable()).isEqualTo(transitista);
        assertThat(transitista.getMascotasEnTransito()).contains(perro);
        assertThat(solicitud.tieneEstadoAprobada()).isEqualTo(true);
    }

    @Test
    public void pasarAAdopcionConSolicitudDeTransitoPasaMascotaAEnAdopcion() {
        Transitista transitista = TransitistaBuilder.transitistaActivo().build();
        Rescatista rescatista = RescatistaBuilder.rescatista().build();
        Mascota perro = new Mascota(8L, Mascota.Tipo.PERRO);
        SolicitudDeTransito solicitud = new SolicitudDeTransito(3L, new Date(), perro, rescatista, transitista);
        transitista.aceptar(solicitud);

        transitista.pasarAAdopcion(perro);

        assertThat(perro.estaEnAdopcion()).isEqualTo(true);
    }

    @Test
    public void agregarSolicitudConSolicitudDeTransitoLaAgregaAlTransitista() {
        Transitista transitista = TransitistaBuilder.transitistaActivo().build();
        Rescatista rescatista = RescatistaBuilder.rescatista().build();
        Mascota perro = new Mascota(8L, Mascota.Tipo.PERRO);
        SolicitudDeTransito solicitud = new SolicitudDeTransito(3L, new Date(), perro, rescatista, transitista);

        transitista.agregar(solicitud);

        assertThat(transitista.getSolicitudesDeTransito()).contains(solicitud);
        assertThat(solicitud.estaEnCurso()).isEqualTo(true);
    }

    @Test
    public void aceptarConSolicitudDeAdopcionYTransititasActivoAumentaEnUnoSuCapacidad() {
        Transitista transitista = TransitistaBuilder.transitistaActivoConMascotas().build();
        Adoptista adoptista = AdoptistaBuilder.adoptista().build();
        Mascota perro = new Mascota(8L, Mascota.Tipo.PERRO);
        SolicitudDeAdopcion solicitud = new SolicitudDeAdopcion(1L, new Date(), perro, adoptista, transitista);

        Mascota resultado = transitista.aceptar(solicitud);

        assertThat(resultado).isEqualTo(perro);
        assertThat(resultado.getUsuarioResponsable()).isEqualTo(adoptista);
        assertThat(transitista.getmascotasTransitadas()).contains(perro);
        assertThat(solicitud.tieneEstadoAprobada()).isEqualTo(true);
        assertThat(transitista.getCapacidad()).isEqualTo(4);
    }

    @Test
    public void agregarSolicitudConSolicitudDeAdopcionLaAgregaAlTransitista() {
        Transitista transitista = TransitistaBuilder.transitistaActivoConMascotas().build();
        Adoptista adoptista = AdoptistaBuilder.adoptista().build();
        Mascota perro = new Mascota(8L, Mascota.Tipo.PERRO);
        SolicitudDeAdopcion solicitud = new SolicitudDeAdopcion(3L, new Date(), perro, adoptista, transitista);

        transitista.agregar(solicitud);

        assertThat(transitista.getSolicitudesDeAdopcion()).contains(solicitud);
        assertThat(solicitud.estaEnCurso()).isEqualTo(true);
    }

    @Test
    public void rechazarSolicitudConSolicitudDeAdopcionLaDejaEnEstadoRechazada() {
        Transitista transitista = TransitistaBuilder.transitistaActivoConMascotas().build();
        Adoptista adoptista = AdoptistaBuilder.adoptista().build();
        Mascota perro = new Mascota(8L, Mascota.Tipo.PERRO);
        SolicitudDeAdopcion solicitud = new SolicitudDeAdopcion(1L, new Date(), perro, adoptista, transitista);

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

    @Test
    public void calcularPuntuacionTotalConTransitistaSinSolicitudesDevuelvePromedioCon3Puntuaciones() {
        Transitista transitista = TransitistaBuilder.transitistaPuntuado3Veces().build();

        float resultado = transitista.calcularPuntuacionTotal();

        assertThat(resultado).isEqualTo(3F);
    }

    @Test
    public void calcularPuntuacionTotalConTransitistaSinSolicitudesDevuelvePromedioCon16Puntuaciones() {
        Transitista transitista = TransitistaBuilder.transitistaPuntuado16Veces().build();

        float resultado = transitista.calcularPuntuacionTotal();

        assertThat(resultado).isEqualTo(2.5F);
    }

    @Test
    public void calcularPuntuacionTotalConTransitistaSinSolicitudesDevuelvePromedioCon3Puntuaciones1DeAdoptista() {
        Transitista transitista = TransitistaBuilder.transitistaPuntuado3Veces1PorAdoptista().build();

        float resultado = transitista.calcularPuntuacionTotal();

        assertThat(resultado).isEqualTo(3.5F);
    }

    @Test
    public void calcularPuntuacionTotalConTransitistaSinSolicitudesDevuelvePromedioCon16PuntuacionesYUnaRespuestaInmediata() {
        Transitista transitista = TransitistaBuilder.transitistaPuntuado16Veces().build();
        SolicitudDeTransito solicitud = SolicitudDeTransitoBuilder.SolicitudDeTransitoRespuestaInmediata().build();

        transitista.agregar(solicitud);

        float resultado = transitista.calcularPuntuacionTotal();

        assertThat(resultado).isEqualTo(2.5F);
    }

    @Test
    public void calcularPuntuacionTotalConTransitistaSinSolicitudesDevuelvePromedioCon16PuntuacionesYCincoRespuestasConMuchaDemora() {
        Transitista transitista = TransitistaBuilder.transitistaPuntuado16Veces().build();
        SolicitudDeTransito solicitud = SolicitudDeTransitoBuilder.SolicitudDeTransitoRespuestaConMuchaDemora().build();
        for (int i = 0; i <= 5; i++){
            transitista.agregar(solicitud);
        }

        float resultado = transitista.calcularPuntuacionTotal();

        assertThat(resultado).isEqualTo(2.4625F);
    }
}