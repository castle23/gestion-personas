package com.example.gestion.personas.controller;

import com.example.gestion.personas.dto.Relacion;
import com.example.gestion.personas.entity.Persona;
import com.example.gestion.personas.exception.*;
import com.example.gestion.personas.service.PersonaService;
import com.example.gestion.personas.service.RelacionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller para exponer los recursos para gestionar la entidad Persona
 */
@Slf4j
@RestController
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;
    @Autowired
    private RelacionService relacionService;

    /**
     * Retorna la lista completa de personas
     *
     * @return List<Persona>
     */
    @GetMapping
    public ResponseEntity<List<Persona>> listarPersonas() {
        List<Persona> personas = personaService.listarPersonas();
        return new ResponseEntity<>(personas, HttpStatus.OK);
    }

    /**
     * Retorna un persona en particular por el ID de la misma
     * en caso de que no la encuentre retorna un error de no encontrada
     *
     * @param id Identificador de la persona a buscar
     * @return Persona encontrada
     * @throws ExceptionPersonaNotFound Error en caso de que no encuentre la persona
     */
    @GetMapping("/{id}")
    public Persona obtenerPersona(@PathVariable Long id) throws ExceptionPersonaNotFound {
        return personaService.obtenerPersona(id);
    }

    /**
     * Guarda una persona nueva, por lo cual se ignora el id y el padre ya que no se permiten en este metodo
     *
     * @param persona Datos de la persona a guardar
     * @return Persona guardada correctamente
     * @throws ExceptionFechaNacimientoInvalid Error al verificar la Edad minima
     * @throws ContactoNotFound                Error al verificar los datos de contacto
     */
    @PostMapping
    public Persona crearPersona(@RequestBody Persona persona) throws ExceptionFechaNacimientoInvalid, ContactoNotFound, DatosPersonaInvalid, PaisNotFount {
        persona.setId(null);
        persona.setPadre(null);
        return personaService.crearPersona(persona);
    }

    /**
     * Actualiza loda datos de una persona segun el ID, se ignora el id y el padre de obejto Persona ya que no se permiten en este metodo
     *
     * @param id      Identificador de la persona a buscar
     * @param persona Datos de las persona ha actuzalizar
     * @return Persona actualizada
     * @throws ExceptionPersonaNotFound        Error al no encontrar una persona
     * @throws ExceptionFechaNacimientoInvalid Error al verificar la Edad minima
     * @throws ContactoNotFound                Error al verificar los datos de contacto
     */
    @PutMapping("/{id}")
    public Persona actualizarPersona(@PathVariable Long id, @RequestBody Persona persona) throws ExceptionPersonaNotFound, ExceptionFechaNacimientoInvalid, ContactoNotFound, DatosPersonaInvalid, PaisNotFount {
        persona.setId(null);
        persona.setPadre(null);
        return personaService.actualizarPersona(id, persona);
    }

    /**
     * Elimina el registro de una persona dado su Identificador
     *
     * @param id Identificador de la persona a buscar
     * @throws ExceptionPersonaNotFound Error al no encontrar una persona
     */
    @DeleteMapping("/{id}")
    public void eliminarPersona(@PathVariable Long id) throws ExceptionPersonaNotFound {
        personaService.eliminarPersona(id);
    }

    /**
     * Asocia un padre a una persona
     *
     * @param idPadre Identificador del padre
     * @param idHijo  Identificador de la persona
     * @return Persona actualizada
     * @throws ExceptionPersonaNotFound Persona no encontrada
     * @throws AncestroInvalid          La persona ya es ancestro de la misma por lo que no se puede asociar
     * @throws IdentificadorInvalido    Los identificadores no pueden ser iguales
     */
    @PostMapping("/{idPadre}/padre/{idHijo}")
    public Persona relacionarPadre(@PathVariable Long idPadre, @PathVariable Long idHijo) throws ExceptionPersonaNotFound, AncestroInvalid, IdentificadorInvalido {
        return personaService.relacionarPadre(idPadre, idHijo);
    }

    /**
     * Devuelve la realcion entre dos personas
     *
     * @param id1 Identificador de la Persona 1
     * @param id2 Identificador de la Persona 2
     * @return Tipo de relacion entre dos personas
     * @throws ExceptionPersonaNotFound Persona no encontrada
     * @throws IdentificadorInvalido    Los identificadores no pueden ser iguales
     * @throws RelacionInexistente      No existe una relacion entre las dos personas
     */
    @GetMapping("/relaciones/{id1}/{id2}")
    public Relacion relacionPesonas(@PathVariable Long id1, @PathVariable Long id2) throws ExceptionPersonaNotFound, IdentificadorInvalido, RelacionInexistente {
        return relacionService.relacionPersona(id1, id2);
    }

}
