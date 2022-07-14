package com.rescatapp.api.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class SolicitudDeTransito extends SolicitudCambioDeResposableDeMascota {
    public SolicitudDeTransito(Long id, LocalDateTime fechaDeCreación, Mascota mascota, Usuario solicitante, Usuario solicitado) {
        super(id, fechaDeCreación, mascota, solicitante, solicitado);
    }

}
