package com.minibank.transfers.core.exception;

public class InsufficientBalanceException extends RuntimeException {

    public InsufficientBalanceException(String value) {
        super(value);
    }
}
