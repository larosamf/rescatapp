package com.rescatapp.api.domain;

import java.time.LocalDateTime;

public class SolicitudDeTransitoBuilder {
    private SolicitudDeTransito solicitudDeTransito;

    private SolicitudDeTransitoBuilder() {
        this.solicitudDeTransito = null;
    }

    public static SolicitudDeTransitoBuilder SolicitudDeTransitoRespuestaInmediata(){
        Transitista transitista = TransitistaBuilder.transitistaActivo().build();
        Rescatista rescatista = RescatistaBuilder.rescatista().build();
        SolicitudDeTransitoBuilder solicitudDeTransitoBuilder = new SolicitudDeTransitoBuilder();
        solicitudDeTransitoBuilder.solicitudDeTransito = new SolicitudDeTransito(2L, LocalDateTime.of(2022, 6, 1, 8, 0),new Mascota(8L, Mascota.Tipo.PERRO), rescatista, transitista);
        solicitudDeTransitoBuilder.solicitudDeTransito.fechaDeRespuesta = LocalDateTime.of(2022,6,1,8,5);
        return solicitudDeTransitoBuilder;
    }

    public static SolicitudDeTransitoBuilder SolicitudDeTransitoRespuestaConMuchaDemora(){
        Transitista transitista = TransitistaBuilder.transitistaActivo().build();
        Rescatista rescatista = RescatistaBuilder.rescatista().build();
        SolicitudDeTransitoBuilder solicitudDeTransitoBuilder = new SolicitudDeTransitoBuilder();
        solicitudDeTransitoBuilder.solicitudDeTransito = new SolicitudDeTransito(2L,LocalDateTime.of(2022,6,1,8,0),new Mascota(8L, Mascota.Tipo.PERRO), rescatista, transitista);
        solicitudDeTransitoBuilder.solicitudDeTransito.fechaDeRespuesta = LocalDateTime.of(2022,6,1,9,5);
        return solicitudDeTransitoBuilder;
    }

    public static SolicitudDeTransitoBuilder SolicitudDeTransitoSinRespuesta(){
        Transitista transitista = TransitistaBuilder.transitistaActivo().build();
        Rescatista rescatista = RescatistaBuilder.rescatista().build();
        SolicitudDeTransitoBuilder solicitudDeTransitoBuilder = new SolicitudDeTransitoBuilder();
        Mascota mascota = new Mascota(8L, Mascota.Tipo.GATO);
        mascota.setPathFoto("/home/13151.png");
        mascota.setCondicionFisica(Mascota.CondicionFisica.SANO);
        solicitudDeTransitoBuilder.solicitudDeTransito = new SolicitudDeTransito(2L,LocalDateTime.of(2022,6,1,8,0), mascota, rescatista, transitista);
        return solicitudDeTransitoBuilder;
    }

    public SolicitudDeTransito build(){
        return this.solicitudDeTransito;
    }
}
