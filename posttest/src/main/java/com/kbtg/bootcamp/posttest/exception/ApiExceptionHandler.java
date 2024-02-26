package com.kbtg.bootcamp.posttest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZonedDateTime;


@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<ApiExceptionResponse> handleNotFoundException(NotFoundException notFoundException) {
        ApiExceptionResponse response = new ApiExceptionResponse(notFoundException.getMessage(),
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<ApiExceptionResponse> handleNotFoundException(BadRequestException badRequestException) {
        ApiExceptionResponse response = new ApiExceptionResponse(badRequestException.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


}


