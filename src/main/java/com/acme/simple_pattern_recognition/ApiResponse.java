package com.acme.simple_pattern_recognition;

import java.time.LocalDateTime;

public class ApiResponse<T> {
    private T response;
    private LocalDateTime timestamp;

    public ApiResponse() {
    }

    public ApiResponse(T response) {
        this.response = response;
        this.timestamp = LocalDateTime.now();
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}