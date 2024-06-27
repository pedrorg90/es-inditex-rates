package com.inditex.testjava2020.esinditexrates.infrastructure.service;

import com.inditex.testjava2020.esinditexrates.domain.repository.IRateRepository;
import com.inditex.testjava2020.esinditexrates.domain.service.IRateService;
import com.inditex.testjava2020.esinditexrates.application.dto.RateRequestDto;
import com.inditex.testjava2020.esinditexrates.domain.model.Rate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Service
public class RateServiceImpl implements IRateService {

    Logger logger = LoggerFactory.getLogger(getClass());
    private static final String DATE_FORMAT = "yyyy-MM-dd-HH.mm.ss";

    @Autowired
    IRateRepository repository;

    @Override
    public Rate getRateByApplicationDate(RateRequestDto rateRequestDto) throws Exception {
        try {
            Rate rate = repository.findByStartDateAndEndDateAndBrandIdAndProductId(
                    rateRequestDto.getBrandId(),
                    rateRequestDto.getProductId(),
                    LocalDateTime.parse(rateRequestDto.getDate(), DateTimeFormatter.ofPattern(DATE_FORMAT)));
            if (Objects.isNull(rate)) {
                logger.info("Rate not found for input: {}",rateRequestDto);
                return null;
            }
            return rate;
        } catch (Exception exception) {
            logger.error("An exception has occurred!: {}",rateRequestDto,exception);
            throw new Exception("An exception has ocurred: ", exception);
        }
    }
}
