package com.inditex.testjava2020.esinditexrates.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RateId implements Serializable {
    private long BrandId;

    private int PriceList;

    private long ProductId;
}