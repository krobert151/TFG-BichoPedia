package com.robertorebolledonaharro.bichoapi.specie.error;

import com.robertorebolledonaharro.bichoapi.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SpeciesErrorControllerAdvice {

    @ExceptionHandler({SpecieNotFoundException.class})
    public ResponseEntity<?> handleSpecieNotFoundException(SpecieNotFoundException ex, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.of(HttpStatus.NOT_FOUND, ex.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler({SpecieScientificNameAlreadyExists.class})
    public ResponseEntity<?> handleSpecieScientificNameAlreadyExistsException(SpecieScientificNameAlreadyExists ex, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.of(HttpStatus.BAD_REQUEST, ex.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler({SpecieDangerIncorrectException.class})
    public ResponseEntity<?> handleSpecieDangerIncorrectException(SpecieDangerIncorrectException ex, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.of(HttpStatus.BAD_REQUEST, ex.getMessage(), request.getRequestURI()));
    }

}
