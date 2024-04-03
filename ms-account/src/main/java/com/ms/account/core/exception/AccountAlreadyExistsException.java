package com.ms.account.core.exception;

public class AccountAlreadyExistsException extends RuntimeException {
                                                              
    public AccountAlreadyExistsException(String value) {
        super(value);
    }
}
