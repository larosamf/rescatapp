package com.rescatapp.api.domain;

import java.time.LocalDateTime;

public class SolicitudDeAdopcion extends SolicitudCambioDeResposableDeMascota {
    public SolicitudDeAdopcion(Long id, LocalDateTime fechaDeCreación, Mascota mascota, Usuario solicitante, Usuario solicitado) {
        super(id, fechaDeCreación, mascota, solicitante, solicitado);
    }
}