package com.robertorebolledonaharro.bichoapi.common.errror;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonErrorControllerAdvice {
    @ExceptionHandler({UUIDIlegalFormatException.class})
    public ResponseEntity<?> handleUUIDIlegalFormatException(UUIDIlegalFormatException ex, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.of(HttpStatus.BAD_REQUEST, ex.getMessage(), request.getRequestURI()));
    }

}
