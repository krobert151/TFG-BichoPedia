package com.robertorebolledonaharro.bichoapi.user.error;

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
