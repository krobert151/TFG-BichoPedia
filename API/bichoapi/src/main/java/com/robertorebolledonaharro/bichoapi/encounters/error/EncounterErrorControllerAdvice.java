package com.robertorebolledonaharro.bichoapi.encounters.error;

import com.robertorebolledonaharro.bichoapi.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EncounterErrorControllerAdvice {

    @ExceptionHandler({EncounterNotFoundException.class})
    public ResponseEntity<?> handleEncounterNotFoundException(EncounterNotFoundException ex, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.of(HttpStatus.NOT_FOUND,ex.getMessage(),request.getRequestURI()));
    }

}
