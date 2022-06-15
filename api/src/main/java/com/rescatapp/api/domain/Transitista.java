package com.rescatapp.api.domain;

import java.util.ArrayList;
import java.util.List;

public class Transitista extends Usuario {
    private int capacidad;
    private final List<Mascota> mascotasTransitadas = new ArrayList<>();
    List<Mascota> mascotasTransitadoActualmente = new ArrayList<>();
    private final List<Donacion> donacionesRecibidas = new ArrayList<>();
    private boolean estaActivo;
    List<SolicitudDeTransito> solicitudesDeTransito = new ArrayList<>();
    List<SolicitudDeAdopcion> solicitudesDeAdopcion = new ArrayList<>();

    public Transitista(Long id, Localizacion localizacion, String nombre, String telefono, String email, int capacidad, ProcesadorPagos procesadorPagos) {
        super(id, localizacion, nombre, telefono, email, procesadorPagos);
        this.capacidad = capacidad;
        this.estaActivo = false;
    }


    public boolean mostrarComoActivo(){
        this.estaActivo = true;
        return true;
    }

    public boolean mostrarComoNoActivo(){
        this.estaActivo = false;
        return false;
    }

    public boolean agregar(SolicitudDeTransito solicitud) {
        this.solicitudesDeTransito.add(solicitud);
        return true;
    }

    public Mascota aceptar(SolicitudDeTransito solicitud){
        if (this.capacidad > 0 && this.estaActivo) {
            solicitud.aprobar();
            Mascota mascota = solicitud.getMascota();
            mascota.setIdUsuarioResponsable(this.getId());
            this.mascotasTransitadoActualmente.add(mascota);
            this.capacidad--;
            return mascota;
        }
        return null;
    }

    public Mascota rechazar(SolicitudDeTransito solicitud){
        solicitud.rechazar();
        return null;
    }

    public boolean agregar(SolicitudDeAdopcion solicitud) {
        this.solicitudesDeAdopcion.add(solicitud);
        return true;
    }

    public Mascota aceptar(SolicitudDeAdopcion solicitud){
        Mascota mascota = solicitud.getMascota();
        if (this.mascotasTransitadoActualmente.contains(mascota) && this.estaActivo) {
            solicitud.aprobar();
            mascota.setIdUsuarioResponsable(solicitud.getIdSolicitante());
            this.mascotasTransitadoActualmente.remove(mascota);
            this.mascotasTransitadas.add(mascota);
            this.capacidad++;
            return mascota;
        }
        return null;
    }

    public Mascota rechazar(SolicitudDeAdopcion solicitud){
        solicitud.rechazar();
        return null;
    }

    public Mascota pasarAAdopcion(Mascota mascota) {
        if (mascotasTransitadoActualmente.contains(mascota)) {
            mascota.pasarAAdopcion();
            return mascota;
        }
        return null;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public List<Mascota> getMascotasEnTransito() {
        return mascotasTransitadoActualmente;
    }

    public List<Mascota> getmascotasTransitadas() {
        return mascotasTransitadas;
    }

    public List<SolicitudDeTransito> getSolicitudesDeTransito() {
        return this.solicitudesDeTransito;
    }

    public List<SolicitudDeAdopcion> getSolicitudesDeAdopcion() {
        return this.solicitudesDeAdopcion;
    }

    public List<Donacion> getDonacionesRecibidas() {
        return donacionesRecibidas;
    }

    public void recibirDonacion(Donacion donacion) {
        this.procesadorPagos.pagar(donacion.getMontoADepositar(), this.getCbu());
        this.procesadorPagos.recaudar(donacion.getMontoComision());
        this.donacionesRecibidas.add(donacion);
    }

}
