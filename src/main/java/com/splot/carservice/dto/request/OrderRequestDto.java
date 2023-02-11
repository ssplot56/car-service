package com.splot.carservice.dto.request;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class OrderRequestDto {
    private Long carId;
    private String problemDescription;
    private List<Long> productIds;
    private List<Long> favorIds;
}
