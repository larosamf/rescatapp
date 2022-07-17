package com.rescatapp.api.domain;

public final class Puntuacion {
    private final float estrellas;

    private final String comentario;

    private boolean isAdoptista = false;
    private Usuario usuarioQuePuntua;

    public Puntuacion(float estrellas, String comentario, Usuario usuarioQuePuntua) {
        this.estrellas = estrellas;
        this.comentario = comentario;
        this.usuarioQuePuntua = usuarioQuePuntua;
    }

    public Puntuacion(float estrellas, String comentario, Usuario usuarioQuePuntua, boolean isAdoptista) {
        this.estrellas = estrellas;
        this.comentario = comentario;
        this.isAdoptista = isAdoptista;
        this.usuarioQuePuntua = usuarioQuePuntua;
    }

    public float getEstrellas(){ return this.estrellas; }

    public boolean provieneDeAdoptista(){ return this.isAdoptista; }

    public Usuario getUsuarioQuePuntua(){ return  this.usuarioQuePuntua; }

}

