package com.example.gestion.personas.controller;

import com.example.gestion.personas.entity.Pais;
import com.example.gestion.personas.entity.Persona;
import com.example.gestion.personas.repository.PaisRepository;
import com.example.gestion.personas.repository.PersonaRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RootControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PaisRepository paisRepository;
    @BeforeEach
    public void setUp() {
        Pais pais = new Pais();
        pais.setId(1L);
        pais.setNombre("Argentina");
        // Configurar mock del repositorio
        Mockito.when(paisRepository.findAll()).thenReturn(Collections.singletonList(pais));

    }
    @Test
    public void listarPaisesTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/paises"))
                .andExpect(status().isOk())
                .andReturn();
        List<Pais> pais = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Pais>>() {
        });
        // Hacer asserts sobre la lista de personas
        assertThat(pais).isNotNull();
        assertThat(pais).hasSize(1);
    }

}