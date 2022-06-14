package com.rescatapp.api.domain;

import java.util.List;

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

    public Transitista build(){
        return this.transitista;
    }
}
