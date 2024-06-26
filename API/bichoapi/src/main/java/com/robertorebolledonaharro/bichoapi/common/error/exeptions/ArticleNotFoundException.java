package com.robertorebolledonaharro.bichoapi.common.error.exeptions;


import jakarta.persistence.EntityNotFoundException;

public class ArticleNotFoundException extends EntityNotFoundException {
    public ArticleNotFoundException() {
        super();
    }

    public ArticleNotFoundException(Exception cause) {
        super(cause);
    }

    public ArticleNotFoundException(String message) {
        super(message);
    }

    public ArticleNotFoundException(String message, Exception cause) {
        super(message, cause);
    }
}
