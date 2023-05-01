package com.willi.twitter.exceptions;

public class UserNameAndEmailAlreadyExistException extends RuntimeException {
    public UserNameAndEmailAlreadyExistException(String message){
        super(message);
    }
}
