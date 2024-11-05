package com.sevsu.payment.exception;

import lombok.Data;

public class AmountCanNotBeLessZeroException extends BadRequestException{

    public AmountCanNotBeLessZeroException(String message) {
        super(message);
    }

}
