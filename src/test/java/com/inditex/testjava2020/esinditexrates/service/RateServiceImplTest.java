package com.inditex.testjava2020.esinditexrates.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import com.inditex.testjava2020.esinditexrates.constant.InternalResponseConstant;
import com.inditex.testjava2020.esinditexrates.dto.RateRequestDto;
import com.inditex.testjava2020.esinditexrates.dto.RateResponseDto;
import com.inditex.testjava2020.esinditexrates.dto.ResponseDto;
import com.inditex.testjava2020.esinditexrates.entity.Rate;
import com.inditex.testjava2020.esinditexrates.repository.IRateRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ExtendWith(MockitoExtension.class)
class RateServiceImplTest {

    @Mock
    IRateRepository repositoryMock;
    @InjectMocks
    RateServiceImpl rateService;

    static DateTimeFormatter dateTimeFormatter;

    @BeforeAll
    static void beforeAll(){
        dateTimeFormatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
    }

    @Test
    void givenWrongDateFormat_WhenGetRateByApplicationDate_ThenReturnBadRequest() {
        //given
        RateRequestDto requestDto = new RateRequestDto("2020-06-14 10:00:00",35455L,1L);

        //when
        ResponseDto responseDto = rateService.getRateByApplicationDate(requestDto);

        //then
        assertEquals(responseDto.getInternalCode(), InternalResponseConstant.INTERNAL_CODE_INVALID_INPUT);
    }

    @Test
    void givenNullRequestDto_WhenGetRateByApplicationDate_ThenReturnInternalError() {
        //given
        RateRequestDto requestDto = null;

        //when
        ResponseDto responseDto = rateService.getRateByApplicationDate(requestDto);

        //then
        assertEquals(responseDto.getInternalCode(), InternalResponseConstant.INTERNAL_CODE_INTERNAL_ERROR);
    }

    @Test
    void givenWrongProductId_WhenGetRateByApplicationDate_ThenReturnNotFound() {
        //given
        RateRequestDto requestDto = new RateRequestDto("2020-06-14-10.00.00",1111L,1L);

        //when
        ResponseDto responseDto = rateService.getRateByApplicationDate(requestDto);

        //then
        assertEquals(responseDto.getInternalCode(), InternalResponseConstant.INTERNAL_CODE_NOT_FOUND);
    }

    @Test
    void givenWrongBrandId_WhenGetRateByApplicationDate_ThenReturnNotFound() {
        //given
        RateRequestDto requestDto = new RateRequestDto("2020-06-14-10.00.00",35455L,11L);

        //when
        ResponseDto responseDto = rateService.getRateByApplicationDate(requestDto);

        //then
        assertEquals(responseDto.getInternalCode(), InternalResponseConstant.INTERNAL_CODE_NOT_FOUND);
    }

    @Test
    void givenWrongApplicationDate_WhenGetRateByApplicationDate_ThenReturnNotFound() {
        //given
        RateRequestDto requestDto = new RateRequestDto("2024-06-14-10.00.00",35455L,1L);

        //when
        ResponseDto responseDto = rateService.getRateByApplicationDate(requestDto);

        //then
        assertEquals(responseDto.getInternalCode(), InternalResponseConstant.INTERNAL_CODE_NOT_FOUND);
    }

    @Test
    void givenCorrectApplicationDateProductIdBrandId_WhenGetRateByApplicationDate_ThenReturnCorrectRate() {
        //given
        RateRequestDto requestDto = new RateRequestDto("2020-06-14-10.00.00",35455L,1L);
        Rate rate = new Rate(1,1,35455, LocalDateTime.parse("2020-06-14-00.00.00", dateTimeFormatter),LocalDateTime.parse("2020-12-31-23.59.59", dateTimeFormatter), 0,35.50,"EUR");
        RateResponseDto rateResponseDto = new RateResponseDto(1,35455,1,LocalDateTime.parse("2020-06-14-00.00.00", dateTimeFormatter),LocalDateTime.parse("2020-12-31-23.59.59", dateTimeFormatter),35.50);
        given(repositoryMock.findByStartDateAndEndDateAndBrandIdAndProductId(
                requestDto.getBrandId(),
                requestDto.getProductId(),
                LocalDateTime.parse(requestDto.getDate(), dateTimeFormatter)))
            .willReturn(rate);

        //when
        ResponseDto responseDto = rateService.getRateByApplicationDate(requestDto);

        //then
        assertEquals(responseDto.getInternalCode(), InternalResponseConstant.INTERNAL_CODE_OK);
        assertEquals(responseDto.getData(), rateResponseDto);
    }
}