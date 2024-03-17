package com.flx.quasar;

public class FooException extends RuntimeException {

    private int code;

    private String message;

    public FooException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
