package com.rescatapp.api.domain;

import java.util.ArrayList;
import java.util.List;

public class Adoptista extends Usuario {

    public enum Tamano {
        GRANDE,
        MEDIANO,
        PEQUENO
    }

    public enum Tipo {
        PERRO,
        GATO,
        OTRO
    }

    public enum CondicionFisica {
        SANO,
        DEBIL,
        LASTIMADO,
        MUY_LASTIMADO
    }
    private final List<Mascota> mascotasAdoptadas = new ArrayList<>();

    private List<SolicitudDeAdopcion> solicitudes = new ArrayList<>();
    private boolean preferencias;
    private Tamano tamanoMascota;
    private Tipo tipoMascota;
    private CondicionFisica condicionFisicaMascota;

    public Adoptista(Long id, Localizacion localizacion, String nombre, String telefono, String email, ProcesadorPagos procesadorPagos) {
        super(id, localizacion, nombre, telefono, email, procesadorPagos);
        this.preferencias = false;
    }


    public void adoptar(Mascota mascota) {
        this.mascotasAdoptadas.add(mascota);
    }


    public List<Mascota> getMascotasAdoptadas(){
        return this.mascotasAdoptadas;
    }
    public void setPreferencias(Tamano tamano, Tipo tipo, CondicionFisica condicionFisica){
        this.preferencias = true;
        this.tamanoMascota = tamano;
        this.tipoMascota = tipo;
        this.condicionFisicaMascota= condicionFisica;
    }
    public boolean getPreferencias(){ return this.preferencias; }
    public Adoptista.Tamano getTamanoMascota() {
        return tamanoMascota;
    }
    public Adoptista.Tipo getTipoMascota() {
        return tipoMascota;
    }
    public Adoptista.CondicionFisica getCondicionFisicaMascota() {
        return condicionFisicaMascota;
    }
}

