package com.splot.carservice.dto.request;

import lombok.Data;

@Data
public class CarRequestDto {
    private String model;
    private Integer manufactureYear;
    private String carNumber;
    private Long ownerId;
}
