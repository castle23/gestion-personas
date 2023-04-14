package com.example.gestion.personas.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Enum con los Codigo y descripciones de los errores de negocio
 */
//TODO esto podria estar en la BD para que se cargen los MSJ dinamicos
@AllArgsConstructor
@NoArgsConstructor
public enum MensajeError {
    FECHA_NACIMIENTO_INVALID("Fecha de nacimiento es invalida"),
    PERSONA_NOT_FOUND("Persona no encontrada"),
    CONTACTO_NOT_FOUND("La Persona debe tener al menos un dato de contacto(mail/telf)"),
    INTERNAL_SERVER_ERROR("Error interno de la aplicacion"),
    DATOS_PERSONA_INVALID("El {0} es invalido");

    @Getter
    private String descripcion;

}
