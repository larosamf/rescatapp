package com.rescatapp.api.domain;

import java.time.LocalDateTime;

public class SolicitudDeAdopcion extends SolicitudCambioDeResposableDeMascota {

    protected Adoptista usuarioSolicitante;

    public SolicitudDeAdopcion(Long id, LocalDateTime fechaDeCreación, Mascota mascota, Adoptista solicitante, Usuario solicitado) {
        super(id, fechaDeCreación, mascota, solicitante, solicitado);
        this.usuarioSolicitante = solicitante;
    }
    public Adoptista getUsuarioSolicitante() {
        return this.usuarioSolicitante;
    }
}