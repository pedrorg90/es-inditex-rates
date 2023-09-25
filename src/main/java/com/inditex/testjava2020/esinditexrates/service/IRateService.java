package com.inditex.testjava2020.esinditexrates.service;

import com.inditex.testjava2020.esinditexrates.dto.RateRequestDto;
import com.inditex.testjava2020.esinditexrates.dto.ResponseDto;

public interface IRateService {
    public ResponseDto getRateByApplicationDate(RateRequestDto rateRequestDto);
}
