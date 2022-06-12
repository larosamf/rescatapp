package com.rescatapp.api.domain;

public class Localizacion {
    private Float latitud;
    private Float longitud;

    private String direccion;

    public Localizacion(Float latitud, Float longitud, String direccion) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.direccion = direccion;
    }

    public Float calcularDistanciaEnMetros(Localizacion otraLocalizacion) {
        return 0F;
    }

    public Float getLatitud() {
        return latitud;
    }

    public void setLatitud(Float latitud) {
        this.latitud = latitud;
    }

    public Float getLongitud() {
        return longitud;
    }

    public void setLongitud(Float longitud) {
        this.longitud = longitud;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
