package com.rescatapp.api.domain;

import com.rescatapp.api.domain.exceptions.SolicitudVencidaException;
import com.rescatapp.api.domain.exceptions.*;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class TransitistaTest {

    @Test
    public void aceptarConSolicitudDeTransitoYTransititasActivoAgregaMascotaASuCargo() {
        Transitista transitista = TransitistaBuilder.transitistaActivo().build();
        Rescatista rescatista = RescatistaBuilder.rescatista().build();
        Mascota perro = new Mascota(8L, Mascota.Tipo.PERRO);
        perro.setCondicionFisica(Mascota.CondicionFisica.SANO);
        perro.setPathFoto("/home/fotos/41351.png");
        SolicitudDeTransito solicitud = new SolicitudDeTransito(3L, LocalDateTime.now(), perro, rescatista, transitista);

        Mascota resultado = transitista.aceptar(solicitud);

        assertThat(resultado).isEqualTo(perro);
        assertThat(resultado.getUsuarioResponsable()).isEqualTo(transitista);
        assertThat(transitista.getMascotasEnTransito()).contains(perro);
        assertThat(solicitud.estaAprobada()).isEqualTo(true);
    }

    @Test
    public void pasarAAdopcionConSolicitudDeTransitoPasaMascotaAEnAdopcion() {
        Transitista transitista = TransitistaBuilder.transitistaActivo().build();
        Rescatista rescatista = RescatistaBuilder.rescatista().build();
        Mascota perro = new Mascota(8L, Mascota.Tipo.PERRO);
        perro.setCondicionFisica(Mascota.CondicionFisica.SANO);
        perro.setPathFoto("/home/fotos/41351.png");
        SolicitudDeTransito solicitud = new SolicitudDeTransito(3L, LocalDateTime.now(), perro, rescatista, transitista);

        transitista.aceptar(solicitud);

        transitista.pasarAAdopcion(perro);

        assertThat(perro.estaEnAdopcion()).isEqualTo(true);
    }

    @Test
    public void aceptarSolicitudDeTransitoConTransitistaSinCapacidadLanzaException() {
        Transitista transitista = TransitistaBuilder.transitistaActivo().build();
        transitista.setCapacidad(0);
        Rescatista rescatista = RescatistaBuilder.rescatista().build();
        Mascota perro = new Mascota(8L, Mascota.Tipo.PERRO);
        SolicitudDeTransito solicitud = new SolicitudDeTransito(3L, LocalDateTime.now(), perro, rescatista, transitista);


        assertThatThrownBy(() -> transitista.aceptar(solicitud)).isInstanceOf(UsuarioSinCapacidadException.class);
    }

    @Test
    public void aceptarConSolicitudDeTransitoVencidaLanzaException() {
        Transitista transitista = TransitistaBuilder.transitistaActivo().build();
        Rescatista rescatista = RescatistaBuilder.rescatista().build();
        Mascota perro = new Mascota(8L, Mascota.Tipo.PERRO);
        perro.setCondicionFisica(Mascota.CondicionFisica.SANO);
        perro.setPathFoto("/home/fotos/41351.png");
        SolicitudDeTransito solicitud = new SolicitudDeTransito(3L, LocalDateTime.now().minus(Duration.ofMinutes(20)), perro, rescatista, transitista);

        assertThatThrownBy(() -> transitista.aceptar(solicitud)).isInstanceOf(SolicitudVencidaException.class);
    }

    @Test
    public void agregarSolicitudConSolicitudDeTransitoLaAgregaAlTransitista() {
        Transitista transitista = TransitistaBuilder.transitistaActivo().build();
        Rescatista rescatista = RescatistaBuilder.rescatista().build();
        Mascota perro = new Mascota(8L, Mascota.Tipo.PERRO);
        SolicitudDeTransito solicitud = new SolicitudDeTransito(3L, LocalDateTime.now(), perro, rescatista, transitista);

        transitista.agregar(solicitud);

        assertThat(transitista.getSolicitudesDeTransito()).contains(solicitud);
        assertThat(solicitud.estaEnCurso()).isEqualTo(true);
    }

    @Test
    public void agregarSolicitudCon5solicitudesPendientesLaRechaza() {
        Rescatista rescatista = RescatistaBuilder.rescatista().build();
        Mascota perro = new Mascota(8L, Mascota.Tipo.PERRO);
        Transitista transitista = TransitistaBuilder.transitistaActivo().conSolicitudesPendientes(4, rescatista, perro).build();
        SolicitudDeTransito solicitud = new SolicitudDeTransito(1L, LocalDateTime.now(), perro, rescatista, transitista);

        transitista.agregar(solicitud);

        assertThat(transitista.solicitudesDeTransito).contains(solicitud);
        assertThat(solicitud.estaRechazada()).isEqualTo(true);
    }


    @Test
    public void aceptarConSolicitudDeAdopcionYTransititasActivoAumentaEnUnoSuCapacidad() throws MascotaNoCumpleConPreferenciasBuscadasException {
        Transitista transitista = TransitistaBuilder.transitistaActivoConMascotas().build();
        Adoptista adoptista = AdoptistaBuilder.adoptista().build();
        Mascota perro = new Mascota(8L, Mascota.Tipo.PERRO);
        SolicitudDeAdopcion solicitud = new SolicitudDeAdopcion(1L, LocalDateTime.now(), perro, adoptista, transitista);

        Mascota resultado = transitista.aceptar(solicitud);

        assertThat(resultado).isEqualTo(perro);
        assertThat(resultado.getUsuarioResponsable()).isEqualTo(adoptista);
        assertThat(transitista.getmascotasTransitadas()).contains(perro);
        assertThat(solicitud.estaAprobada()).isEqualTo(true);
        assertThat(transitista.getCapacidad()).isEqualTo(4);
    }

    @Test
    public void aceptarSolicitudDeAdopcionConTransitistaSinMascotaLanzaException() {
        Transitista transitista = TransitistaBuilder.transitistaActivo().build();
        Adoptista adoptista = AdoptistaBuilder.adoptista().build();
        Mascota perro = new Mascota(8L, Mascota.Tipo.PERRO);
        SolicitudDeAdopcion solicitud = new SolicitudDeAdopcion(3L, LocalDateTime.now(), perro, adoptista, transitista);

        assertThatThrownBy(() -> transitista.aceptar(solicitud)).isInstanceOf(UsuarioNoTieneEsaMascotaException.class);
    }

    @Test
    public void agregarSolicitudConSolicitudDeAdopcionLaAgregaAlTransitista() {
        Transitista transitista = TransitistaBuilder.transitistaActivoConMascotas().build();
        Adoptista adoptista = AdoptistaBuilder.adoptista().build();
        Mascota perro = new Mascota(8L, Mascota.Tipo.PERRO);
        SolicitudDeAdopcion solicitud = new SolicitudDeAdopcion(3L, LocalDateTime.now(), perro, adoptista, transitista);

        transitista.agregar(solicitud);

        assertThat(transitista.getSolicitudesDeAdopcion()).contains(solicitud);
        assertThat(solicitud.estaEnCurso()).isEqualTo(true);
    }

    @Test
    public void rechazarSolicitudConSolicitudDeAdopcionLaDejaEnEstadoRechazada() {
        Transitista transitista = TransitistaBuilder.transitistaActivoConMascotas().build();
        Adoptista adoptista = AdoptistaBuilder.adoptista().build();
        Mascota perro = new Mascota(8L, Mascota.Tipo.PERRO);
        SolicitudDeAdopcion solicitud = new SolicitudDeAdopcion(1L, LocalDateTime.now(), perro, adoptista, transitista);

        transitista.rechazar(solicitud);

        assertThat(solicitud.estaAprobada()).isEqualTo(false);
        assertThat(solicitud.estaEnCurso()).isEqualTo(false);
    }

    @Test
    public void recibirDonacionConDonacionTransfiereDineroATransitista() throws ValorComisionIncorrectoException {
        ProcesadorPagos procesadorPagos = mock(ProcesadorPagos.class);
        String cbu = "11111111111111111111";
        Transitista transitista = new Transitista(1L, new Localizacion(-50f, -50f, "prueba"), "prueba", "1234", "test@†est.com", 2, procesadorPagos);
        transitista.setCbu(cbu);
        Donacion donacion = new Donacion(1L, new BigDecimal(100));
        doNothing().when(procesadorPagos).pagar(donacion.getMontoADepositar(), cbu);
        doNothing().when(procesadorPagos).recaudar(donacion.getMontoComision());

        transitista.recibirDonacion(donacion);

        assertThat(transitista.getDonacionesRecibidas()).contains(donacion);
        verify(procesadorPagos).pagar(donacion.getMontoADepositar(), cbu);
        verify(procesadorPagos).recaudar(donacion.getMontoComision());

    }

    @Test
    public void calcularPuntuacionTotalConTransitistaSinSolicitudesDevuelvePromedioCon3Puntuaciones() throws UsuarioQuePuntuaYaRealizoUnaPuntuacionAntesException {
        Transitista transitista = TransitistaBuilder.transitistaPuntuado3Veces().build();

        float resultado = transitista.calcularPuntuacionTotal();

        assertThat(resultado).isEqualTo(3F);
    }

    @Test
    public void calcularPuntuacionTotalConTransitistaSinSolicitudesDevuelvePromedioCon16Puntuaciones() throws UsuarioQuePuntuaYaRealizoUnaPuntuacionAntesException {
        Transitista transitista = TransitistaBuilder.transitistaPuntuado16Veces().build();

        float resultado = transitista.calcularPuntuacionTotal();

        assertThat(resultado).isEqualTo(2.5F);
    }

    @Test
    public void calcularPuntuacionTotalConTransitistaSinSolicitudesDevuelvePromedioCon3Puntuaciones1DeAdoptista() throws UsuarioQuePuntuaYaRealizoUnaPuntuacionAntesException {
        Transitista transitista = TransitistaBuilder.transitistaPuntuado3Veces1PorAdoptista().build();

        float resultado = transitista.calcularPuntuacionTotal();

        assertThat(resultado).isEqualTo(3.5F);
    }

    @Test
    public void calcularPuntuacionTotalConTransitistaSinSolicitudesDevuelvePromedioCon16PuntuacionesYUnaRespuestaInmediata() throws UsuarioQuePuntuaYaRealizoUnaPuntuacionAntesException {
        Transitista transitista = TransitistaBuilder.transitistaPuntuado16Veces().build();
        SolicitudDeTransito solicitud = SolicitudDeTransitoBuilder.SolicitudDeTransitoRespuestaInmediata().build();

        transitista.agregar(solicitud);

        float resultado = transitista.calcularPuntuacionTotal();

        assertThat(resultado).isEqualTo(2.5F);
    }

    @Test
    public void calcularPuntuacionTotalConTransitistaSinSolicitudesDevuelvePromedioCon16PuntuacionesYCincoRespuestasConMuchaDemora() throws UsuarioQuePuntuaYaRealizoUnaPuntuacionAntesException {
        Transitista transitista = TransitistaBuilder.transitistaPuntuado16Veces().build();
        SolicitudDeTransito solicitud = SolicitudDeTransitoBuilder.SolicitudDeTransitoRespuestaConMuchaDemora().build();
        for (int i = 0; i <= 5; i++) {
            transitista.agregar(solicitud);
        }

        float resultado = transitista.calcularPuntuacionTotal();

        assertThat(resultado).isEqualTo(2.4625F);
    }

    @Test
    public void calcularPuntuacionTotalConTransitistaSinPuntuaciones() {
        ProcesadorPagos procesadorPagos = mock(ProcesadorPagos.class);
        Transitista transitista = new Transitista(1L, new Localizacion(-50f, -50f, "prueba"), "prueba", "1234", "test@†est.com", 2, procesadorPagos);

        float resultado = transitista.calcularPuntuacionTotal();

        assertThat(resultado).isEqualTo(0);
    }

    @Test
    public void mismoUsuarioPuntuaMasDeUnaVezAlMismoTransitistaLanzaException() throws UsuarioQuePuntuaYaRealizoUnaPuntuacionAntesException {
        Transitista transitista = new Transitista(1L, new Localizacion(-50f, -50f, "prueba"), "prueba", "1234", "test@†est.com", 2, null);
        Usuario usuario_2 = new UsuarioTest.DummyUsuario(2L, new Localizacion(-50f, -50f, "prueba"), "prueba2", "5678", "test@†est.com",  null);
        Puntuacion puntuacion_1 = new Puntuacion(2.5F, "Buena persona.",usuario_2);
        transitista.agregarPuntuacion(puntuacion_1);
        Puntuacion puntuacion_2 = new Puntuacion(5F, "Buena persona.",usuario_2);

        assertThatThrownBy(() -> transitista.agregarPuntuacion(puntuacion_2)).isInstanceOf(UsuarioQuePuntuaYaRealizoUnaPuntuacionAntesException.class);
    }

    @Test
    public void aceptarSolicitudDeAdopcionConAdoptistaConPreferenciasDistintasALaMacotaLanzaException() {
        Transitista transitista = new Transitista(1L, new Localizacion(-50f, -50f, "prueba"), "prueba", "1234", "test@†est.com", 2, null);
        Adoptista adoptista = new Adoptista(1L, new Localizacion(-50f, -50f,"prueba"), "prueba", "1234", "test@†est.com", null);
        Mascota perro = new Mascota(8L, Mascota.Tipo.PERRO);
        perro.setCondicionFisica(Mascota.CondicionFisica.MUY_LASTIMADO);
        perro.setTamano(Mascota.Tamano.GRANDE);
        adoptista.setPreferencias(Adoptista.Tamano.GRANDE, Adoptista.Tipo.PERRO, Adoptista.CondicionFisica.SANO);
        SolicitudDeAdopcion solicitud = new SolicitudDeAdopcion(3L, LocalDateTime.now(), perro, adoptista, transitista);

        assertThatThrownBy(()->transitista.aceptar(solicitud)).isInstanceOf(MascotaNoCumpleConPreferenciasBuscadasException.class);
    }
}