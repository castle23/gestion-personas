package com.example.gestion.personas.service;

import com.example.gestion.personas.entity.Persona;
import com.example.gestion.personas.exception.ExceptionFechaNacimientoInvalid;
import com.example.gestion.personas.exception.ExceptionPersonaNotFound;
import com.example.gestion.personas.repository.PersonaRepository;
import com.example.gestion.personas.utils.Dates;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PersonaService {

    @Autowired
    private Dates datesUtils;

    @Autowired
    private PersonaRepository personaRepository;

    public List<Persona> listarPersonas() {
        return personaRepository.findAll();
    }

    public Persona obtenerPersona(Long id) throws ExceptionPersonaNotFound {
        Optional<Persona> optionalPersona = personaRepository.findById(id);
        return optionalPersona.orElseThrow(ExceptionPersonaNotFound::new);
    }

    public Persona crearPersona(Persona persona) throws ExceptionFechaNacimientoInvalid {
        if (datesUtils.isValidFechaNacimiento(persona.getFechaNacimiento()))
            return personaRepository.save(persona);
        else throw new ExceptionFechaNacimientoInvalid();
    }

    public Persona actualizarPersona(Long id, Persona persona) throws ExceptionPersonaNotFound {
        Persona personaExistente = obtenerPersona(id);
        persona.setId(id);
        return personaRepository.save(persona);
    }

    public void eliminarPersona(Long id) throws ExceptionPersonaNotFound {
        Persona personaExistente = obtenerPersona(id);
        personaRepository.deleteById(id);
    }
}