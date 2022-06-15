package com.rescatapp.api.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Adoptista extends Usuario {
    private final List<Mascota> mascotasAdoptadas = new ArrayList<>();
    private List<SolicitudDeAdopcion> solicitudes = new ArrayList<>();

    public Adoptista(Long id, Localizacion localizacion, String nombre, String telefono, String email, int capacidad, ProcesadorPagos procesadorPagos) {
        super(id, localizacion, nombre, telefono, email, procesadorPagos);
    }


    public boolean agregar(SolicitudDeTransito solicitud) {
        return true;
    }


}

