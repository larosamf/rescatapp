package com.rescatapp.api.domain;

public class RescatistaBuilder {
    private static Rescatista rescatista;

    private RescatistaBuilder() {
        this.rescatista = null;
    }

    public static RescatistaBuilder rescatista(){
        RescatistaBuilder rescatistaBuilder = new RescatistaBuilder();
        RescatistaBuilder.rescatista = new Rescatista(1L, new Localizacion(-50f, -50f, "prueba"), "prueba", "1234", "test@†est.com",null);
        return rescatistaBuilder;
    }

    public Rescatista build(){
        return this.rescatista;
    }
}
