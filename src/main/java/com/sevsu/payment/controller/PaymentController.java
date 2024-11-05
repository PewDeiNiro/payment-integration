package com.sevsu.payment.controller;

import com.sevsu.payment.entity.Invoice;
import com.sevsu.payment.request.CreateInvoiceRequest;
import com.sevsu.payment.response.InvoiceResponse;
import com.sevsu.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/invoice")
    public InvoiceResponse createInvoice(@RequestBody CreateInvoiceRequest request) {
        return paymentService.createInvoice(request);
    }

    @GetMapping("/invoices/{id}")
    public InvoiceResponse getInvoiceById(@PathVariable("id") int id) {
        return paymentService.getInvoiceById(id);
    }

    @GetMapping("/invoices")
    public List<Invoice> getAllInvoices() {
        return paymentService.getAllInvoices();
    }

}
