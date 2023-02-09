package com.splot.carservice.dto.response;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponseDto {
    private Long id;
    private Long carId;
    private String problemDescription;
    private LocalDateTime acceptDate;
    private List<Long> favorIds;
    private List<Long> componentIds;
    private String status;
    private Double finalCost;
    private LocalDateTime completeDate;
}
