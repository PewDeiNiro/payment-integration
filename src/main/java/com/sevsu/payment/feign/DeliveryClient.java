package com.sevsu.payment.feign;

import com.sevsu.payment.request.UpdatePaymentRequest;
import com.sevsu.payment.response.UpdatePaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "DeliveryClient",
        url = "${services.intercity-delivery.url}")
public interface DeliveryClient {

    @PostMapping("/payment/update")
    UpdatePaymentResponse updatePayment(UpdatePaymentRequest request);

}
