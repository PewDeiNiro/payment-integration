package com.sevsu.payment.mapper;

import com.sevsu.payment.entity.Invoice;
import com.sevsu.payment.request.CreateInvoiceRequest;
import com.sevsu.payment.request.UpdatePaymentRequest;
import com.sevsu.payment.response.InvoiceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface InvoiceMapper {

    @Mapping(target = "title", source = "title")
    @Mapping(target = "amount", source = "amount")
    @Mapping(target = "userId", source = "userId")
    Invoice mapCreateInvoiceToInvoice(CreateInvoiceRequest request);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "amount", source = "amount")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "url", expression = "java(\"http://localhost:8091/payment/invoice/\" + invoice.getId())")
    InvoiceResponse mapInvoiceToInvoiceResponse(Invoice invoice);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "amount", source = "amount")
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "url", expression = "java(\"http://localhost:8091/payment/invoice/\" + invoice.getId())")
    UpdatePaymentRequest mapInvoiceToUpdatePaymentRequest(Invoice invoice);

}
