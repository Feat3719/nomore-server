package com.kimoi.nomore.exception;

public class BadRequestErrorException extends RuntimeException{
    public BadRequestErrorException(String message) {
        super(message);
    }
    
}
