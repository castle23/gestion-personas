package com.example.gestion.personas.service;

import com.example.gestion.personas.entity.EstadisticasPorPais;
import com.example.gestion.personas.repository.PaisRepository;
import com.example.gestion.personas.repository.PersonaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EstadisticasService {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private PaisRepository paisRepository;

    /**
     * Calcula los porcentajes de personas por país
     * @return Lista de estadisticas
     */
    public List<EstadisticasPorPais> getEstadisticasPorPais() {
        long count = personaRepository.count();
        List<EstadisticasPorPais> estadistica = paisRepository.obtenerEstadisticasPorPais();
        estadistica.forEach(e -> e.setPorcentaje(e.getCantidadPersonas() * 100 / count));
        return estadistica;
    }
}
