package com.robertorebolledonaharro.bichoapi.common.error.exeptions;

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
