package com.acme.hip.hop.chocobo.rest;

import java.time.LocalDateTime;

/**
 * Generic API response container class.
 * @param <T> the type of the response object
 */
public class ApiResponse<T> {
    private T response;
    private LocalDateTime timestamp;

    /**
     * Default constructor.
     */
    public ApiResponse() {
    }

    /**
     * Constructs an ApiResponse with a response object and sets the current timestamp.
     * @param response the response object
     */
    public ApiResponse(T response) {
        this.response = response;
        this.timestamp = LocalDateTime.now();  // Automatically sets the response time
    }

    /**
     * Returns the response object.
     * @return the response object
     */
    public T getResponse() {
        return response;
    }

    /**
     * Sets the response object.
     * @param response the new response object to set
     */
    public void setResponse(T response) {
        this.response = response;
    }

    /**
     * Returns the timestamp of the ApiResponse.
     * @return the timestamp
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp of the ApiResponse.
     * @param timestamp the new timestamp to set
     */
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}