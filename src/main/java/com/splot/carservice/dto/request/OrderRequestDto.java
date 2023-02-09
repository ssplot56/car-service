package com.splot.carservice.dto.request;

import lombok.Data;
import java.util.List;

@Data
public class OrderRequestDto {
    private Long carId;
    private String problemDescription;
    private List<Long> favorIds;
}
