package com.rescatapp.api.domain;

import java.util.Date;

public class SolicitudDeAdopcion extends SolicitudCambioDeResposableDeMascota {
    public SolicitudDeAdopcion(Long id, Date fechaDeCreación, Mascota mascota, Long idSolicitante, Long idSolicitado) {
        super(id, fechaDeCreación, mascota, idSolicitante, idSolicitado);
    }
}