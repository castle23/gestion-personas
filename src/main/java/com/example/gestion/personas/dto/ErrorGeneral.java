package com.example.gestion.personas.dto;

import com.example.gestion.personas.controller.MensajeError;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase para responder los errores de negocio
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorGeneral {
    private String codigo;
    private String descripcion;

    public ErrorGeneral(MensajeError mensajeError) {
        this.codigo = mensajeError.name();
        this.descripcion = mensajeError.getDescripcion();
    }
}
