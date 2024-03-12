package com.robertorebolledonaharro.bichoapi.encounters.error;

import jakarta.persistence.EntityNotFoundException;

public class EncounterNotFoundException extends EntityNotFoundException {

    public EncounterNotFoundException() {
        super();
    }

    public EncounterNotFoundException(Exception cause) {
        super(cause);
    }

    public EncounterNotFoundException(String message) {
        super(message);
    }

    public EncounterNotFoundException(String message, Exception cause) {
        super(message, cause);
    }
}
