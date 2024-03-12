package com.robertorebolledonaharro.bichoapi.specie.error;

import jakarta.persistence.EntityNotFoundException;

public class SpecieNotFoundException extends EntityNotFoundException {

    public SpecieNotFoundException() {
        super();
    }

    public SpecieNotFoundException(Exception cause) {
        super(cause);
    }

    public SpecieNotFoundException(String message) {
        super(message);
    }

    public SpecieNotFoundException(String message, Exception cause) {
        super(message, cause);
    }
}
