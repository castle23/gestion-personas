package com.example.gestion.personas.utils;

import com.example.gestion.personas.entity.Persona;
import com.example.gestion.personas.exception.ContactoNotFound;
import com.example.gestion.personas.exception.DatosPersonaInvalid;
import com.example.gestion.personas.exception.ExceptionFechaNacimientoInvalid;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Slf4j
@Service
public class Utils {

    //TODO esto bien puede estar en la BD en una tabla config para que se maneje de forma dinamica
    /**
     * Minino de edad permitido para guardar en el sistema
     */
    @Value("${minimo.edad}")
    private int minimoEdad;

    /**
     * Valida la edad minima admitida segun la fecha de nacimiento
     * @param fechaNacimiento fecha a validar
     * @throws ExceptionFechaNacimientoInvalid error de edad minima
     */
    public void isValidFechaNacimiento(LocalDate fechaNacimiento) throws ExceptionFechaNacimientoInvalid {
        if (fechaNacimiento == null) {
            throw new ExceptionFechaNacimientoInvalid(); // no permitir valores nulos
        }
        LocalDate hoy = LocalDate.now();
        int edad = Period.between(fechaNacimiento, hoy).getYears();
        if(edad < minimoEdad) throw new ExceptionFechaNacimientoInvalid();
    }

    /**
     * Valida los datos de contacto de una persona
     * @param persona datos de las persona
     * @throws ContactoNotFound error de contacto no encontrado
     */
    public void isValidContacto(Persona persona) throws ContactoNotFound {
        if(StringUtils.isBlank(persona.getMail()) && StringUtils.isBlank(persona.getTelefono()))
            throw new ContactoNotFound();
    }

    // TODO esto se podria realizar un anotacion y una clase que para que valide
    //  toda una clase cualquiera tanto nulos como requeridos,logitud,minimos,maximos,
    //  regex,etc y devuelva una exception con el nombre del campo y el tipo de error
    /**
     * Valida los datos de una persona
     * @param persona datos de las persona
     * @throws DatosPersonaInvalid error de datos de persona
     */
    public void isValidDatosPersona(Persona persona) throws DatosPersonaInvalid {
        if (StringUtils.isBlank(persona.getNombre())) {
            throw new DatosPersonaInvalid("Nombre");
        } else if (StringUtils.isBlank(persona.getApellido())) {
            throw new DatosPersonaInvalid("Apellido");
        } else if (StringUtils.isBlank(persona.getTipoDocumento())) {
            throw new DatosPersonaInvalid("Tipo Documento");
        } else if (StringUtils.isBlank(persona.getNumeroDocumento())) {
            throw new DatosPersonaInvalid("Numero Documento");
        } else if (persona.getPais() == null || persona.getPais().getId() == null) {
            throw new DatosPersonaInvalid("Pais");
        }
    }
}
