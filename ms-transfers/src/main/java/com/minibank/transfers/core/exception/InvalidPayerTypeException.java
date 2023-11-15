package com.minibank.transfers.core.exception;

public class InvalidPayerTypeException extends RuntimeException {

    public InvalidPayerTypeException(String value) {
        super(value);
    }
}
