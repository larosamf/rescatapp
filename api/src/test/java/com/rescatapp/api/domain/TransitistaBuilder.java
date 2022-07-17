package com.rescatapp.api.domain;

import com.rescatapp.api.domain.exceptions.UsuarioQuePuntuaYaRealizoUnaPuntuacionAntesException;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class TransitistaBuilder {
    private Transitista transitista;

    private TransitistaBuilder() {
        this.transitista = null;
    }

    public static TransitistaBuilder transitistaActivo(){
        TransitistaBuilder transitistaBuilder = new TransitistaBuilder();
        transitistaBuilder.transitista = new Transitista(1L, new Localizacion(-50f, -50f, "prueba"), "prueba", "1234", "test@†est.com",3,null);
        transitistaBuilder.transitista.mostrarComoActivo();
        return transitistaBuilder;
    }

    public static TransitistaBuilder transitistaConSolicitudesDeTransicion(List<SolicitudDeTransito> solicitudes){
        TransitistaBuilder transitistaBuilder = new TransitistaBuilder();
        transitistaBuilder.transitista = new Transitista(1L, new Localizacion(-50f, -50f, "prueba"), "prueba", "1234", "test@†est.com",3,null);
        transitistaBuilder.transitista.solicitudesDeTransito = solicitudes;
        return transitistaBuilder;
    }

    public static TransitistaBuilder transitistaActivoConMascotas(){
        TransitistaBuilder transitistaBuilder = new TransitistaBuilder();
        transitistaBuilder.transitista = new Transitista(1L, new Localizacion(-50f, -50f, "prueba"), "prueba", "1234", "test@†est.com",3,null);
        List<Mascota> mascotas = new ArrayList<>(asList(new Mascota(8L, Mascota.Tipo.PERRO), new Mascota(9L, Mascota.Tipo.PERRO), new Mascota(10L, Mascota.Tipo.PERRO)));
        transitistaBuilder.transitista.mascotasTransitadoActualmente = mascotas;
        transitistaBuilder.transitista.mostrarComoActivo();
        return transitistaBuilder;
    }

    public static TransitistaBuilder transitistaPuntuado3Veces() throws UsuarioQuePuntuaYaRealizoUnaPuntuacionAntesException {
        TransitistaBuilder transitistaBuilder = new TransitistaBuilder();
        transitistaBuilder.transitista = new Transitista(1L, new Localizacion(-50f, -50f, "prueba"), "prueba", "1234", "test@†est.com",3,null);
        Usuario usuario_2 = new UsuarioTest.DummyUsuario(2L, new Localizacion(-50f, -50f, "prueba"), "prueba2", "5678", "test@†est.com",  null);
        Usuario usuario_3 = new UsuarioTest.DummyUsuario(2L, new Localizacion(-50f, -50f, "prueba"), "prueba3", "91011", "test@†est.com",  null);
        Usuario usuario_4 = new UsuarioTest.DummyUsuario(2L, new Localizacion(-50f, -50f, "prueba"), "prueba4", "121314", "test@†est.com",  null);
        Puntuacion puntuacion_1 = new Puntuacion(2.5F, "Buena persona.",usuario_2);
        transitistaBuilder.transitista.agregarPuntuacion(puntuacion_1);
        Puntuacion puntuacion_2 = new Puntuacion(5F, "Buena persona.",usuario_3);
        transitistaBuilder.transitista.agregarPuntuacion(puntuacion_2);
        Puntuacion puntuacion_3 = new Puntuacion(1.5F, "Buena persona.",usuario_4);
        transitistaBuilder.transitista.agregarPuntuacion(puntuacion_3);
        return transitistaBuilder;
    }

    public static TransitistaBuilder transitistaPuntuado16Veces() throws UsuarioQuePuntuaYaRealizoUnaPuntuacionAntesException {
        TransitistaBuilder transitistaBuilder = new TransitistaBuilder();
        transitistaBuilder.transitista = new Transitista(1L, new Localizacion(-50f, -50f, "prueba"), "prueba", "1234", "test@†est.com",3,null);
        Usuario usuario;
        Puntuacion puntuacion;
        for (int i = 0; i < 16; i++ ){
            usuario = new UsuarioTest.DummyUsuario(2L, new Localizacion(-50f, -50f, "prueba"), "prueba2", "5678", "test@†est.com",  null);
            puntuacion = new Puntuacion(2.5F, "Buena persona.",usuario);
            transitistaBuilder.transitista.agregarPuntuacion(puntuacion);
        }
        return transitistaBuilder;
    }

    public static TransitistaBuilder transitistaPuntuado3Veces1PorAdoptista() throws UsuarioQuePuntuaYaRealizoUnaPuntuacionAntesException {
        TransitistaBuilder transitistaBuilder = new TransitistaBuilder();
        transitistaBuilder.transitista = new Transitista(1L, new Localizacion(-50f, -50f, "prueba"), "prueba", "1234", "test@†est.com",3,null);
        Usuario usuario_2 = new UsuarioTest.DummyUsuario(2L, new Localizacion(-50f, -50f, "prueba"), "prueba2", "5678", "test@†est.com",  null);
        Usuario usuario_3 = new UsuarioTest.DummyUsuario(2L, new Localizacion(-50f, -50f, "prueba"), "prueba3", "91011", "test@†est.com",  null);
        Usuario usuario_4 = new UsuarioTest.DummyUsuario(2L, new Localizacion(-50f, -50f, "prueba"), "prueba4", "121314", "test@†est.com",  null);
        Puntuacion puntuacion_1 = new Puntuacion(2.5F, "Buena persona.",usuario_2);
        transitistaBuilder.transitista.agregarPuntuacion(puntuacion_1);
        Puntuacion puntuacion_2 = new Puntuacion(5F, "Buena persona.",usuario_3,true);
        transitistaBuilder.transitista.agregarPuntuacion(puntuacion_2);
        Puntuacion puntuacion_3 = new Puntuacion(1.5F, "Buena persona.",usuario_4);
        transitistaBuilder.transitista.agregarPuntuacion(puntuacion_3);
        return transitistaBuilder;
    }

    public Transitista build(){
        return this.transitista;
    }
}
