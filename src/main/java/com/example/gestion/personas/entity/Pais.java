package com.example.gestion.personas.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Entidad Pais
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pais", indexes = {
        @Index(name = "idx_pais_id", columnList = "id", unique = true)
})
public class Pais {

    /**
     * Identificador del pais
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pais")
    @SequenceGenerator(name="seq_pais", sequenceName = "seq_pais", allocationSize = 1)
    private Long id;

    /**
     * Nombre del pais
     */
    @Column(nullable = false)
    private String nombre;

    /**
     * Lista de personas del pais
     */
    @JsonIgnore
    @OneToMany(mappedBy = "pais")
    private List<Persona> personas;
}
