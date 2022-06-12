package com.rescatapp.api.domain;

import java.util.ArrayList;
import java.util.List;

public class Rescatista extends Persona {
    private final List<Mascota> mascotasRescatadas = new ArrayList<>();

    public Rescatista(Long id, Localizacion localizacion, String nombre, String telefono, String email) {
        super(id, localizacion, nombre, telefono, email);
    }

    public Mascota registrar(Mascota mascota) {
        mascota.setIdUsuarioResponsable(this.getId());
        this.mascotasRescatadas.add(mascota);
        return mascota;
    }

    public List<Mascota> getMascotasRescatadas() {
        return mascotasRescatadas;
    }
}
