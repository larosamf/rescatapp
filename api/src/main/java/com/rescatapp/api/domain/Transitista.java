package com.rescatapp.api.domain;

public class Transitista extends Persona {
    private int capacidad;

    public Transitista(Long id, Localizacion localizacion, String nombre, String telefono, String email, int capacidad) {
        super(id, localizacion, nombre, telefono, email);
        this.capacidad = capacidad;
    }

    public Mascota aceptar(Mascota mascota){
        return mascota;
    }

    public int getCapacidad() {
        return capacidad;
    }
}
