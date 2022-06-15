package com.rescatapp.api.domain;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class TransitistaBuilder {
    private Transitista transitista;

    private TransitistaBuilder() {
        this.transitista = null;
    }

    public static TransitistaBuilder transitistaConSolicitudesDeTransicion(List<SolicitudDeTransito> solicitudes){
        TransitistaBuilder transitistaBuilder = new TransitistaBuilder();
        transitistaBuilder.transitista = new Transitista(1L, new Localizacion(-50f, -50f, "prueba"), "prueba", "1234", "test@†est.com",3,null);
        transitistaBuilder.transitista.solicitudesDeTransito = solicitudes;
        return transitistaBuilder;
    }

    public static TransitistaBuilder transitistaActivoConMascotas(){
        TransitistaBuilder transitistaBuilder = new TransitistaBuilder();
        transitistaBuilder.transitista = new Transitista(1L, new Localizacion(-50f, -50f, "prueba"), "prueba", "1234", "test@†est.com",3,null);
        List<Mascota> mascotas = new ArrayList<>(asList(new Mascota(8L, Mascota.Tipo.PERRO), new Mascota(9L, Mascota.Tipo.PERRO), new Mascota(10L, Mascota.Tipo.PERRO)));
        transitistaBuilder.transitista.mascotasTransitadoActualmente = mascotas;
        transitistaBuilder.transitista.mostrarComoActivo();
        return transitistaBuilder;
    }

    public Transitista build(){
        return this.transitista;
    }
}
