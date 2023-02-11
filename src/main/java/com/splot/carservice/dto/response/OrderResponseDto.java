package com.splot.carservice.dto.response;

import com.splot.carservice.model.Order;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponseDto {
    private Long id;
    private Long carId;
    private String problemDescription;
    private LocalDateTime acceptDate;
    private List<Long> favorIds;
    private List<Long> productIds;
    private Order.StatusName status;
    private Double price;
    private LocalDateTime completeDate;
}
