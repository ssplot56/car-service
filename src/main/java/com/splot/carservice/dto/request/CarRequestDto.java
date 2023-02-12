package com.splot.carservice.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarRequestDto {
    private String model;
    private Integer manufactureYear;
    private String carNumber;
    private Long ownerId;
}
