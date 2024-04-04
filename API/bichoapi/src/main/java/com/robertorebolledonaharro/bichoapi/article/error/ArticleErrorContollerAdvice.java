package com.robertorebolledonaharro.bichoapi.article.error;

import com.robertorebolledonaharro.bichoapi.common.errror.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ArticleErrorContollerAdvice {

    @ExceptionHandler({ArticleNotFoundException.class})
    public ResponseEntity<?> handleArticleNotFoundException(ArticleNotFoundException ex, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.of(HttpStatus.NOT_FOUND, ex.getMessage(), request.getRequestURI()));
    }

}
