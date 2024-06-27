package com.inditex.testjava2020.esinditexrates.application.mapper;

import com.inditex.testjava2020.esinditexrates.application.dto.RateResponseDto;
import com.inditex.testjava2020.esinditexrates.domain.model.Rate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RateMapper {

    @Mapping(target = "brandId", source = "brandId")
    @Mapping(target = "productId", source = "productId")
    @Mapping(target = "priceList", source = "priceList")
    @Mapping(target = "startDate", source = "startDate")
    @Mapping(target = "endDate", source = "endDate")
    @Mapping(target = "price", source = "price")
    RateResponseDto toResponseDto(Rate rate);
}
