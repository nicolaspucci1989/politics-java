package com.nicolas.politics.controller;

import com.nicolas.politics.dao.ZonaRepository;
import com.nicolas.politics.domain.Zona;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.nicolas.politics.controller.TestHelper.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("Dado un controller de zonas")
public class ZonaControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ZonaRepository repoZonas;
    @Test
    @DisplayName("las zonas solo traen los datos de primer nivel")
    public void todasLasZonas() throws Exception {
        var responseEntity = mockMvc.perform(MockMvcRequestBuilders.get("/zonas")).andReturn().getResponse();
        var zonas = fromJsonToList(responseEntity.getContentAsString(), Zona.class);
        assertEquals(200, responseEntity.getStatus());
        assertEquals(2, zonas.size());
        // los zonas no traen candidatos
        assertEquals(0, zonas.get(0).getCandidatos().size());
    }
}
