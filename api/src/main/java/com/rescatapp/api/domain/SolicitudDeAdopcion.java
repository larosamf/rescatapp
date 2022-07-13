package com.rescatapp.api.domain;

import java.util.Date;

public class SolicitudDeAdopcion extends SolicitudCambioDeResposableDeMascota {
    public SolicitudDeAdopcion(Long id, Date fechaDeCreación, Mascota mascota, Usuario solicitante, Usuario solicitado) {
        super(id, fechaDeCreación, mascota, solicitante, solicitado);
    }
}