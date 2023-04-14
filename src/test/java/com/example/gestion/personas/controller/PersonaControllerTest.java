package com.example.gestion.personas.controller;

import com.example.gestion.personas.dto.ErrorGeneral;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PersonaRepository personaRepository;
    @MockBean
    private PaisRepository paisRepository;

    @BeforeEach
    public void setUp() {
        Persona  persona = persona();
        // Configurar mock del repositorio
        Mockito.when(personaRepository.findAll()).thenReturn(Collections.singletonList(persona));
        Mockito.when(personaRepository.findById(1L)).thenReturn(Optional.of(persona));
        Mockito.when(paisRepository.findById(persona.getPais().getId())).thenReturn(Optional.of(persona.getPais()));
        Mockito.when(personaRepository.save(any())).thenReturn(persona);
        Mockito.doNothing().when(personaRepository).delete(any());

    }

    @Test
    public void listarPersonas() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/personas"))
                .andExpect(status().isOk())
                .andReturn();
        List<Persona> personas = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Persona>>() {
        });
        // Hacer asserts sobre la lista de personas
        assertThat(personas).isNotNull();
        assertThat(personas).hasSize(1);

        Persona persona = personas.get(0);
        assertThat(persona.getId()).isEqualTo(1L);
        assertThat(persona.getNombre()).isEqualTo("Carlos");
        assertThat(persona.getApellido()).isEqualTo("Castillo");
        assertThat(persona.getTipoDocumento()).isEqualTo("DNI");
        assertThat(persona.getNumeroDocumento()).isEqualTo("123456789");
        assertThat(persona.getTelefono()).isEqualTo("1234567890");
        assertThat(persona.getMail()).isEqualTo("carlos.castillo@example.com");
        assertThat(persona.getFechaNacimiento()).isEqualTo(LocalDate.of(1991, 1, 22));
        assertThat(persona.getPais().getId()).isEqualTo(1L);
        assertThat(persona.getPais().getNombre()).isEqualTo("Argentina");
    }

    @Test
    void obtenerPersona() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/personas/1"))
                .andExpect(status().isOk())
                .andReturn();
        Persona persona = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Persona.class);
        // Hacer asserts sobre la persona
        assertThat(persona.getId()).isEqualTo(1L);
        assertThat(persona.getNombre()).isEqualTo("Carlos");
        assertThat(persona.getApellido()).isEqualTo("Castillo");
        assertThat(persona.getTipoDocumento()).isEqualTo("DNI");
        assertThat(persona.getNumeroDocumento()).isEqualTo("123456789");
        assertThat(persona.getTelefono()).isEqualTo("1234567890");
        assertThat(persona.getMail()).isEqualTo("carlos.castillo@example.com");
        assertThat(persona.getFechaNacimiento()).isEqualTo(LocalDate.of(1991, 1, 22));
        assertThat(persona.getPais().getId()).isEqualTo(1L);
        assertThat(persona.getPais().getNombre()).isEqualTo("Argentina");
    }

    @Test
    void obtenerPersonaError() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/personas/2"))
                .andExpect(status().isNotFound())
                .andReturn();
        ErrorGeneral response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ErrorGeneral.class);
        //Hacer asserts sobre response
        assertThat(response.getCodigo()).isEqualTo(MensajeError.PERSONA_NOT_FOUND.name());
        assertThat(response.getDescripcion()).isEqualTo(MensajeError.PERSONA_NOT_FOUND.getDescripcion());
    }

    @Test
    void crearPersona() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/personas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(persona())))
                .andExpect(status().isOk())
                .andReturn();
        Persona persona = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Persona.class);
        // Hacer asserts sobre la persona
        assertThat(persona.getId()).isEqualTo(1L);
        assertThat(persona.getNombre()).isEqualTo("Carlos");
        assertThat(persona.getApellido()).isEqualTo("Castillo");
        assertThat(persona.getTipoDocumento()).isEqualTo("DNI");
        assertThat(persona.getNumeroDocumento()).isEqualTo("123456789");
        assertThat(persona.getTelefono()).isEqualTo("1234567890");
        assertThat(persona.getMail()).isEqualTo("carlos.castillo@example.com");
        assertThat(persona.getFechaNacimiento()).isEqualTo(LocalDate.of(1991, 1, 22));
        assertThat(persona.getPais().getId()).isEqualTo(1L);
        assertThat(persona.getPais().getNombre()).isEqualTo("Argentina");
    }

    @Test
    void crearPersonaInvalidFechaNacimiento() throws Exception {
        Persona persona = persona();
        persona.setFechaNacimiento(LocalDate.of(2022, 1, 1));
        MvcResult mvcResult = mockMvc.perform(post("/personas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(persona)))
                .andExpect(status().isBadRequest())
                .andReturn();
        ErrorGeneral response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ErrorGeneral.class);
        //Hacer asserts sobre response
        assertThat(response.getCodigo()).isEqualTo(MensajeError.FECHA_NACIMIENTO_INVALID.name());
        assertThat(response.getDescripcion()).isEqualTo(MensajeError.FECHA_NACIMIENTO_INVALID.getDescripcion());
    }
    @Test
    void crearPersonaInvalidContacto() throws Exception {
        Persona persona = persona();
        persona.setTelefono(null);
        persona.setMail(null);
        MvcResult mvcResult = mockMvc.perform(post("/personas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(persona)))
                .andExpect(status().isBadRequest())
                .andReturn();
        ErrorGeneral response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ErrorGeneral.class);
        //Hacer asserts sobre response
        assertThat(response.getCodigo()).isEqualTo(MensajeError.CONTACTO_NOT_FOUND.name());
        assertThat(response.getDescripcion()).isEqualTo(MensajeError.CONTACTO_NOT_FOUND.getDescripcion());
    }
    @Test
    void crearPersonaInvalidDatos() throws Exception {
        Persona persona = persona();
        persona.setPais(null);
        MvcResult mvcResult = mockMvc.perform(post("/personas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(persona)))
                .andExpect(status().isBadRequest())
                .andReturn();
        ErrorGeneral response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ErrorGeneral.class);
        //Hacer asserts sobre response
        assertThat(response.getCodigo()).isEqualTo(MensajeError.DATOS_PERSONA_INVALID.name());
        assertThat(response.getDescripcion()).isEqualTo(MessageFormat.format(MensajeError.DATOS_PERSONA_INVALID.getDescripcion(), "Pais"));
    }

    @Test
    void actualizarPersona() throws Exception {
        MvcResult mvcResult = mockMvc.perform(put("/personas/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(persona())))
                .andExpect(status().isOk())
                .andReturn();

        Persona persona = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Persona.class);

        assertThat(persona.getId()).isEqualTo(1L);
        assertThat(persona.getNombre()).isEqualTo("Carlos");
        assertThat(persona.getApellido()).isEqualTo("Castillo");
        assertThat(persona.getTipoDocumento()).isEqualTo("DNI");
        assertThat(persona.getNumeroDocumento()).isEqualTo("123456789");
        assertThat(persona.getTelefono()).isEqualTo("1234567890");
        assertThat(persona.getMail()).isEqualTo("carlos.castillo@example.com");
        assertThat(persona.getFechaNacimiento()).isEqualTo(LocalDate.of(1991, 1, 22));
        assertThat(persona.getPais().getId()).isEqualTo(1L);
        assertThat(persona.getPais().getNombre()).isEqualTo("Argentina");
    }

    @Test
    void eliminarPersona() throws Exception {
        // Realizar la petición DELETE a la URL /personas/1
        mockMvc.perform(delete("/personas/{id}", 1L))
                .andExpect(status().isOk());

        // Verificar que el método deleteById del repositorio mockeado fue invocado una vez
        verify(personaRepository, times(1)).delete(persona());
    }

    public Persona persona() {
        Pais pais = new Pais();
        pais.setId(1L);
        pais.setNombre("Argentina");

        Persona persona = new Persona();
        persona.setId(1L);
        persona.setNombre("Carlos");
        persona.setApellido("Castillo");
        persona.setTipoDocumento("DNI");
        persona.setNumeroDocumento("123456789");
        persona.setTelefono("1234567890");
        persona.setMail("carlos.castillo@example.com");
        persona.setFechaNacimiento(LocalDate.of(1991, 1, 22));
        persona.setPais(pais);
        return persona;
    }
}