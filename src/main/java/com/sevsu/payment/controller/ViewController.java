package com.sevsu.payment.controller;

import com.sevsu.payment.entity.Invoice;
import com.sevsu.payment.enums.PaymentStatus;
import com.sevsu.payment.exception.InvoiceDoesNotExist;
import com.sevsu.payment.feign.DeliveryClient;
import com.sevsu.payment.mapper.InvoiceMapper;
import com.sevsu.payment.model.CardDetails;
import com.sevsu.payment.repository.InvoiceRepository;
import com.sevsu.payment.request.UpdatePaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
@RequestMapping("/payment")
public class ViewController {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Autowired
    private DeliveryClient deliveryClient;

    private String status;

    @RequestMapping("/invoice/{id}")
    public String createInvoice(@PathVariable int id, Model model) {
        Optional<Invoice> invoiceEntity = invoiceRepository.findById(id);
        if (invoiceEntity.isEmpty()) {
            return "payment-not-found";
        }
        Invoice invoice = invoiceEntity.get();
        if (invoiceEntity.get().getStatus() == PaymentStatus.SUCCEEDED || invoiceEntity.get().getStatus() == PaymentStatus.CANCELLED){
            if (invoiceEntity.get().getStatus() == PaymentStatus.SUCCEEDED){
                model.addAttribute("paymentStatus", "Оплата прошла успешно!");
                UpdatePaymentRequest request = invoiceMapper.mapInvoiceToUpdatePaymentRequest(invoice);
                deliveryClient.updatePayment(request);
            }
            else{
                model.addAttribute("paymentStatus", "Оплата была отменена!");
            }
            return "payment-finished";
        }

        model.addAttribute("id", invoice.getId());
        model.addAttribute("status", status);
        model.addAttribute("cardDetails", new CardDetails());
        return "payment-page";
    }

    @PostMapping("/invoice/{id}")
    public RedirectView payInvoice(@PathVariable int id, @ModelAttribute("cardDetails") CardDetails details, Model model) {
        if (!details.getNumber().replaceAll(" ", "").equals("5124585563456201")
                || !details.getExpire().replaceAll(" ", "").equals("01/25")
                || !details.getCvv().replaceAll(" ", "").equals("123")) {
            status = "Ошибка";
        }
        else{
            Invoice invoice = invoiceRepository.findById(id)
                    .orElseThrow(() -> new InvoiceDoesNotExist("Платежа с таким уникальным идентификатором не существует"));
            invoice.setStatus(PaymentStatus.SUCCEEDED);
            invoiceRepository.saveAndFlush(invoice);
        }
        return new RedirectView("/payment/invoice/" + id);
    }

    @PostMapping("/invoice/cancelInvoice/{id}")
    public RedirectView cancelInvoice(@PathVariable int id) {
        Optional<Invoice> invoiceEntity = invoiceRepository.findById(id);
        if (invoiceEntity.isEmpty()) {
            return new RedirectView("/payment/invoice/error");
        }
        Invoice invoice = invoiceEntity.get();
        invoice.setStatus(PaymentStatus.CANCELLED);
        invoiceRepository.saveAndFlush(invoice);
        return new RedirectView("/payment/invoice/" + id);
    }

    @RequestMapping("/invoice/error")
    public String errorPage(){
        return "payment-not-found";
    }

}
