package com.rescatapp.api.domain;

import com.rescatapp.api.domain.exceptions.NoSeCompletoRegistroDeMascotaException;
import com.rescatapp.api.domain.exceptions.UsuarioNoTieneEsaMascotaException;
import com.rescatapp.api.domain.exceptions.UsuarioReportadoException;
import com.rescatapp.api.domain.exceptions.UsuarioSinCapacidadException;
import java.time.temporal.ChronoUnit;
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

    List<Transitista> contactosTransitistas = new ArrayList<>();

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

    public Mascota aceptar(SolicitudDeTransito solicitud) {
        if (solicitud.getUsuarioSolicitante().fueResportado()) {
            throw new UsuarioReportadoException("El rescatista no es confiable");
        }

        if (this.capacidad == 0 || !this.estaActivo) {
            throw new UsuarioSinCapacidadException("El transitista no tiene capacidad para recibir mas mascotas");
        }

        Mascota mascota = solicitud.getMascota();
        if (!mascota.completoResgitro()) {
            throw new NoSeCompletoRegistroDeMascotaException("No se completo el registro de la mascota");
        }

        solicitud.aprobar();
        mascota.setUsuarioResponsable(this);
        this.mascotasTransitadoActualmente.add(mascota);
        this.capacidad--;
        return mascota;
    }

    public void rechazar(SolicitudDeTransito solicitud){
        solicitud.rechazar();
        if (contactosTransitistas.size() != 0) {
            solicitud.cambiarUsuarioSolicitado(contactosTransitistas.get(0));
        }
    }

    public boolean agregar(SolicitudDeAdopcion solicitud) {
        this.solicitudesDeAdopcion.add(solicitud);
        return true;
    }

    public Mascota aceptar(SolicitudDeAdopcion solicitud){
        Mascota mascota = solicitud.getMascota();
        if (this.mascotasTransitadoActualmente.contains(mascota) && this.estaActivo) {
            solicitud.aprobar();
            mascota.setUsuarioResponsable(solicitud.getUsuarioSolicitante());
            this.mascotasTransitadoActualmente.remove(mascota);
            this.mascotasTransitadas.add(mascota);
            this.capacidad++;
            return mascota;
        }
        throw new UsuarioNoTieneEsaMascotaException("El transitista no esta a cargo de esa mascotas");
    }

    public void rechazar(SolicitudDeAdopcion solicitud){
        solicitud.rechazar();
    }

    public Mascota pasarAAdopcion(Mascota mascota) {
        if (mascotasTransitadoActualmente.contains(mascota)) {
            mascota.pasarAAdopcion();
            return mascota;
        }
        throw new UsuarioNoTieneEsaMascotaException("El transitista no esta a cargo de esa mascotas");
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
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

    public float calcularPuntuacionTotal() {
        float total = 0;
        float penalizacion = 0;
        long diferenciaFechas;
        long tiempoAceptableEnMinutos = 15;
        float result;
        long divisor = this.puntuacionesRecibidas.size();
        if (this.puntuacionesRecibidas.size() == 0)
            return 0;
        //Caso por si tiene menos de 15 puntuaciones
        if (this.puntuacionesRecibidas.size() <= 15) {
            for (Puntuacion puntaje : this.puntuacionesRecibidas) {
                total += puntaje.getEstrellas();
                if (puntaje.provieneDeAdoptista()) {
                    total += puntaje.getEstrellas();
                    divisor += 1;
                }
            }
        //Caso por si tiene mas de 15 puntuaciones
        }else{
            for (Puntuacion puntaje : this.puntuacionesRecibidas) {
                total += puntaje.getEstrellas();
            }
        }
        //Resto penalizaciones por administrar lentamente las solicitudes de transito
        for (SolicitudDeTransito solicitud : this.solicitudesDeTransito) {
            diferenciaFechas = ChronoUnit.MINUTES.between(solicitud.getFechaDeCreacion(), solicitud.getFechaDeRespuesta());
            if (diferenciaFechas > tiempoAceptableEnMinutos) {
                penalizacion += 0.1F;
            }
        }
        result = total - penalizacion;
        //Devuelvo 0 tiene mas penalizaciones que puntuacion y devuelve el promedio en caso contrario
        if (result <= 0){
            return 0;
        }else{
            return result / divisor;
        }
    }

}
