package com.switchfully.eurder.exceptions;

public class UserDoesNotExistException extends RuntimeException{
    public UserDoesNotExistException(){super("This user doesn't exist");}
}
