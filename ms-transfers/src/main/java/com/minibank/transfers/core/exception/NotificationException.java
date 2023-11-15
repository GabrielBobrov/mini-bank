package com.minibank.transfers.core.exception;

import lombok.Getter;

@Getter
public class NotificationException extends RuntimeException {
    private final Integer value;

    public NotificationException(Integer value) {
        this.value = value;
    }

    public NotificationException(String message, Integer value) {
        super(message);
        this.value = value;
    }

}
