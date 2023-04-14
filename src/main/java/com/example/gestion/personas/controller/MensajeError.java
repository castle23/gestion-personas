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
    INTERNAL_SERVER_ERROR("Error interno de la aplicacion"),
    FECHA_NACIMIENTO_INVALID("Fecha de nacimiento es invalida"),
    PERSONA_NOT_FOUND("Persona no encontrada"),
    CONTACTO_NOT_FOUND("La Persona debe tener al menos un dato de contacto(mail/telf)"),
    DATOS_PERSONA_INVALID("El {0} es invalido"),
    ANCESTRO_INVALID("No se puede asociar el padre ya que esta como ancestro de este"),
    ID_INVALID("Los identificadores no pueden ser iguales");

    @Getter
    private String descripcion;

}
