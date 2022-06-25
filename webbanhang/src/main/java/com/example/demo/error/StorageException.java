package com.example.demo.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StorageException extends RuntimeException {
    private String messageContent;
    private String messageKey;
    private String[] params;

    public StorageException(String messageContent) {
        this.messageContent = messageContent;
    }

    public StorageException(String msgKey, String[] params) {
        this.messageKey = msgKey;
        this.params = params;
    }
}