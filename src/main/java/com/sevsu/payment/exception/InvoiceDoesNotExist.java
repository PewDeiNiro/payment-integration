package com.sevsu.payment.exception;

public class InvoiceDoesNotExist extends BadRequestException{

    public InvoiceDoesNotExist(String message) {
        super(message);
    }

}
