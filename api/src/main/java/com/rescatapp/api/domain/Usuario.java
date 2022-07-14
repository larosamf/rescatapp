package com.rescatapp.api.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class Usuario {
    private final Long id;
    private final double max_km_para_considerar_cercanos = 1;
    private Localizacion localizacion;
    private String nombre;
    private String telefono;
    private String email;
    private String cbu;
    private final List<Donacion> donacionesRealizadas = new ArrayList<>();
    protected final ProcesadorPagos procesadorPagos;
    final List<Puntuacion> puntuacionesRecibidas = new ArrayList<>();
    private boolean reportado;

    public Usuario(Long id, Localizacion localizacion, String nombre, String telefono, String email, ProcesadorPagos procesadorPagos) {
        this.id = id;
        this.localizacion = localizacion;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.procesadorPagos = procesadorPagos;
        this.reportado = false;
    }

    public void donar(Donacion donacion) {
        procesadorPagos.cobrar(donacion.getMontoACobrar(), this.cbu);
        this.donacionesRealizadas.add(donacion);
    }
    public void agregarPuntuacion(Puntuacion puntuacion) {
        this.puntuacionesRecibidas.add(puntuacion);
    }

    public float calcularPuntuacionTotal() {
        float total = 0;
        if (this.puntuacionesRecibidas.size() == 0)
            return 0;
        for (Puntuacion puntaje : this.puntuacionesRecibidas) {
            total += puntaje.getEstrellas();
        }
        return total / this.puntuacionesRecibidas.size();
    }

    public boolean estaCercaDe(Localizacion localizacion) {
        if (localizacion.calcularDistanciaEnKilometros(this.localizacion) < this.max_km_para_considerar_cercanos)
            return true;
        return false;
    }

    public Long getId() {
        return id;
    }

    public Localizacion getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(Localizacion localizacion) {
        this.localizacion = localizacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    public List<Donacion> getDonacionesRealizadas() {
        return donacionesRealizadas;
    }

    public void reportar() {
        this.reportado = true;
    }

    public boolean fueResportado() {
        return this.reportado;
    }
}
