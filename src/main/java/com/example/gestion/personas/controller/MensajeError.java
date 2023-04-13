package com.example.gestion.personas.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum MensajeError {
    FECHA_NACIMIENTO_INVALID("Fecha de nacimiento es invalida"),
    PERSONA_NOT_FOUND("Persona no encontrada");
    @Getter
    private String descripcion;

}
