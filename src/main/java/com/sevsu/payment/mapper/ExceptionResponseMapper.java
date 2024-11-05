package com.sevsu.payment.mapper;

import com.sevsu.payment.exception.HttpException;
import com.sevsu.payment.response.ExceptionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ExceptionResponseMapper {

    @Mapping(target = "message", source = "message")
    @Mapping(target = "code", expression = "java(httpException.getCode())")
    @Mapping(target = "httpStatus", expression = "java(httpException.getHttpStatus())")
    ExceptionResponse mapExceptionToExceptionResponse(HttpException httpException);

}
