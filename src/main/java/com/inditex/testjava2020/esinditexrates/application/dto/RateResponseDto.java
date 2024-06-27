package com.inditex.testjava2020.esinditexrates.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RateResponseDto {
    private long brandId;

    private long productId;

    private int priceList;

    @JsonFormat(pattern="yyyy-MM-dd-HH.mm.ss")
    private LocalDateTime startDate;

    @JsonFormat(pattern="yyyy-MM-dd-HH.mm.ss")
    private LocalDateTime endDate;

    private double price;
}
