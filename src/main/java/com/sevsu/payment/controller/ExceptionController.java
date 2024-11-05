package com.sevsu.payment.controller;

import com.sevsu.payment.exception.HttpException;
import com.sevsu.payment.mapper.ExceptionResponseMapper;
import com.sevsu.payment.response.ExceptionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @Autowired
    private ExceptionResponseMapper exceptionResponseMapper;

    @ExceptionHandler(HttpException.class)
    public ResponseEntity<ExceptionResponse> handleHttpException(HttpException exception){
        ExceptionResponse exceptionResponse = exceptionResponseMapper.mapExceptionToExceptionResponse(exception);
        return new ResponseEntity<>(exceptionResponse, exceptionResponse.getHttpStatus());
    }

}
