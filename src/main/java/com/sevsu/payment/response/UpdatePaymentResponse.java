package com.sevsu.payment.response;

import com.sevsu.payment.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePaymentResponse {

    private int id;

    private String url;

    private double amount;

    private String title;

    private int userId;

    private PaymentStatus status;

}
