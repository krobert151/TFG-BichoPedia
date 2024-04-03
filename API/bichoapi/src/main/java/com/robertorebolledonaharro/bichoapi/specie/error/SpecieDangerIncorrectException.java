package com.robertorebolledonaharro.bichoapi.specie.error;

import org.apache.coyote.BadRequestException;

public class SpecieDangerIncorrectException extends BadRequestException {
    public SpecieDangerIncorrectException() {
        super();
    }

    public SpecieDangerIncorrectException(String message) {
        super(message);
    }

    public SpecieDangerIncorrectException(Throwable throwable) {
        super(throwable);
    }

    public SpecieDangerIncorrectException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
