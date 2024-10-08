package com.springfarm.SpringFarm.dtos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ErrorResponseDTO {
    private String message;
    private String timestamp;
    private String details;

    public ErrorResponseDTO(String message, String timestamp, String details) {
        this.message = message;
        this.timestamp = timestamp;
        this.details = details;
    }

    public ErrorResponseDTO(String message, String details) {
        this.message = message;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.details = details;
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
