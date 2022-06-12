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

    private final Long id;
    private Long idUsuarioResponsable;
    private String nombre;
    private Tamano tamano;
    private int edad;
    private List<Vacuna> vacunas;
    private String descripcion;
    private final Tipo tipo;
    private int estado;

    public Mascota(Long id, Long idUsuarioResponsable, String nombre, Tamano tamano, int edad, List<Vacuna> vacunas, String descripcion, Tipo tipo, int estado) {
        this.id = id;
        this.idUsuarioResponsable = idUsuarioResponsable;
        this.nombre = nombre;
        this.tamano = tamano;
        this.edad = edad;
        this.vacunas = vacunas;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.estado = estado;
    }

    public Mascota(Long id, Tipo tipo) {
        this.id = id;
        this.tipo = tipo;
        this.estado = 1;
    }

    public Long getId() {
        return id;
    }

    public Long getIdUsuarioResponsable() {
        return idUsuarioResponsable;
    }

    public void setIdUsuarioResponsable(Long idUsuarioResponsable) {
        this.idUsuarioResponsable = idUsuarioResponsable;
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
        if (this.estado == 3) {
            return true;
        }
        return false;
    }

    public boolean pasarAAdopcion() {
        if (this.estado != 4) {
            this.estado = 3;
            return true;
        }
        return false;
    }
}
