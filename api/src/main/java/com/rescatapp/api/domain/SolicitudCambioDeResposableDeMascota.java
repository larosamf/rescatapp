package com.rescatapp.api.domain;

import java.time.LocalDateTime;

public abstract class SolicitudCambioDeResposableDeMascota {
    private Long id;
    private LocalDateTime fechaDeCreacion;
    LocalDateTime fechaDeRespuesta;
    private boolean estaAprobada;
    private Mascota mascota;
    protected Usuario usuarioSolicitante;
    private Usuario usuarioSolicitado;
    private boolean enCurso;

    public SolicitudCambioDeResposableDeMascota(Long id, LocalDateTime fechaDeCreación, Mascota mascota, Usuario solicitante, Usuario solicitado) {
        this.id = id;
        this.fechaDeCreacion = fechaDeCreación;
        this.fechaDeRespuesta = null;
        this.estaAprobada = false;
        this.mascota = mascota;
        this.usuarioSolicitante = solicitante;
        this.usuarioSolicitado = solicitado;
        this.enCurso = true;
    }

    public boolean aprobar() {
        this.estaAprobada = true;
        this.enCurso = false;
        this.fechaDeRespuesta = LocalDateTime.now();
        return true;
    }

    public boolean rechazar() {
        this.estaAprobada = false;
        this.enCurso = false;
        this.fechaDeRespuesta = LocalDateTime.now();
        return false;
    }

    public Mascota getMascota() {
        return this.mascota;
    }

    public boolean estaAprobada() {
        return this.estaAprobada;
    }

    public boolean estaRechazada() {
        return !this.estaAprobada;
    }


    public boolean estaEnCurso() {
        return this.enCurso;
    }

    public Usuario getUsuarioSolicitante() {
        return usuarioSolicitante;
    }

    public LocalDateTime getFechaDeCreacion() {
        return this.fechaDeCreacion;
    }

    public LocalDateTime getFechaDeRespuesta() {
        return this.fechaDeRespuesta;
    }

    public void cambiarUsuarioSolicitado(Usuario solicitado) {
        this.usuarioSolicitado = solicitado;
    }

}
