package com.robertorebolledonaharro.bichoapi.common.error.exeptions;

import jakarta.persistence.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {
    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(Exception cause) {
        super(cause);
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Exception cause) {
        super(message, cause);
    }
}
