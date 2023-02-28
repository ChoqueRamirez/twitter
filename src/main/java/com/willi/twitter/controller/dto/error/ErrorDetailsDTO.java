package com.willi.twitter.controller.dto.error;

import org.springframework.http.HttpStatus;

public class ErrorDetailsDTO {
    private String message;
    private HttpStatus status;

    public ErrorDetailsDTO(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
