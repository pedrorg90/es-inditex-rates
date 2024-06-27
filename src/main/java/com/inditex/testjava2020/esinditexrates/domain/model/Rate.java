package com.inditex.testjava2020.esinditexrates.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@IdClass(RateId.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rate {
    @Id
    private long BrandId;
    @Id
    private int PriceList;
    @Id
    private long ProductId;

    private LocalDateTime StartDate;

    private LocalDateTime EndDate;

    private int Priority;

    private double Price;

    private String curr;
}
