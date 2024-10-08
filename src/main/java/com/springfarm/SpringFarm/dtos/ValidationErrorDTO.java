package com.springfarm.SpringFarm.dtos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ValidationErrorDTO {
    private String message;
    private String timestamp;
    private List<String> details;

    public ValidationErrorDTO(String message, String timestamp, List<String> details) {
        this.message = message;
        this.timestamp = timestamp;
        this.details = details;
    }

    public ValidationErrorDTO(String message, List<String> details) {
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

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }
}
