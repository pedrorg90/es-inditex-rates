package com.inditex.testjava2020.esinditexrates.service;

import com.inditex.testjava2020.esinditexrates.constant.InternalResponseConstant;
import com.inditex.testjava2020.esinditexrates.dto.RateRequestDto;
import com.inditex.testjava2020.esinditexrates.dto.RateResponseDto;
import com.inditex.testjava2020.esinditexrates.dto.ResponseDto;
import com.inditex.testjava2020.esinditexrates.entity.Rate;
import com.inditex.testjava2020.esinditexrates.repository.IRateRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

@Service
public class RateServiceImpl implements IRateService {

    Logger logger = LoggerFactory.getLogger(getClass());
    private static final String DATE_FORMAT = "yyyy-MM-dd-HH.mm.ss";

    @Autowired
    IRateRepository repository;

    @Override
    public ResponseDto getRateByApplicationDate(RateRequestDto rateRequestDto){
        try {
            Rate rate = repository.findByStartDateAndEndDateAndBrandIdAndProductId(
                    rateRequestDto.getBrandId(),
                    rateRequestDto.getProductId(),
                    LocalDateTime.parse(rateRequestDto.getDate(), DateTimeFormatter.ofPattern(DATE_FORMAT)));
            if(Objects.isNull(rate)){
                logger.info("Rate not found for input: {}",rateRequestDto);
                return new ResponseDto(InternalResponseConstant.INTERNAL_CODE_NOT_FOUND,InternalResponseConstant.MESSAGE_NOT_FOUND);
            }
            return new ModelMapper().map(rate, RateResponseDto.class).getResponse(InternalResponseConstant.INTERNAL_CODE_OK, InternalResponseConstant.MESSAGE_OK);
        } catch (DateTimeParseException dateTimeParseException){
            logger.error("DateTimeParseException caused by wrong date format: {}", rateRequestDto,dateTimeParseException);
            return new ResponseDto(InternalResponseConstant.INTERNAL_CODE_INVALID_INPUT,InternalResponseConstant.MESSAGE_DATE_FORMAT_ERROR);
        } catch (Exception exception){
            logger.error("An exception has occurred!: {}",rateRequestDto,exception);
            return new ResponseDto(InternalResponseConstant.INTERNAL_CODE_INTERNAL_ERROR,InternalResponseConstant.MESSAGE_INTERNAL_ERROR);
        }
    }
}
