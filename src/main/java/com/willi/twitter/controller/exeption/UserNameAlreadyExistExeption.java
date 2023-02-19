package com.willi.twitter.controller.exeption;

public class UserNameAlreadyExistExeption extends RuntimeException{
    public UserNameAlreadyExistExeption(String message){
        super(message);
    }
}
