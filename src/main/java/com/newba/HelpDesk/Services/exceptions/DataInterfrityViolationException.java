package com.newba.HelpDesk.Services.exceptions;

public class DataInterfrityViolationException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public DataInterfrityViolationException(String message, Throwable cause){
        super(message, cause);
    }

    public DataInterfrityViolationException(String message){
        super(message);
    }
}