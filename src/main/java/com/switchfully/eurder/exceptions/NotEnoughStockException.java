package com.switchfully.eurder.exceptions;

public class NotEnoughStockException extends RuntimeException {
    public NotEnoughStockException(){super("The stock is not sufficient for the order.");}
}
