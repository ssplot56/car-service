package com.splot.carservice.dto.response;

import com.splot.carservice.model.Favor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavorResponseDto {
    private Long id;
    private Long orderId;
    private Long mechanicId;
    private Double cost;
    private Favor.StatusName status;
}
