package com.rescatapp.api.domain;

import java.util.Date;

public class SolicitudDeTransito extends SolicitudCambioDeResposableDeMascota {
    public SolicitudDeTransito(Long id, Date fechaDeCreación, Mascota mascota, Long idSolicitante, Long idSolicitado) {
        super(id, fechaDeCreación, mascota, idSolicitante, idSolicitado);
    }
}
