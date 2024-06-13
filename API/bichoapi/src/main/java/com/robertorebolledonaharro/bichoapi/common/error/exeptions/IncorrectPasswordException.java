package com.robertorebolledonaharro.bichoapi.common.error.exeptions;

import org.apache.coyote.BadRequestException;

public class IncorrectPasswordException extends BadRequestException {
    public IncorrectPasswordException() {
        super();
    }

    public IncorrectPasswordException(String message) {
        super(message);
    }

    public IncorrectPasswordException(Throwable throwable) {
        super(throwable);
    }

    public IncorrectPasswordException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
