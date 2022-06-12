package com.rescatapp.api.domain;

import java.util.ArrayList;
import java.util.List;

public class Transitista extends Persona {
    private int capacidad;
    private final List<Mascota> mascotasTransitadas = new ArrayList<>();
    private final List<Mascota> mascotasTransitadoActualmente = new ArrayList<>();
    private boolean estaActivo;
    private List<SolicitudDeTransito> solicitudes = new ArrayList<>();

    public Transitista(Long id, Localizacion localizacion, String nombre, String telefono, String email, int capacidad) {
        super(id, localizacion, nombre, telefono, email);
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
        this.solicitudes.add(solicitud);
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

    public List<SolicitudDeTransito> getSolicitudes() {
        return this.solicitudes;
    }
}
