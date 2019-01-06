package com.cayuse.commenting.model.Exceptions;

public class ComplianceException extends Exception {
    public ComplianceException(String message) {
        super(message);

        //todo: add logging
    }
}
