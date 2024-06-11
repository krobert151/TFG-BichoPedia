package com.robertorebolledonaharro.bichoapi.common.error.exeptions;

import org.springframework.dao.DuplicateKeyException;

public class SpecieScientificNameAlreadyExists extends DuplicateKeyException {
    public SpecieScientificNameAlreadyExists(String msg) {
        super(msg);
    }

    public SpecieScientificNameAlreadyExists(String msg, Throwable cause) {
        super(msg, cause);
    }
}
