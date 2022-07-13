package com.rescatapp.api.domain;

import java.util.Date;

public class SolicitudDeTransitoBuilder {
    private SolicitudDeTransito solicitudDeTransito;

    private SolicitudDeTransitoBuilder() {
        this.solicitudDeTransito = null;
    }

    public static SolicitudDeTransitoBuilder SolicitudDeTransitoRespuestaInmediata(){
        Transitista transitista = TransitistaBuilder.transitistaActivo().build();
        Rescatista rescatista = RescatistaBuilder.rescatista().build();
        SolicitudDeTransitoBuilder solicitudDeTransitoBuilder = new SolicitudDeTransitoBuilder();
        solicitudDeTransitoBuilder.solicitudDeTransito = new SolicitudDeTransito(2L,new Date(2022,6,1,8,0,0),new Mascota(8L, Mascota.Tipo.PERRO), rescatista, transitista);
        solicitudDeTransitoBuilder.solicitudDeTransito.fechaDeRespuesta = new Date(2022,6,1,8,05,0);
        return solicitudDeTransitoBuilder;
    }

    public static SolicitudDeTransitoBuilder SolicitudDeTransitoRespuestaConMuchaDemora(){
        Transitista transitista = TransitistaBuilder.transitistaActivo().build();
        Rescatista rescatista = RescatistaBuilder.rescatista().build();
        SolicitudDeTransitoBuilder solicitudDeTransitoBuilder = new SolicitudDeTransitoBuilder();
        solicitudDeTransitoBuilder.solicitudDeTransito = new SolicitudDeTransito(2L,new Date(2022,6,1,8,0,0),new Mascota(8L, Mascota.Tipo.PERRO), rescatista, transitista);
        solicitudDeTransitoBuilder.solicitudDeTransito.fechaDeRespuesta = new Date(2022,6,1,9,5,0);
        return solicitudDeTransitoBuilder;
    }


    public SolicitudDeTransito build(){
        return this.solicitudDeTransito;
    }
}
