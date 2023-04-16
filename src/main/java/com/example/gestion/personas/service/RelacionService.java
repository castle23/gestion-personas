package com.example.gestion.personas.service;

import com.example.gestion.personas.dto.Relacion;
import com.example.gestion.personas.entity.Persona;
import com.example.gestion.personas.exception.ExceptionPersonaNotFound;
import com.example.gestion.personas.exception.IdentificadorInvalido;
import com.example.gestion.personas.exception.RelacionInexistente;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Maneja la logica en cuanto a la relacion entre personas
 */
@Slf4j
@Service
public class RelacionService {

    @Autowired
    private PersonaService personaService;

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
    public Relacion relacionPersona(Long id1, Long id2) throws ExceptionPersonaNotFound, IdentificadorInvalido, RelacionInexistente {
        if (id1.equals(id2)) throw new IdentificadorInvalido();
        Persona persona1 = personaService.obtenerPersona(id1);
        Persona persona2 = personaService.obtenerPersona(id2);
        if (this.esHermano(persona1, persona2)) {
            return Relacion.HERMANO;
        } else if (this.esTio(persona1, persona2)) {
            return Relacion.TIO;
        } else if (this.esPrimo(persona1, persona2)) {
            return Relacion.PRIMO;
        } else {
            throw new RelacionInexistente();
        }
    }

    /**
     * Verifica si son hermanos
     * @param persona1
     * @param persona2
     * @return
     */
    public boolean esHermano(Persona persona1, Persona persona2) {
        // Si ambas personas tienen el mismo padre entonces son hermanos
        return persona1.getPadre() != null && persona2.getPadre() != null && persona1.getPadre().getId().equals(persona2.getPadre().getId());
    }

    /**
     * Verifica si la persona 2 es tio de la persona 1 o viceversa
     * @param persona1
     * @param persona2
     * @return
     */
    public boolean esTio(Persona persona1, Persona persona2) {
        // Si persona2 es hermano del padre de persona1
        if (persona1.getPadre() != null && esHermano(persona1.getPadre(), persona2)) {
            return true;
        }
        // Si persona1 es hermano del padre de persona2
        if (persona2.getPadre() != null && esHermano(persona2.getPadre(), persona1)) {
            return true;
        }
        // Si no se cumple no es tío/a
        return false;
    }

    /**
     * verifica si son primos
     * @param persona1
     * @param persona2
     * @return
     */
    public boolean esPrimo(Persona persona1, Persona persona2) {
        // Si ambas personas tienen padre y son hermanos entonces son primos
        return persona1.getPadre() != null && persona2.getPadre() != null && esHermano(persona1.getPadre(), persona2.getPadre());
    }

    //TODO se puede seguir agregando relaciones, y mas complejidad al agregar la mandre
}
