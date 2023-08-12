package com.shoppingmart.shoppingverse.exception;

public class EmptyCartFoundException extends RuntimeException{
    public EmptyCartFoundException(String message) {
        super(message);
    }
}
