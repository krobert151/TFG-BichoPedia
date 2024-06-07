package com.robertorebolledonaharro.bichoapi.common.error.exeptions;

public class UnauthorizedEncounterAccessException extends RuntimeException{
    public UnauthorizedEncounterAccessException() {
        super();
    }

    public UnauthorizedEncounterAccessException(String message) {
        super(message);
    }

    public UnauthorizedEncounterAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnauthorizedEncounterAccessException(Throwable cause) {
        super(cause);
    }
}
