package com.rescatapp.api.domain;

import com.rescatapp.api.domain.exceptions.NoSeCompletoRegistroDeMascotaException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.lang.Double;

public class Institucion {
    private final Long id;
    private Localizacion localizacion;
    private String nombre;
    private String telefono;
    private String email;
    private String cbu;
    private final List<Donacion> donacionesRealizadas = new ArrayList<>();
    protected final ProcesadorPagos procesadorPagos;
    private List<Transitista> transitistas = new ArrayList<>();
    private List<Mascota> mascotasTransitadoActualmente = new ArrayList<>();

    public Institucion(Long id, Localizacion localizacion, String nombre, String telefono, String email, ProcesadorPagos procesadorPagos) {
        this.id = id;
        this.localizacion = localizacion;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.procesadorPagos = procesadorPagos;
    }

    public void agregarTransitista(Transitista transitista) {
        this.transitistas.add(transitista);
    }

    public void aceptar(SolicitudDeTransito solicitud) {
        /*if (horario no es entre 9 y 18)
            lanzar excepcion de institucion cerrada*/

        Mascota mascota = solicitud.getMascota();
        if (!mascota.completoResgitro()) {
            throw new NoSeCompletoRegistroDeMascotaException("No se completo el registro de la mascota");
        }
        this.mascotasTransitadoActualmente.add(mascota);

        this.getRankingTransitistas().forEach((transitista) -> {
            try {
                transitista.aceptar(solicitud);
                this.mascotasTransitadoActualmente.remove(mascota);
                return;
            } catch (Exception e) {
            }
        });

        /*Lanzar excepcion de no se encontro transitista, probar de nuevo mañana*/
    }

    private List<Transitista> getRankingTransitistas() {
        /*return Collections.sort(this.transitistas, (Comparator<Transitista>) (t1, t2) -> {
            Localizacion localizacion1 = t1.getLocalizacion();
            Localizacion localizacion2 = t2.getLocalizacion();
            double distancia1 = localizacion.calcularDistanciaEnKilometros(localizacion2);
            double distancia2 = localizacion1.calcularDistanciaEnKilometros(localizacion2);

            float puntuacion1 = t1.calcularPuntuacionTotal();
            float puntuacion2 = t2.calcularPuntuacionTotal();
            return (distancia1-puntuacion1).compareTo(distancia2-puntuacion2);
        });*/
        return this.transitistas;
    }
}
