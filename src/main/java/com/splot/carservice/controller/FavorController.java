package com.splot.carservice.controller;

import com.splot.carservice.dto.request.FavorRequestDto;
import com.splot.carservice.dto.request.StatusRequestDto;
import com.splot.carservice.dto.response.FavorResponseDto;
import com.splot.carservice.model.Favor;
import com.splot.carservice.service.FavorService;
import com.splot.carservice.service.mapper.RequestDtoMapper;
import com.splot.carservice.service.mapper.ResponseDtoMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/favors")
public class FavorController {
    private final FavorService favorService;
    private final RequestDtoMapper<FavorRequestDto, Favor> requestMapper;
    private final ResponseDtoMapper<FavorResponseDto, Favor> responseMapper;
    private final RequestDtoMapper<StatusRequestDto, Favor.StatusName> statusRequestMapper;

    public FavorController(FavorService favorService,
                           RequestDtoMapper<FavorRequestDto, Favor> requestMapper,
                           ResponseDtoMapper<FavorResponseDto, Favor> responseMapper,
                           RequestDtoMapper<StatusRequestDto, Favor.StatusName> statusRequestMapper) {
        this.favorService = favorService;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
        this.statusRequestMapper = statusRequestMapper;
    }

    @PostMapping
    @ApiOperation("Add a new favor")
    public FavorResponseDto create(@RequestBody FavorRequestDto requestDto) {
        return responseMapper.mapToDto(favorService
                .save(requestMapper.mapToModel(requestDto)));
    }

    @PutMapping("/{id}")
    @ApiOperation("Update existing favor by id")
    public FavorResponseDto update(@PathVariable Long id,
                                   @RequestBody FavorRequestDto requestDto) {
        Favor favor = requestMapper.mapToModel(requestDto);
        favor.setId(id);
        return responseMapper.mapToDto(favorService.save(favor));
    }

    @PutMapping("/{id}/status")
    @ApiOperation("Update favor status by favor id")
    private FavorResponseDto changeStatus(@PathVariable Long id,
                                          @RequestBody StatusRequestDto requestDto) {
        Favor favor = favorService.getById(id);
        favor.setStatus(statusRequestMapper.mapToModel(requestDto));
        return responseMapper.mapToDto(favorService.save(favor));
    }
}
