package com.example.gestion.personas.controller;

import com.example.gestion.personas.entity.Persona;
import com.example.gestion.personas.exception.ExceptionFechaNacimientoInvalid;
import com.example.gestion.personas.exception.ExceptionPersonaNotFound;
import com.example.gestion.personas.service.PersonaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping
    public ResponseEntity<List<Persona>> listarPersonas() {
        List<Persona> personas = personaService.listarPersonas();
        return new ResponseEntity<>(personas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Persona obtenerPersona(@PathVariable Long id) throws ExceptionPersonaNotFound {
        return personaService.obtenerPersona(id);
    }

    @PostMapping
    public Persona crearPersona(@RequestBody Persona persona) throws ExceptionFechaNacimientoInvalid {
        return personaService.crearPersona(persona);
    }

    @PutMapping("/{id}")
    public Persona actualizarPersona(@PathVariable Long id, @RequestBody Persona persona) throws ExceptionPersonaNotFound {
        return personaService.actualizarPersona(id, persona);
    }

    @DeleteMapping("/{id}")
    public void eliminarPersona(@PathVariable Long id) throws ExceptionPersonaNotFound {
        personaService.eliminarPersona(id);
    }
}
