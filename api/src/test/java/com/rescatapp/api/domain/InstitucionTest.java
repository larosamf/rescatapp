package com.rescatapp.api.domain;

import com.rescatapp.api.domain.exceptions.NoSeEncontroTransitistaException;
import com.rescatapp.api.domain.exceptions.UsuarioNoTieneEsaMascotaException;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class InstitucionTest {

    @Test
    public void agregarTransitistaConTransitistaActivoLoAgregaALaListaDeTransititasDeLaInstitucion() {
        Institucion institucion = new Institucion(1L, new Localizacion(-50f, -50f, "prueba"), "prueba", "1234", "test@†est.com",null);;
        Transitista transitista = TransitistaBuilder.transitistaActivo().build();

        institucion.agregarTransitista(transitista);

        assertThat(institucion.getTransitistas()).contains(transitista);
    }

    @Test
    public void aceptarConMascotaYSinTransitistaDejaALaMascotaEnLaInstitucionYLanzaExcepcion() {
        Institucion institucion = new Institucion(1L, new Localizacion(-50f, -50f, "prueba"), "prueba", "1234", "test@†est.com",null);;
        SolicitudDeTransito solicitud = SolicitudDeTransitoBuilder.SolicitudDeTransitoSinRespuesta().build();

        assertThatThrownBy(() -> institucion.aceptar(solicitud)).isInstanceOf(NoSeEncontroTransitistaException.class);
        assertThat(institucion.getMascotas()).contains(solicitud.getMascota());
    }
}