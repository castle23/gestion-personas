package com.example.gestion.personas.utils;

import com.example.gestion.personas.entity.Persona;
import com.example.gestion.personas.exception.ContactoNotFound;
import com.example.gestion.personas.exception.ExceptionFechaNacimientoInvalid;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class Dates {
    public static final int MIN_EDAD = 18;
    public void isValidFechaNacimiento(LocalDate fechaNacimiento) throws ExceptionFechaNacimientoInvalid {
        if (fechaNacimiento == null) {
            throw new ExceptionFechaNacimientoInvalid(); // no permitir valores nulos
        }

        LocalDate hoy = LocalDate.now();
        int edad = Period.between(fechaNacimiento, hoy).getYears();
        if(edad < MIN_EDAD) throw new ExceptionFechaNacimientoInvalid();
    }

    public void isValidContacto(Persona persona) throws ContactoNotFound {
        if(StringUtils.isBlank(persona.getMail()) && StringUtils.isBlank(persona.getTelefono()))
            throw new ContactoNotFound();
    }
}
