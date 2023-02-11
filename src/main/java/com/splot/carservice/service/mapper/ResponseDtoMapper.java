package com.splot.carservice.service.mapper;

public interface ResponseDtoMapper<D, T> {
    D mapToDto(T t);
}
