package com.sevsu.payment.mapper;

import com.sevsu.payment.exception.HttpException;
import com.sevsu.payment.response.ExceptionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExceptionResponseMapper {

    @Mapping(target = "code", expression = "java(httpException.getCode())")
    @Mapping(target = "httpStatus", expression = "java(httpException.getHttpStatus())")
    ExceptionResponse mapExceptionToExceptionResponse(HttpException httpException);

}
