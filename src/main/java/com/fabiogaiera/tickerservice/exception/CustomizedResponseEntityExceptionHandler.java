package com.fabiogaiera.tickerservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomizedResponseEntityExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleException(Exception ex) {

        //TODO return JSON content
        ErrorResponse errorResponse = new ErrorResponse();
        logger.error(String.format("%s%s", "Error during processing the request: ", ex.getMessage()));
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public final ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(Exception ex) {

        //TODO return JSON content
        ErrorResponse errorResponse = new ErrorResponse();
        logger.error(String.format("%s%s", "Error during processing the request: ", ex.getMessage()));
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

}