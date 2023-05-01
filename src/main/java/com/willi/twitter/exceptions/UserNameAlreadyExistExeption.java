package com.willi.twitter.exceptions;

public class UserNameAlreadyExistExeption extends RuntimeException{
    public UserNameAlreadyExistExeption(String message){
        super(message);
    }
}
