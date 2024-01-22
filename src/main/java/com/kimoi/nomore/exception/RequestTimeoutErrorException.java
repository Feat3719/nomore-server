package com.kimoi.nomore.exception;


public class RequestTimeoutErrorException extends RuntimeException{
    public RequestTimeoutErrorException(String message) {
        super(message);
    }
    
}
