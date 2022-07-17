package com.rescatapp.api.domain;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Donacion {
    private final Long id;
    private final BigDecimal montoACobrar;
    private BigDecimal montoADepositar;
    private BigDecimal comision;
    private BigDecimal montoComision;

    private final BigDecimal comisionRegular = new BigDecimal(10);

    private final BigDecimal comisionRecurrente = new BigDecimal(5);

    private final BigDecimal maximoMontoComision = new BigDecimal(1000);

    public Donacion(Long id, BigDecimal montoACobrar, BigDecimal comision) {
        this.id = id;
        this.montoACobrar = montoACobrar;
        this.comision = comision;
        this.montoComision = montoACobrar.multiply(comision.setScale(10, RoundingMode.HALF_DOWN).divide(new BigDecimal(100), RoundingMode.HALF_DOWN));
        this.montoADepositar = montoACobrar.subtract(this.montoComision);
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getMontoACobrar() {
        return montoACobrar;
    }

    public BigDecimal getMontoADepositar() {
        return montoADepositar;
    }

    public BigDecimal getMontoComision() {
        return montoComision;
    }

    public void procesar(String cbuDestino, int cantidadDonacionesRecibidas, ProcesadorPagos procesadorPagos) {
        this.comision = cantidadDonacionesRecibidas >= limiteRecurrente ? comisionRecurrente : comisionRegular;
        this.montoComision = montoACobrar.multiply(comision.setScale(10, RoundingMode.HALF_DOWN).divide(new BigDecimal(100), RoundingMode.HALF_DOWN)).min(maximoMontoComision);
        this.montoADepositar = montoACobrar.subtract(this.montoComision);
        procesadorPagos.pagar(montoADepositar, cbuDestino);
        procesadorPagos.recaudar(montoComision);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Donacion donacion = (Donacion) o;
        return id.equals(donacion.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
