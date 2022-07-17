package com.rescatapp.api.domain;

import com.rescatapp.api.domain.exceptions.ValorComisionIncorrectoException;
import com.rescatapp.api.domain.exceptions.UsuarioQuePuntuaYaRealizoUnaPuntuacionAntesException;

import java.util.ArrayList;
import java.util.List;

public abstract class Usuario {
    private final Long id;
    private final double max_km_para_considerar_cercanos = 1;
    private Localizacion localizacion;
    private String nombre;
    private String telefono;
    private String email;
    private String cbu;
    private final List<Donacion> donacionesRealizadas = new ArrayList<>();
    protected final ProcesadorPagos procesadorPagos;
    final List<Puntuacion> puntuacionesRecibidas = new ArrayList<>();
    final List<Usuario> usuariosQuePuntuaron = new ArrayList<>();
    private boolean reportado;

    public Usuario(Long id, Localizacion localizacion, String nombre, String telefono, String email, ProcesadorPagos procesadorPagos) {
        this.id = id;
        this.localizacion = localizacion;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.procesadorPagos = procesadorPagos;
        this.reportado = false;
    }

    public void donar(Donacion donacion) {
        procesadorPagos.cobrar(donacion.getMontoACobrar(), this.cbu);
        this.donacionesRealizadas.add(donacion);
    }

    public void agregarPuntuacion(Puntuacion puntuacion) throws UsuarioQuePuntuaYaRealizoUnaPuntuacionAntesException {
        this.puntuacionesRecibidas.add(puntuacion);
        Usuario usuarioQuePuntua = puntuacion.getUsuarioQuePuntua();
        if (usuariosQuePuntuaron.contains(usuarioQuePuntua))
            throw new UsuarioQuePuntuaYaRealizoUnaPuntuacionAntesException("El usuario que quiere puntuar ya habia hecho una puntuacion antes." );
        this.usuariosQuePuntuaron.add(puntuacion.getUsuarioQuePuntua());
    }

    public float calcularPuntuacionTotal() {
        if (this.puntuacionesRecibidas.size() == 0)
            return 0;
        float total = this.puntuacionesRecibidas.stream().map(Puntuacion::getEstrellas).reduce(0f, Float::sum);
        return total / this.puntuacionesRecibidas.size();
    }

    public boolean estaCercaDe(Localizacion localizacion) {
        return localizacion.calcularDistanciaEnKilometros(this.localizacion) < this.max_km_para_considerar_cercanos;
    }

    public Long getId() {
        return id;
    }

    public Localizacion getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(Localizacion localizacion) {
        this.localizacion = localizacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    public List<Donacion> getDonacionesRealizadas() {
        return donacionesRealizadas;
    }

    public void reportar() {
        this.reportado = true;
    }

    public boolean fueResportado() {
        return this.reportado;
    }
}
