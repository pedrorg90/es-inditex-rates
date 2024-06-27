package com.inditex.testjava2020.esinditexrates.domain.repository;

import com.inditex.testjava2020.esinditexrates.domain.model.Rate;

import java.time.LocalDateTime;

public interface IRateRepository {
    Rate findByStartDateAndEndDateAndBrandIdAndProductId(long brandId, long productId, LocalDateTime applicationDate);

    Rate save(Rate rate);
}
