package com.willi.twitter.controller.exeption;

public class EmailAlreadyExistExeption extends RuntimeException {
    public EmailAlreadyExistExeption(String message) {
        super(message);
    }
}
