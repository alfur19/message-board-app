package com.example.backend.service.exceptions;

public class MessageNotFoundException extends RuntimeException {

    public MessageNotFoundException(Long id) {
        super("Could not find message " + id);
    }
}
