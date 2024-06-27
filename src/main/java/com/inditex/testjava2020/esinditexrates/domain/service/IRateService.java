package com.inditex.testjava2020.esinditexrates.domain.service;

import com.inditex.testjava2020.esinditexrates.application.dto.RateRequestDto;
import com.inditex.testjava2020.esinditexrates.domain.model.Rate;

public interface IRateService {
    public Rate getRateByApplicationDate(RateRequestDto rateRequestDto) throws Exception;
}
