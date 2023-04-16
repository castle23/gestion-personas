package com.example.gestion.personas.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;

/**
 * Entidad Persona
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "persona", indexes = {
        @Index(name = "idx_persona_id", columnList = "id", unique = true),
        @Index(name = "idx_persona_doc", columnList = "id_pais, tipo_documento, numero_documento", unique = true)
})
public class Persona {

    /**
     * Identificador de la persona
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_persona")
    @SequenceGenerator(name="seq_persona", sequenceName = "seq_persona", allocationSize = 1)
    private Long id;

    /**
     * Nombre de la persona
     */
    @Column(nullable = false)
    private String nombre;

    /**
     * Apellido de las persona
     */
    @Column(nullable = false)
    private String apellido;

    /**
     * Padre de las persona
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_padre")
    private Persona padre;

    /**
     * Pais de la persona
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_pais", referencedColumnName = "id")
    private Pais pais;

    //TODO esto podria ser una tabla adicional
    /**
     * Tipo de documento de la persona
     */
    @Column(name = "tipo_documento", nullable = false)
    private String tipoDocumento;

    /**
     * Numero del Domcumento de la persona
     */
    @Column(name = "numero_documento", nullable = false)
    private String numeroDocumento;

    // TODO esto se podria hacer en otra tabla en caso de que se requiera mas de un telefono
    //  asi como tambien se puede agregar un regex para validar el formato
    /**
     * Telefono de contacto de la persona
     */
    @Column(name = "telefono")
    private String telefono;

    /**
     * Mail de contacto de la persona
     */
    @Column(name = "mail")
    private String mail;

    /**
     * Fecha de nacimiento de la persona
     */
    @JsonFormat(pattern = "yyyy/MM/dd")
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    /**
     * Actualiza los datos de una persona menos el id y el padre
     * @param p datos para actualizar a la persona
     */
    public void update(Persona p) {
        this.nombre = p.getNombre();
        this.apellido = p.getApellido();
        this.pais = p.getPais();
        this.tipoDocumento = p.getTipoDocumento();
        this.numeroDocumento = p.getNumeroDocumento();
        this.telefono = p.getTelefono();
        this.mail = p.getMail();
        this.fechaNacimiento = p.getFechaNacimiento();
    }
}