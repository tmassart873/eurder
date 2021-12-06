package com.switchfully.eurder.exceptions;

public class UserNotAuthorizedException extends RuntimeException{
    public UserNotAuthorizedException(){super("You are not allowed to do this action.");}
}
