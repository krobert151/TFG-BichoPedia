package com.robertorebolledonaharro.bichoapi.common.error.exeptions;

public class EmailAlreadyExistsException extends IllegalArgumentException{
    public EmailAlreadyExistsException() {
        super();
    }

    public EmailAlreadyExistsException(String s) {
        super(s);
    }

    public EmailAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
