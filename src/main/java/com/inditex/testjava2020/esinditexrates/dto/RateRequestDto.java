package com.inditex.testjava2020.esinditexrates.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RateRequestDto {
    @NotEmpty
    private String date;
    @NotNull
    private Long productId;
    @NotNull
    private Long brandId;
}
