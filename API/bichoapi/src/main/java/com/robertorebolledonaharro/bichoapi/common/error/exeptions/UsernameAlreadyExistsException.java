package com.robertorebolledonaharro.bichoapi.common.error.exeptions;

import org.apache.coyote.BadRequestException;

public class UsernameAlreadyExistsException extends BadRequestException {
    public UsernameAlreadyExistsException() {
        super();
    }

    public UsernameAlreadyExistsException(String message) {
        super(message);
    }

    public UsernameAlreadyExistsException(Throwable throwable) {
        super(throwable);
    }

    public UsernameAlreadyExistsException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
