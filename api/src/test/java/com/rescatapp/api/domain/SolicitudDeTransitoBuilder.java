package com.rescatapp.api.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.Arrays.asList;

public class SolicitudDeTransitoBuilder {
    private static SolicitudDeTransito solicitudDeTransito;

    private SolicitudDeTransitoBuilder() {
        this.solicitudDeTransito = null;
    }

    public static SolicitudDeTransito SolicitudDeTransitoRespuestaInmediata(){
        SolicitudDeTransitoBuilder solicitudDeTransitoBuilder = new SolicitudDeTransitoBuilder();
        SolicitudDeTransitoBuilder.solicitudDeTransito = new SolicitudDeTransito(2L,new Date(2022,6,1,8,0,0),new Mascota(8L, Mascota.Tipo.PERRO), 1L, 2L);
        SolicitudDeTransitoBuilder.solicitudDeTransito.fechaDeRespuesta = new Date(2022,6,1,8,05,0);
        return solicitudDeTransito;
    }

    public static SolicitudDeTransito SolicitudDeTransitoRespuestaConMuchaDemora(){
        SolicitudDeTransitoBuilder solicitudDeTransitoBuilder = new SolicitudDeTransitoBuilder();
        SolicitudDeTransitoBuilder.solicitudDeTransito = new SolicitudDeTransito(2L,new Date(2022,6,1,9,0,0),new Mascota(8L, Mascota.Tipo.PERRO), 1L, 2L);
        SolicitudDeTransitoBuilder.solicitudDeTransito.fechaDeRespuesta = new Date(2022,6,1,8,05,0);
        return solicitudDeTransito;
    }



    public SolicitudDeTransito build(){
        return this.solicitudDeTransito;
    }
}
