package com.rescatapp.api.domain;

import com.rescatapp.api.domain.exceptions.ValorComisionIncorrectoException;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class UsuarioTest {
    public static class DummyUsuario extends Usuario {
        public DummyUsuario(Long id, Localizacion localizacion, String nombre, String telefono, String email, ProcesadorPagos procesadorPagos) {
            super(id, localizacion, nombre, telefono, email, procesadorPagos);
        }
    }

    @Test
    public void donarConUsuarioLeAgregaUnaDonacion() throws ValorComisionIncorrectoException {
        ProcesadorPagos procesadorPagos = mock(ProcesadorPagos.class);
        String cbu = "11111111111111111111";
        Usuario usuario = new DummyUsuario(1L, new Localizacion(-50f, -50f, "prueba"), "prueba", "1234", "test@†est.com",  procesadorPagos);
        usuario.setCbu(cbu);
        Donacion donacion = new Donacion(1L, new BigDecimal(100), new BigDecimal(1));
        doNothing().when(procesadorPagos).pagar(donacion.getMontoADepositar(), cbu);

        usuario.donar(donacion);

        assertThat(usuario.getDonacionesRealizadas()).contains(donacion);
        verify(procesadorPagos).cobrar(donacion.getMontoACobrar(), cbu);
    }

    @Test
    public void estaCercaDeConUsuariosUbicadosAMenosDe1kmDevuelveTrue() {
        ProcesadorPagos procesadorPagos = mock(ProcesadorPagos.class);
        Usuario persona = new DummyUsuario(1L, new Localizacion(50f, 50f, "prueba"), "prueba", "1234", "test@†est.com",  procesadorPagos);
        Localizacion localizacion = new Localizacion(50.001f, 50.01f, "prueba");

        boolean resultado = persona.estaCercaDe(localizacion);

        assertThat(resultado).isEqualTo(true);
    }

    @Test
    public void estaCercaDeConUsuariosUbicadosAMasDe1kmDevuelveFalse() {
        ProcesadorPagos procesadorPagos = mock(ProcesadorPagos.class);
        Usuario persona = new DummyUsuario(1L, new Localizacion(50f, 50f, "prueba"), "prueba", "1234", "test@†est.com",  procesadorPagos);
        Localizacion localizacion = new Localizacion(51f, 50f, "prueba");

        boolean resultado = persona.estaCercaDe(localizacion);

        assertThat(resultado).isEqualTo(false);
    }

    @Test
    public void calcularPuntuacionTotalConUsuarioNoTransitistaDevuelvePromedio() {
        ProcesadorPagos procesadorPagos = mock(ProcesadorPagos.class);
        Usuario usuario = new UsuarioTest.DummyUsuario(1L, new Localizacion(-50f, -50f, "prueba"), "prueba", "1234", "test@†est.com",  procesadorPagos);
        Puntuacion puntuacion_1 = new Puntuacion(2.5F, "Buena persona.");
        usuario.agregarPuntuacion(puntuacion_1);
        Puntuacion puntuacion_2 = new Puntuacion(5F, "Buena persona.");
        usuario.agregarPuntuacion(puntuacion_2);
        Puntuacion puntuacion_3 = new Puntuacion(1.5F, "Buena persona.");
        usuario.agregarPuntuacion(puntuacion_3);

        float resultado = usuario.calcularPuntuacionTotal();
        assertThat(resultado).isEqualTo(3F);

    }
}