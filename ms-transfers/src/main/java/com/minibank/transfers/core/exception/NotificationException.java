package com.minibank.transfers.core.exception;

import lombok.Getter;

@Getter
public class NotificationException extends RuntimeException {
    private final Integer value;
    private final String userMessage;
    private final String title;
    private final String type;

    public NotificationException(Integer value, String userMessage, String title, String type) {
        this.value = value;
        this.userMessage = userMessage;
        this.title = title;
        this.type = type;
    }

    public NotificationException(String message, Integer value, String title, String type) {
        this.value = value;
        this.userMessage = message;
        this.title = title;
        this.type = type;
    }

}
