package com.newba.HelpDesk.Services.exceptions;

public class ObjectnotFoundexcption extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ObjectnotFoundexcption(String message, Throwable cause){
        super(message, cause);
    }

    public ObjectnotFoundexcption(String message){
        super(message);
    }
}