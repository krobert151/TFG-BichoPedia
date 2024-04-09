package com.robertorebolledonaharro.bichoapi.common.error.exeptions;

import org.apache.coyote.BadRequestException;

public class PersonRoleIncorrectException extends BadRequestException {
    public PersonRoleIncorrectException() {
        super();
    }

    public PersonRoleIncorrectException(String message) {
        super(message);
    }

    public PersonRoleIncorrectException(Throwable throwable) {
        super(throwable);
    }

    public PersonRoleIncorrectException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
