package com.sevsu.payment.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HttpException extends RuntimeException{

    protected int code;

    protected HttpStatus httpStatus;

    public HttpException(String message) {
        super(message);
    }

}
