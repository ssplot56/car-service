package com.splot.carservice.service.mapper;

import com.splot.carservice.dto.request.StatusRequestDto;
import com.splot.carservice.model.Favor;
import org.springframework.stereotype.Component;

@Component
public class StatusMapper implements RequestDtoMapper<StatusRequestDto, Favor.StatusName> {
    @Override
    public Favor.StatusName mapToModel(StatusRequestDto dto) {
        return Favor.StatusName.valueOf(dto.getName());
    }
}
