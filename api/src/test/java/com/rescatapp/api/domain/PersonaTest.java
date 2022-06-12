package com.rescatapp.api.domain;

import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class PersonaTest {
    public static class DummyPersona extends Persona {
        public DummyPersona(Long id, Localizacion localizacion, String nombre, String telefono, String email, ProcesadorPagos procesadorPagos) {
            super(id, localizacion, nombre, telefono, email, procesadorPagos);
        }
    }

    @Test
    public void donar() {
        ProcesadorPagos procesadorPagos = mock(ProcesadorPagos.class);
        String cbu = "11111111111111111111";
        Persona persona = new DummyPersona(1L, new Localizacion(-50f, -50f, "prueba"), "prueba", "1234", "test@†est.com",  procesadorPagos);
        persona.setCbu(cbu);
        Donacion donacion = new Donacion(1L, new BigDecimal(100), new BigDecimal(1));
        doNothing().when(procesadorPagos).pagar(donacion.getMontoADepositar(), cbu);

        persona.donar(donacion);

        assertThat(persona.getDonacionesRealizadas()).contains(donacion);
        verify(procesadorPagos).cobrar(donacion.getMontoACobrar(), cbu);

    }
}