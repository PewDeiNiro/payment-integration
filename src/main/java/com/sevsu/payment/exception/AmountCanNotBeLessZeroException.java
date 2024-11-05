package com.sevsu.payment.exception;

public class AmountCanNotBeLessZeroException extends BadRequestException{

    public AmountCanNotBeLessZeroException() {
        super("Сумма платежа не может быть меньше 0");
    }

}
