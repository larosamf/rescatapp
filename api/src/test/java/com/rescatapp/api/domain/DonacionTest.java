package com.rescatapp.api.domain;

import com.rescatapp.api.domain.exceptions.ValorComisionIncorrectoException;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class DonacionTest {

    @Test
    public void getMontoACobrar() throws ValorComisionIncorrectoException {
        Donacion donacion = new Donacion(1L, new BigDecimal("100"), new BigDecimal(1));

        assertThat(donacion.getMontoACobrar()).isEqualByComparingTo(new BigDecimal(100));
    }

    @Test
    public void getMontoADepositar() throws ValorComisionIncorrectoException {
        Donacion donacion = new Donacion(1L, new BigDecimal(100), new BigDecimal(1));

        assertThat(donacion.getMontoADepositar()).isEqualByComparingTo(new BigDecimal(99));

    }

    @Test
    public void getMontoComision() throws ValorComisionIncorrectoException {
        Donacion donacion = new Donacion(1L, new BigDecimal(100), new BigDecimal(1));

        assertThat(donacion.getMontoComision()).isEqualByComparingTo(new BigDecimal(1));
    }

}