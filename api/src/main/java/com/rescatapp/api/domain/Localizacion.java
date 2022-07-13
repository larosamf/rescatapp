package com.rescatapp.api.domain;

public final class Localizacion {
    private final Float latitud;
    private final Float longitud;

    private final String direccion;

    public Localizacion(Float latitud, Float longitud, String direccion) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.direccion = direccion;
    }

    public double calcularDistanciaEnKilometros(Localizacion otraLocalizacion) {
        double radioTierra = 6371;//en kilómetros

        double dLat = Math.toRadians(otraLocalizacion.getLatitud() - this.latitud);
        double dLng = Math.toRadians(otraLocalizacion.getLongitud() - this.longitud);
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(this.latitud)) * Math.cos(Math.toRadians(otraLocalizacion.getLatitud()));
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));
        double distancia = radioTierra * va2;

        return distancia;
    }

    public Float getLatitud() {
        return latitud;
    }


    public Float getLongitud() {
        return longitud;
    }


    public String getDireccion() {
        return direccion;
    }

}
