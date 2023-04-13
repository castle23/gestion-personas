package com.example.gestion.personas.controller;

import com.example.gestion.personas.exception.ExceptionFechaNacimientoInvalid;
import com.example.gestion.personas.exception.ExceptionPersonaNotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalError {

    @ExceptionHandler(ExceptionFechaNacimientoInvalid.class)
    public ResponseEntity<ErrorGeneral> exceptionFechaNacimiento(){
        ErrorGeneral error = new ErrorGeneral(MensajeError.FECHA_NACIMIENTO_INVALID);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ExceptionPersonaNotFound.class)
    public ResponseEntity<ErrorGeneral> exceptionPersonaNotFound(){
        ErrorGeneral error = new ErrorGeneral(MensajeError.PERSONA_NOT_FOUND);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
