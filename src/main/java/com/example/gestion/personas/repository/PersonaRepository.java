package com.example.gestion.personas.repository;

import com.example.gestion.personas.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
}