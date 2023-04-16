package com.example.gestion.personas.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstadisticasPorPais {

    private String pais;

    @JsonIgnore
    private long cantidadPersonas;

    private float porcentaje;

    public EstadisticasPorPais(String pais, Long cantidadPersonas) {
        this.pais = pais;
        this.cantidadPersonas = cantidadPersonas;
    }
}
