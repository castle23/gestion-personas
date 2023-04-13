package com.example.gestion.personas.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
