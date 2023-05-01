package com.willi.twitter.exceptions;

public class EmailAlreadyExistExeption extends RuntimeException {
    public EmailAlreadyExistExeption(String message) {
        super(message);
    }
}
