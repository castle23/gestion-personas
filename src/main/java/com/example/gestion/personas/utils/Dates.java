package com.example.gestion.personas.utils;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class Dates {
    public static final int MIN_FECHA_NACIMIENTO = 18;
    public boolean isValidFechaNacimiento(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) {
            return false; // no permitir valores nulos
        }

        LocalDate hoy = LocalDate.now();
        int edad = Period.between(fechaNacimiento, hoy).getYears();
        return edad >= MIN_FECHA_NACIMIENTO;
    }
}
