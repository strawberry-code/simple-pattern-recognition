package com.acme.hip.hop.chocobo.rest;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Generic API response container class.
 * @param <T> the type of the response object
 */
public class ApiResponse<T> {
    private T response;
    private LocalDateTime timestamp;
    private Long count;  // Optional count field

    /**
     * Default constructor.
     */
    public ApiResponse() {
    }

    /**
     * Constructs an ApiResponse with a response object and sets the current timestamp.
     * Also initializes 'count' if 'response' is a List.
     * @param response the response object
     */
    public ApiResponse(T response) {
        this.response = response;
        this.timestamp = LocalDateTime.now();  // Automatically sets the response time

        // Set count if response is a List
        if (response instanceof List) {
            this.count = (long)((List<?>) response).size();
        }
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
     * If 'response' is a List, 'count' is also updated.
     * @param response the new response object to set
     */
    public void setResponse(T response) {
        this.response = response;
        if (response instanceof List) {
            this.count = (long)((List<?>) response).size();
        } else {
            this.count = null;  // Reset count if not a List
        }
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

    /**
     * Returns the optional count of elements if the response is a list.
     * @return the count or null if not applicable
     */
    public Long getCount() {
        return count;
    }
}