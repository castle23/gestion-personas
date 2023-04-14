package com.example.gestion.personas.controller;

import com.example.gestion.personas.dto.ErrorGeneral;
import com.example.gestion.personas.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.MessageFormat;

/**
 * Clase para formatear las excepciones de negocio
 */
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
    @ExceptionHandler(ContactoNotFound.class)
    public ResponseEntity<ErrorGeneral> exceptionContactoNotFound(){
        ErrorGeneral error = new ErrorGeneral(MensajeError.CONTACTO_NOT_FOUND);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DatosPersonaInvalid.class)
    public ResponseEntity<ErrorGeneral> exceptionDatosPersonaInvalid(DatosPersonaInvalid e){
        ErrorGeneral error = new ErrorGeneral(MensajeError.DATOS_PERSONA_INVALID.name(),MessageFormat.format(MensajeError.DATOS_PERSONA_INVALID.getDescripcion(), e.getMessage()));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(AncestroInvalid.class)
    public ResponseEntity<ErrorGeneral> exceptionAncestroInvalid(){
        ErrorGeneral error = new ErrorGeneral(MensajeError.ANCESTRO_INVALID);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(IdentificadorInvalido.class)
    public ResponseEntity<ErrorGeneral> exceptionIdentificadorInvalido(){
        ErrorGeneral error = new ErrorGeneral(MensajeError.ID_INVALID);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorGeneral> exceptionGeneral(){
        ErrorGeneral error = new ErrorGeneral(MensajeError.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
