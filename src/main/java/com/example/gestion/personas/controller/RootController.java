package com.example.gestion.personas.controller;

import com.example.gestion.personas.dto.EstadisticasPorPais;
import com.example.gestion.personas.entity.Pais;
import com.example.gestion.personas.repository.PaisRepository;
import com.example.gestion.personas.service.EstadisticasService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller para exponer los datos maestros
 */
@Slf4j
@RestController
@RequestMapping("/")
public class RootController {

    @Autowired
    private PaisRepository paisRepository;
    @Autowired
    private EstadisticasService estadisticasService;

    /**
     * Retorna la lista de paises
     *
     * @return
     */
    //TODO se podria agregar una cache a la lista ya que no varia mucho, esto aumenta la performance
    @GetMapping("/paises")
    public ResponseEntity<List<Pais>> listarPaises() {
        List<Pais> paises = paisRepository.findAll();
        return new ResponseEntity<>(paises, HttpStatus.OK);
    }

    /**
     * retorna las estadisticas de personas por pais
     * @return  Lista de estadisticas por pais
     */
    @GetMapping("/stats")
    public List<EstadisticasPorPais> getEstadisticasPorPais() {
        return estadisticasService.getEstadisticasPorPais();
    }

    //TODO se podria hacer una lista de tipos de Documentos con una tabla aparte
}
