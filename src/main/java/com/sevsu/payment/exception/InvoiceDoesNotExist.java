package com.sevsu.payment.exception;

public class InvoiceDoesNotExist extends BadRequestException{

    public InvoiceDoesNotExist() {
        super("Платежа с таким уникальным идентификатором не существует");
    }

}
