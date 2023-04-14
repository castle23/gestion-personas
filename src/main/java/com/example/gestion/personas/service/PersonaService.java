package com.example.gestion.personas.service;

import com.example.gestion.personas.entity.Pais;
import com.example.gestion.personas.entity.Persona;
import com.example.gestion.personas.exception.*;
import com.example.gestion.personas.repository.PaisRepository;
import com.example.gestion.personas.repository.PersonaRepository;
import com.example.gestion.personas.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Maneja la logica en cuanto a la gestion de personas
 */
@Slf4j
@Service
public class PersonaService {

    @Autowired
    private Utils utils;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private PaisRepository paisRepository;

    /**
     * Retorna la lista completa de personas
     * @return List<Persona>
     */
    public List<Persona> listarPersonas() {
        return personaRepository.findAll();
    }

    /**
     * Retorna un persona en particular por el ID de la misma
     * en caso de que no la encuentre retorna un error de no encontrada
     * @param id Identificador de la persona a buscar
     * @return Persona encontrada
     * @throws ExceptionPersonaNotFound Error en caso de que no encuentre la persona
     */
    public Persona obtenerPersona(Long id) throws ExceptionPersonaNotFound {
        return personaRepository.findById(id).orElseThrow(ExceptionPersonaNotFound::new);
    }
    /**
     * Guarda/Actualiza y valida un registro de Persona
     * @param persona Datos de la persona
     * @return Persona guardada/actulizada correctamente
     * @throws ExceptionFechaNacimientoInvalid Error al verificar la Edad minima
     * @throws ContactoNotFound Error al verificar los datos de contacto
     */
    public Persona crearPersona(Persona persona) throws ExceptionFechaNacimientoInvalid, ContactoNotFound, DatosPersonaInvalid, PaisNotFount {
        utils.isValidDatosPersona(persona);
        utils.isValidFechaNacimiento(persona.getFechaNacimiento());
        utils.isValidContacto(persona);
        Pais pais = paisRepository.findById(persona.getPais().getId()).orElseThrow(PaisNotFount::new);
        persona.setPais(pais);
        return personaRepository.save(persona);
    }

    /**
     * Actualiza loda datos de una persona segun el ID, menos los datos del padre
     * @param id Identificador de la persona a buscar
     * @param persona Datos de las persona ha actuzalizar
     * @return Persona actualizada
     * @throws ExceptionPersonaNotFound Error al no encontrar una persona
     * @throws ExceptionFechaNacimientoInvalid Error al verificar la Edad minima
     * @throws ContactoNotFound Error al verificar los datos de contacto
     */
    public Persona actualizarPersona(Long id, Persona persona) throws ExceptionPersonaNotFound, ExceptionFechaNacimientoInvalid, ContactoNotFound, DatosPersonaInvalid, PaisNotFount {
        Persona personaExistente = obtenerPersona(id);
        personaExistente.update(persona);
        return crearPersona(personaExistente);
    }

    /**
     * Elimina el registro de una persona dado su Identificador
     * @param id Identificador de la persona a buscar
     * @throws ExceptionPersonaNotFound Error al no encontrar una persona
     */
    public void eliminarPersona(Long id) throws ExceptionPersonaNotFound {
        Persona personaExistente = obtenerPersona(id);
        personaRepository.delete(personaExistente);
    }

    /**
     * Asocia un Padre a una Persona
     * @param idPadre Identificador del padre
     * @param idHijo Identificador de la persona
     * @return Persona actualizada
     * @throws ExceptionPersonaNotFound Persona No encontrada
     * @throws AncestroInvalid No se puede asociar el padre ya que es ansestro de este
     */
    public Persona relacionarPadre(Long idPadre, Long idHijo) throws ExceptionPersonaNotFound, AncestroInvalid, IdentificadorInvalido {
        if(idPadre.equals(idHijo)) throw new IdentificadorInvalido();
        Persona padre = obtenerPersona(idPadre);
        Persona persona = obtenerPersona(idHijo);
        utils.isValidPadre(padre,idHijo);
        persona.setPadre(padre);
        return personaRepository.save(persona);
    }
}