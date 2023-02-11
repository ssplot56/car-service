package com.splot.carservice.dto.response;

import lombok.Data;
import java.util.List;

@Data
public class CarOwnerResponseDto {
    private Long id;
    private String fullName;
    private List<Long> carIds;
    private List<Long> orderIds;
}
