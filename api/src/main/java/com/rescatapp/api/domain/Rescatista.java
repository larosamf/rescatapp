package com.rescatapp.api.domain;

import java.util.ArrayList;
import java.util.List;

public class Rescatista extends Usuario {
    private final List<Mascota> mascotasRescatadas = new ArrayList<>();

    public Rescatista(Long id, Localizacion localizacion, String nombre, String telefono, String email, ProcesadorPagos procesadorPagos) {
        super(id, localizacion, nombre, telefono, email, procesadorPagos);
    }

    public Mascota registrar(Mascota mascota) {
        mascota.setUsuarioResponsable(this);
        this.mascotasRescatadas.add(mascota);
        return mascota;
    }

    public List<Mascota> getMascotasRescatadas() {
        return mascotasRescatadas;
    }
}
