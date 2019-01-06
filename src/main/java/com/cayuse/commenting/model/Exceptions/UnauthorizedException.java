package com.cayuse.commenting.model.Exceptions;

public class UnauthorizedException extends Exception {
    public UnauthorizedException(String message) {
        super(message);

        //todo: add logging
    }
}
