package com.siddhi.taskmanagement.exception;   // ← this package must match

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}