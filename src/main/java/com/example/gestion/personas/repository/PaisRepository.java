package com.example.gestion.personas.repository;

import com.example.gestion.personas.entity.EstadisticasPorPais;
import com.example.gestion.personas.entity.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Maneja las tracciones con la BD de la entidad Pais
 */
public interface PaisRepository extends JpaRepository<Pais, Long> {

    @Query("SELECT new com.example.gestion.personas.entity.EstadisticasPorPais(p.pais.nombre, COUNT(p)) FROM Persona p GROUP BY p.pais.nombre")
    List<EstadisticasPorPais> obtenerEstadisticasPorPais();

}
