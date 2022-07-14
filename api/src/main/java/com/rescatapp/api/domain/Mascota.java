package com.rescatapp.api.domain;

import java.util.List;
import java.util.Objects;

public class Mascota {

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

    public enum Estado {
        RESCATADO,
        EN_TRANSITO,
        EN_ADOPCION,
        ADOPTADO
    }

    public enum CondicionFisica {
        SANO,
        DEBIL,
        LASTIMADO,
        MUY_LASTIMADO
    }

    private final Long id;
    private Usuario usuarioResponsable;
    private String nombre;
    private Tamano tamano;
    private int edad;
    private List<Vacuna> vacunas;
    private String descripcion;
    private final Tipo tipo;
    private Estado estado;

    private String pathFoto;

    private CondicionFisica condicionFisica;

    public Mascota(Long id, Usuario usuarioResponsable, String nombre, Tamano tamano, int edad, List<Vacuna> vacunas, String descripcion, Tipo tipo, Estado estado) {
        this.id = id;
        this.usuarioResponsable = usuarioResponsable;
        this.nombre = nombre;
        this.tamano = tamano;
        this.edad = edad;
        this.vacunas = vacunas;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.estado = estado;
        this.condicionFisica = null;
        this.pathFoto = null;
    }

    public Mascota(Long id, Tipo tipo) {
        this.id = id;
        this.tipo = tipo;
        this.estado = Estado.RESCATADO;
        this.condicionFisica = null;
        this.pathFoto = null;
    }

    public Long getId() {
        return id;
    }

    public Usuario getUsuarioResponsable() {
        return usuarioResponsable;
    }

    public void setUsuarioResponsable(Usuario usuarioResponsable) {
        this.usuarioResponsable = usuarioResponsable;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Tamano getTamano() {
        return tamano;
    }

    public void setTamano(Tamano tamano) {
        this.tamano = tamano;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public List<Vacuna> getVacunas() {
        return vacunas;
    }

    public void setVacunas(List<Vacuna> vacunas) {
        this.vacunas = vacunas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CondicionFisica getCondicionFisica() {
        return condicionFisica;
    }

    public void setCondicionFisica(CondicionFisica condicionFisica) {
        this.condicionFisica = condicionFisica;
    }

    public String getPathFoto() {
        return pathFoto;
    }

    public void setPathFoto(String pathFoto) {
        this.pathFoto = pathFoto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mascota mascota = (Mascota) o;
        return id.equals(mascota.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public boolean estaEnAdopcion() {
        if (this.estado == Estado.EN_ADOPCION) {
            return true;
        }
        return false;
    }

    public boolean pasarAAdopcion() {
        if (this.estado != Estado.ADOPTADO) {
            this.estado = Estado.EN_ADOPCION;
            return true;
        }
        return false;
    }

    public boolean completoResgitro() {
        return ((this.condicionFisica != null) && (this.pathFoto != null));
    }
}
