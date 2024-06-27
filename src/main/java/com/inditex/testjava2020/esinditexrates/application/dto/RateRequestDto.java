package com.inditex.testjava2020.esinditexrates.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = "^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])-\\d{2}\\.\\d{2}\\.\\d{2}$")
    @JsonFormat(pattern="yyyy-MM-dd-HH.mm.ss")
    private String date;
    @NotNull
    private Long productId;
    @NotNull
    private Long brandId;
}
