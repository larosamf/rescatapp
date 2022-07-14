package com.rescatapp.api.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class TransitistaBuilder {
    private Transitista transitista;

    private TransitistaBuilder() {
        this.transitista = null;
    }

    public static TransitistaBuilder transitistaActivo() {
        TransitistaBuilder transitistaBuilder = new TransitistaBuilder();
        transitistaBuilder.transitista = new Transitista(1L, new Localizacion(-50f, -50f, "prueba"), "prueba", "1234", "test@†est.com", 3, null);
        transitistaBuilder.transitista.mostrarComoActivo();
        return transitistaBuilder;
    }

    public static TransitistaBuilder transitistaConSolicitudesDeTransicion(List<SolicitudDeTransito> solicitudes) {
        TransitistaBuilder transitistaBuilder = new TransitistaBuilder();
        transitistaBuilder.transitista = new Transitista(1L, new Localizacion(-50f, -50f, "prueba"), "prueba", "1234", "test@†est.com", 3, null);
        transitistaBuilder.transitista.solicitudesDeTransito = solicitudes;
        return transitistaBuilder;
    }

    public static TransitistaBuilder transitistaActivoConMascotas() {
        TransitistaBuilder transitistaBuilder = new TransitistaBuilder();
        transitistaBuilder.transitista = new Transitista(1L, new Localizacion(-50f, -50f, "prueba"), "prueba", "1234", "test@†est.com", 3, null);
        List<Mascota> mascotas = new ArrayList<>(asList(new Mascota(8L, Mascota.Tipo.PERRO), new Mascota(9L, Mascota.Tipo.PERRO), new Mascota(10L, Mascota.Tipo.PERRO)));
        transitistaBuilder.transitista.mascotasTransitadoActualmente = mascotas;
        transitistaBuilder.transitista.mostrarComoActivo();
        return transitistaBuilder;
    }

    public static TransitistaBuilder transitistaPuntuado3Veces() {
        TransitistaBuilder transitistaBuilder = new TransitistaBuilder();
        transitistaBuilder.transitista = new Transitista(1L, new Localizacion(-50f, -50f, "prueba"), "prueba", "1234", "test@†est.com", 3, null);
        Puntuacion puntuacion_1 = new Puntuacion(2.5F, "Buena persona.");
        transitistaBuilder.transitista.agregarPuntuacion(puntuacion_1);
        Puntuacion puntuacion_2 = new Puntuacion(5F, "Buena persona.");
        transitistaBuilder.transitista.agregarPuntuacion(puntuacion_2);
        Puntuacion puntuacion_3 = new Puntuacion(1.5F, "Buena persona.");
        transitistaBuilder.transitista.agregarPuntuacion(puntuacion_3);
        return transitistaBuilder;
    }

    public static TransitistaBuilder transitistaPuntuado16Veces() {
        TransitistaBuilder transitistaBuilder = new TransitistaBuilder();
        transitistaBuilder.transitista = new Transitista(1L, new Localizacion(-50f, -50f, "prueba"), "prueba", "1234", "test@†est.com", 3, null);
        Puntuacion puntuacion = new Puntuacion(2.5F, "Buena persona.");
        for (int i = 0; i < 16; i++) {
            transitistaBuilder.transitista.agregarPuntuacion(puntuacion);
        }
        return transitistaBuilder;
    }

    public static TransitistaBuilder transitistaPuntuado3Veces1PorAdoptista() {
        TransitistaBuilder transitistaBuilder = new TransitistaBuilder();
        transitistaBuilder.transitista = new Transitista(1L, new Localizacion(-50f, -50f, "prueba"), "prueba", "1234", "test@†est.com", 3, null);
        Puntuacion puntuacion_1 = new Puntuacion(2.5F, "Buena persona.");
        transitistaBuilder.transitista.agregarPuntuacion(puntuacion_1);
        Puntuacion puntuacion_2 = new Puntuacion(5F, "Buena persona.", true);
        transitistaBuilder.transitista.agregarPuntuacion(puntuacion_2);
        Puntuacion puntuacion_3 = new Puntuacion(1.5F, "Buena persona.");
        transitistaBuilder.transitista.agregarPuntuacion(puntuacion_3);
        return transitistaBuilder;
    }

    public TransitistaBuilder conSolicitudesPendientes(int cantidad, Rescatista rescatista, Mascota mascota) {
        for (int i = 1; i < cantidad + 1; i++) {
            this.transitista.solicitudesDeTransito.add(new SolicitudDeTransito((long) i, LocalDateTime.now(), mascota, rescatista, this.transitista));
        }
        return this;
    }


    public Transitista build() {
        return this.transitista;
    }

}
