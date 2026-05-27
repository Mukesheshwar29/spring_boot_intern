package com.eduhub.eduhub_backend.exceptions;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(
            ResourceNotFoundException.class)

    public ResponseEntity<ErrorResponse>
    handleNotFound(

            ResourceNotFoundException ex,

            HttpServletRequest request){

        return new ResponseEntity<>(

                new ErrorResponse(

                        LocalDateTime.now(),

                        404,

                        "NOT_FOUND",

                        ex.getMessage(),

                        request.getRequestURI()),

                HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(
            IllegalArgumentException.class)

    public ResponseEntity<ErrorResponse>
    handleIllegal(

            IllegalArgumentException ex,

            HttpServletRequest request){

        return new ResponseEntity<>(

                new ErrorResponse(

                        LocalDateTime.now(),

                        400,

                        "BAD_REQUEST",

                        ex.getMessage(),

                        request.getRequestURI()),

                HttpStatus.BAD_REQUEST);
    }
}