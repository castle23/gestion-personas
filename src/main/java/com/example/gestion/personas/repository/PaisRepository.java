package com.example.gestion.personas.repository;

import com.example.gestion.personas.entity.Pais;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Maneja las tracciones con la BD de la entidad Pais
 */
public interface PaisRepository extends JpaRepository<Pais, Long> {
}
