package com.nicolas.politics.controller;

import com.nicolas.politics.dao.CandidatoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static com.nicolas.politics.controller.TestHelper.mapper;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("Dado un controller de candidates")
public class CandidatoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    CandidatoRepository repoCandidatos;

    static String CANDIDATO_NOMBRE = "Julio Sosa";

    @Transactional
    @Test
    @DisplayName("podemos actualizar la información de una persona candidata")
    public void actualizarCandidato() throws Exception {
        var candidato = repoCandidatos.findByNombre(CANDIDATO_NOMBRE);
        assertEquals(0, candidato.getVotos());
        candidato.reset();
        candidato.votar();

        var responseEntity = mockMvc.perform(
                MockMvcRequestBuilders.put("/candidatos/" + candidato.id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper().writeValueAsString(candidato))
        ).andReturn().getResponse();

        assertEquals(200, responseEntity.getStatus());

        var candidatoActualizado = repoCandidatos.findByNombre(CANDIDATO_NOMBRE);
        assertEquals(1, candidatoActualizado.getVotos());

    }


}
