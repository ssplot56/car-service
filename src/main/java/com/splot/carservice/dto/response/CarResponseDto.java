package com.splot.carservice.dto.response;

import lombok.Data;

@Data
public class CarResponseDto {
    private Long id;
    private String model;
    private Integer manufactureYear;
    private String carNumber;
    private Long ownerId;
}
