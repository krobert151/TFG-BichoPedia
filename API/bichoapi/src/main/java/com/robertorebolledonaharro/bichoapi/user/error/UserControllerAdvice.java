package com.robertorebolledonaharro.bichoapi.user.error;

import com.robertorebolledonaharro.bichoapi.common.errror.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserControllerAdvice {


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
