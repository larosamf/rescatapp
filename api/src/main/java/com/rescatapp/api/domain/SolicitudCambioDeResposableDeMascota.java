package com.rescatapp.api.domain;

import java.util.Date;

public abstract class SolicitudCambioDeResposableDeMascota {
    private Long id;
    private Date fechaDeCreación;
    private Date fechaDeRespuesta;
    private boolean estaAprobada;
    private Mascota mascota;
    private Long idSolicitante;
    private Long idSolicitado;
    private boolean enCurso;

    public SolicitudCambioDeResposableDeMascota(Long id, Date fechaDeCreación, Mascota mascota, Long idSolicitante, Long idSolicitado) {
        this.id = id;
        this.fechaDeCreación = fechaDeCreación;
        this.fechaDeRespuesta = null;
        this.estaAprobada = false;
        this.mascota = mascota;
        this.idSolicitante = idSolicitante;
        this.idSolicitado = idSolicitado;
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

    public Long getIdSolicitante() {
        return idSolicitante;
    }
}
