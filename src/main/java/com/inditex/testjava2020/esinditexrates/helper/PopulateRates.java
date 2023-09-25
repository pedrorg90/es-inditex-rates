package com.inditex.testjava2020.esinditexrates.helper;

import com.inditex.testjava2020.esinditexrates.entity.Rate;
import com.inditex.testjava2020.esinditexrates.repository.IRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class PopulateRates {

    private static final String DATE_FORMAT = "yyyy-MM-dd-HH.mm.ss";

    @Autowired
    IRateRepository repository;

    DateTimeFormatter dateTimeFormatter =  DateTimeFormatter.ofPattern(DATE_FORMAT);

    /**
     * The goal of this function is to populate rate table in h2
     */
    @EventListener(ContextRefreshedEvent.class)
    public void populate(){
        repository.save(new Rate(1,1,35455, LocalDateTime.parse("2020-06-14-00.00.00", dateTimeFormatter),LocalDateTime.parse("2020-12-31-23.59.59", dateTimeFormatter), 0,35.50,"EUR"));
        repository.save(new Rate(1,2,35455,LocalDateTime.parse("2020-06-14-15.00.00", dateTimeFormatter),LocalDateTime.parse("2020-06-14-18.30.00", dateTimeFormatter), 1,25.45,"EUR"));
        repository.save(new Rate(1,3,35455,LocalDateTime.parse("2020-06-15-00.00.00", dateTimeFormatter),LocalDateTime.parse("2020-06-15-11.00.00", dateTimeFormatter), 1,30.50,"EUR"));
        repository.save(new Rate(1,4,35455,LocalDateTime.parse("2020-06-15-16.00.00", dateTimeFormatter),LocalDateTime.parse("2020-12-31-23.59.59", dateTimeFormatter), 1,38.95,"EUR"));
    }
}
