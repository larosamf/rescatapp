package com.rescatapp.api.domain;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Donacion {
    private final Long id;
    private final BigDecimal montoACobrar;
    private final BigDecimal montoADepositar;
    private final BigDecimal montoComision;
    private final BigDecimal comision;

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

    public BigDecimal getComision() {
        return comision;
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
