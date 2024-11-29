package com.sevsu.payment.mapper;

import com.sevsu.payment.entity.Invoice;
import com.sevsu.payment.request.CreateInvoiceRequest;
import com.sevsu.payment.request.UpdatePaymentRequest;
import com.sevsu.payment.response.InvoiceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InvoiceMapper {

    Invoice mapCreateInvoiceToInvoice(CreateInvoiceRequest request);

    @Mapping(target = "url", expression = "java(\"http://localhost:8091/payment/invoice/\" + invoice.getId())")
    InvoiceResponse mapInvoiceToInvoiceResponse(Invoice invoice);

    @Mapping(target = "url", expression = "java(\"http://localhost:8091/payment/invoice/\" + invoice.getId())")
    UpdatePaymentRequest mapInvoiceToUpdatePaymentRequest(Invoice invoice);

}
