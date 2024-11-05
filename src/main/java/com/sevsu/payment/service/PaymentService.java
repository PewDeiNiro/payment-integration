package com.sevsu.payment.service;

import com.sevsu.payment.entity.Invoice;
import com.sevsu.payment.enums.PaymentStatus;
import com.sevsu.payment.exception.AmountCanNotBeLessZeroException;
import com.sevsu.payment.exception.InvoiceDoesNotExist;
import com.sevsu.payment.mapper.InvoiceMapper;
import com.sevsu.payment.repository.InvoiceRepository;
import com.sevsu.payment.request.CreateInvoiceRequest;
import com.sevsu.payment.response.InvoiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceMapper invoiceMapper;

    public InvoiceResponse createInvoice(CreateInvoiceRequest request) {
        if (request.getAmount() <= 0){
            throw new AmountCanNotBeLessZeroException("Сумма платежа не может быть меньше 0");
        }
        Invoice invoice = invoiceMapper.mapCreateInvoiceToInvoice(request);
        invoice.setStatus(PaymentStatus.PENDING);
        invoiceRepository.saveAndFlush(invoice);
        return invoiceMapper.mapInvoiceToInvoiceResponse(invoice);
    }

    public InvoiceResponse getInvoiceById(int id){
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new InvoiceDoesNotExist("Платежа с таким уникальным идентификатором не существует"));
        return invoiceMapper.mapInvoiceToInvoiceResponse(invoice);
    }

    public List<Invoice> getAllInvoices(){
        return invoiceRepository.findAll();
    }

}
