package com.example.CustomExceptions;

public class ContactNotFoundException extends Throwable{

    public ContactNotFoundException(String message) {
        super(message);
    }
}
