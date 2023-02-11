package com.splot.carservice.dto.request;

import com.splot.carservice.model.Favor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavorRequestDto {
    private Long orderId;
    private Long mechanicId;
    private Double cost;
    private Favor.StatusName status;
}
