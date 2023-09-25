package com.inditex.testjava2020.esinditexrates.repository;

import com.inditex.testjava2020.esinditexrates.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface IRateRepository extends JpaRepository<Rate, Long> {

    @Query("SELECT r " +
            "FROM Rate r " +
            "where r.BrandId = :brand_id and r.ProductId = :product_id and :application_date between r.StartDate and r.EndDate " +
            "order by r.Priority desc " +
            "limit 1")
    Rate findByStartDateAndEndDateAndBrandIdAndProductId(@Param("brand_id") long brandId,
                                                         @Param("product_id") long productId,
                                                         @Param("application_date") LocalDateTime applicationDate);
}
