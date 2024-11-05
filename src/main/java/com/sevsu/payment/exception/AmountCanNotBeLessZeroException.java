package com.sevsu.payment.exception;

public class AmountCanNotBeLessZeroException extends BadRequestException{

    public AmountCanNotBeLessZeroException(String message) {
        super(message);
    }

}
