package com.splot.carservice.dto.request;

import lombok.Data;
import java.util.List;

@Data
public class CarOwnerRequestDto {
    private String fullName;
    private List<CarRequestDto> carRequestDtos;
}
