package com.jacobo.CRUDApp;

public class UserNotFoundException extends Throwable {
    public UserNotFoundException(String message){
        super(message);
    }
}
