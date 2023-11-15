package com.minibank.transfers.core.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String value) {
        super(value);
    }
}
