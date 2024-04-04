package com.robertorebolledonaharro.bichoapi.common.errror;

public class UUIDIlegalFormatException extends IllegalArgumentException{
    public UUIDIlegalFormatException() {
        super();
    }

    public UUIDIlegalFormatException(String s) {
        super(s);
    }

    public UUIDIlegalFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public UUIDIlegalFormatException(Throwable cause) {
        super(cause);
    }
}
