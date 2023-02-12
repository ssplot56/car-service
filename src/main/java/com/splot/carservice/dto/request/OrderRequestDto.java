package com.splot.carservice.dto.request;

import com.splot.carservice.model.Order;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestDto {
    private Long carId;
    private String problemDescription;
    private List<Long> productIds;
    private List<Long> favorIds;
    private Order.StatusName status;
}
