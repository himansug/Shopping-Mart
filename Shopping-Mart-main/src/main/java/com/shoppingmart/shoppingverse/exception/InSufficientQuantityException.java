package com.shoppingmart.shoppingverse.exception;

public class InSufficientQuantityException extends RuntimeException{
    public InSufficientQuantityException(String message) {
        super(message);
    }
}
