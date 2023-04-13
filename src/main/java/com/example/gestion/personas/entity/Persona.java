package com.example.gestion.personas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "persona", indexes = {
        @Index(name = "idx_persona_id", columnList = "id", unique = true),
        @Index(name = "idx_persona_doc", columnList = "id_pais, tipo_documento, numero_documento", unique = true)
})
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_persona")
    @SequenceGenerator(name="seq_persona", sequenceName = "seq_persona", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_padre")
    private Persona padre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pais", referencedColumnName = "id")
    private Pais pais;

    @Column(name = "tipo_documento", nullable = false)
    private String tipoDocumento;

    @Column(name = "numero_documento", nullable = false)
    private String numeroDocumento;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "mail")
    private String mail;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;
}