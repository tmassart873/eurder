package com.switchfully.eurder.exceptions;

public class PasswordNotValidException extends RuntimeException{
    public PasswordNotValidException(){super("Wrong password");}
}
