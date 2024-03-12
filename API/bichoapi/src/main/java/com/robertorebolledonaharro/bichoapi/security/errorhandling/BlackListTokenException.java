package com.robertorebolledonaharro.bichoapi.security.errorhandling;

import javax.naming.AuthenticationException;

public class BlackListTokenException extends AuthenticationException {

    public BlackListTokenException(String explanation) {
        super(explanation);
    }

    public BlackListTokenException() {
        super();
    }
}
