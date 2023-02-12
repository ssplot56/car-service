package com.splot.carservice.dto.response;

import lombok.Setter;
import lombok.Getter;
import java.util.List;

@Getter
@Setter
public class MechanicResponseDto {
    private Long id;
    private String fullName;
    private List<Long> completeOrderIds;
}
