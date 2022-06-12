package com.rescatapp.api.domain;

import java.math.BigDecimal;

public interface ProcesadorPagos {
    void cobrar(BigDecimal monto, String cuenta);
    void pagar(BigDecimal monto, String cuenta);
    void recaudar(BigDecimal monto);

}
