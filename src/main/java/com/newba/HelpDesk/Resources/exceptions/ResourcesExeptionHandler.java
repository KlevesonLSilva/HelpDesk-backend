package com.newba.HelpDesk.Resources.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.newba.HelpDesk.Services.exceptions.DataInterfrityViolationException;
import com.newba.HelpDesk.Services.exceptions.ObjectnotFoundexcption;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourcesExeptionHandler {

    @ExceptionHandler(ObjectnotFoundexcption.class)
    public ResponseEntity<StanderError> objectnotFoundException(ObjectnotFoundexcption ex, HttpServletRequest request){

        StanderError error  = new StanderError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
         "Object Not Found", ex.getMessage(), request.getRequestURI());
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }

    @ExceptionHandler(DataInterfrityViolationException.class)
    public ResponseEntity<StanderError> DataInterfrityViolationException(DataInterfrityViolationException ex, HttpServletRequest request){

        StanderError error  = new StanderError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
         "Violação de dados", ex.getMessage(), request.getRequestURI());
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

    }

}