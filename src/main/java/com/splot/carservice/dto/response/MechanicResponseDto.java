package com.splot.carservice.dto.response;

import lombok.Data;
import java.util.List;

@Data
public class MechanicResponseDto {
    private Long id;
    private String fullName;
    private List<Long> completeOrderIds;
}
