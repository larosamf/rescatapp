package com.rescatapp.api.domain;

import java.util.Date;

public abstract class SolicitudCambioDeResposableDeMascota {
    private Long id;
    private Date fechaDeCreacion;
    Date fechaDeRespuesta;
    private boolean estaAprobada;
    private Mascota mascota;
    private Usuario usuarioSolicitante;
    private Usuario usuarioSolicitado;
    private boolean enCurso;

    public SolicitudCambioDeResposableDeMascota(Long id, Date fechaDeCreación, Mascota mascota, Usuario solicitante, Usuario solicitado) {
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
        this.fechaDeRespuesta = new Date();
        return true;
    }

    public boolean rechazar() {
        this.estaAprobada = false;
        this.enCurso = false;
        this.fechaDeRespuesta = new Date();
        return false;
    }

    public Mascota getMascota() {
        return this.mascota;
    }

    public boolean tieneEstadoAprobada() {
        return this.estaAprobada;
    }

    public boolean estaEnCurso() {
        return this.enCurso;
    }


    public Usuario getUsuarioSolicitante() {
        return usuarioSolicitante;
    }

    public Date getFechaDeCreacion() {
        return this.fechaDeCreacion;
    }

    public Date getFechaDeRespuesta() {
        return this.fechaDeRespuesta;
    }


}
