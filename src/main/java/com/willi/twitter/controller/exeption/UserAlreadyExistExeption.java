package com.willi.twitter.controller.exeption;

public class UserAlreadyExistExeption extends RuntimeException{
    public UserAlreadyExistExeption(String message){
        super(message);
    }
}
