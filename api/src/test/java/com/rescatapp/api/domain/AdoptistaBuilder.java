package com.rescatapp.api.domain;

public class AdoptistaBuilder {
    private static Adoptista adoptista;

    private AdoptistaBuilder() {
        this.adoptista = null;
    }

    public static AdoptistaBuilder adoptista(){
        AdoptistaBuilder adoptistaBuilder = new AdoptistaBuilder();
        AdoptistaBuilder.adoptista = new Adoptista(1L, new Localizacion(-50f, -50f, "prueba"), "prueba", "1234", "test@†est.com",null);
        return adoptistaBuilder;
    }

    public Adoptista build(){
        return this.adoptista;
    }
}
