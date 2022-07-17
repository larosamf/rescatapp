package com.rescatapp.api.domain;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DonacionTest {

    @Test
    public void getMontoACobrar() {
        Donacion donacion = new Donacion(1L, new BigDecimal("100"), new BigDecimal(1));

        assertThat(donacion.getMontoACobrar()).isEqualByComparingTo(new BigDecimal(100));
    }

    @Test
    public void getMontoADepositar() {
        Donacion donacion = new Donacion(1L, new BigDecimal(100), new BigDecimal(1));

        String cbu = "1111111111";
        donacion.procesar(cbu, 10, procesadorPagos);
        ArgumentCaptor<BigDecimal> montoApagar = ArgumentCaptor.forClass(BigDecimal.class);
        ArgumentCaptor<String> cbuEnviado = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<BigDecimal> comisionRecaudada = ArgumentCaptor.forClass(BigDecimal.class);

        verify(procesadorPagos).pagar(montoApagar.capture(), cbuEnviado.capture());
        verify(procesadorPagos).recaudar(comisionRecaudada.capture());

        assertThat(montoApagar.getValue()).isEqualByComparingTo(new BigDecimal(95));
        assertThat(comisionRecaudada.getValue()).isEqualByComparingTo(new BigDecimal(5));
        assertThat(cbuEnviado.getValue()).isEqualTo(cbu);
    }

    @Test
    public void getMontoComision() {
        Donacion donacion = new Donacion(1L, new BigDecimal(100), new BigDecimal(1));

        assertThat(montoApagar.getValue()).isEqualByComparingTo(new BigDecimal(49000));
        assertThat(comisionRecaudada.getValue()).isEqualByComparingTo(new BigDecimal(1000));
        assertThat(cbuEnviado.getValue()).isEqualTo(cbu);
    }


}