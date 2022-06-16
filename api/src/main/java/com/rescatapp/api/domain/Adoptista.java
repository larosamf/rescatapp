package com.rescatapp.api.domain;

import java.util.ArrayList;
import java.util.List;

public class Adoptista extends Usuario {
    private final List<Mascota> mascotasAdoptadas = new ArrayList<>();

    private List<SolicitudDeAdopcion> solicitudes = new ArrayList<>();

    public Adoptista(Long id, Localizacion localizacion, String nombre, String telefono, String email, ProcesadorPagos procesadorPagos) {
        super(id, localizacion, nombre, telefono, email, procesadorPagos);
    }


    public void adoptar(Mascota mascota) {
        this.mascotasAdoptadas.add(mascota);
    }


    public List<Mascota> getMascotasAdoptadas(){
        return this.mascotasAdoptadas;
    }
}

