package com.robertorebolledonaharro.bichoapi.common.error;

import com.robertorebolledonaharro.bichoapi.common.error.exeptions.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalRestControllerAdvice {
    @ExceptionHandler({UUIDIlegalFormatException.class})
    public ResponseEntity<?> handleUUIDIlegalFormatException(UUIDIlegalFormatException ex, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.of(HttpStatus.BAD_REQUEST, ex.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler({ArticleNotFoundException.class})
    public ResponseEntity<?> handleArticleNotFoundException(ArticleNotFoundException ex, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.of(HttpStatus.NOT_FOUND, ex.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler({EncounterNotFoundException.class})
    public ResponseEntity<?> handleEncounterNotFoundException(EncounterNotFoundException ex, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.of(HttpStatus.NOT_FOUND,ex.getMessage(),request.getRequestURI()));
    }

    @ExceptionHandler({UnauthorizedEncounterAccessException.class})
    public ResponseEntity<?> handleUnauthorizedEncounterAccessException(UnauthorizedEncounterAccessException ex, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ErrorMessage.of(HttpStatus.FORBIDDEN, ex.getMessage(), request.getRequestURI()));
    }

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

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException ex, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.of(HttpStatus.NOT_FOUND,ex.getMessage(),request.getRequestURI()));
    }


    @ExceptionHandler({PersonRoleIncorrectException.class})
    public ResponseEntity<?> handlePersonRoleIncorrectException(PersonRoleIncorrectException ex,  HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.of(HttpStatus.BAD_REQUEST, ex.getMessage(),request.getRequestURI()));
    }

    @ExceptionHandler({EmailAlreadyExistsException.class})
    public ResponseEntity<?> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex,  HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.of(HttpStatus.BAD_REQUEST, ex.getMessage(),request.getRequestURI()));
    }

}
