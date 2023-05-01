package com.willi.twitter.exceptions;

public class UserAlreadyExistExeption extends RuntimeException{
    public UserAlreadyExistExeption(String message){
        super(message);
    }
}
