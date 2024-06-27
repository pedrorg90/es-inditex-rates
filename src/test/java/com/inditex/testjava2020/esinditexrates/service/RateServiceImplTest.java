package com.inditex.testjava2020.esinditexrates.service;

import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.*;
import com.inditex.testjava2020.esinditexrates.application.dto.RateRequestDto;
import com.inditex.testjava2020.esinditexrates.application.dto.RateResponseDto;
import com.inditex.testjava2020.esinditexrates.domain.model.Rate;
import com.inditex.testjava2020.esinditexrates.infrastructure.repository.JpaRateRepository;
import com.inditex.testjava2020.esinditexrates.infrastructure.service.RateServiceImpl;
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
    JpaRateRepository repositoryMock;
    @InjectMocks
    RateServiceImpl rateService;

    static DateTimeFormatter dateTimeFormatter;

    @BeforeAll
    static void beforeAll(){
        dateTimeFormatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
    }

    @Test
    void givenWrongProductId_WhenGetRateByApplicationDate_ThenReturnNotFound() throws Exception {
        //given
        RateRequestDto requestDto = new RateRequestDto("2020-06-14-10.00.00",1111L,1L);

        //when
        Rate rate = rateService.getRateByApplicationDate(requestDto);

        //then
        assertNull(rate);
    }

    @Test
    void givenWrongBrandId_WhenGetRateByApplicationDate_ThenReturnNotFound() throws Exception {
        //given
        RateRequestDto requestDto = new RateRequestDto("2020-06-14-10.00.00",35455L,11L);

        //when
        Rate rate = rateService.getRateByApplicationDate(requestDto);

        //then
        assertNull(rate);
    }

    @Test
    void givenWrongApplicationDate_WhenGetRateByApplicationDate_ThenReturnNotFound() throws Exception {
        //given
        RateRequestDto requestDto = new RateRequestDto("2024-06-14-10.00.00",35455L,1L);

        //when
        Rate rate = rateService.getRateByApplicationDate(requestDto);

        //then
        assertNull(rate);
    }

    @Test
    void givenCorrectApplicationDateProductIdBrandId_WhenGetRateByApplicationDate_ThenReturnCorrectRate() throws Exception{
        //given
        RateRequestDto requestDto = new RateRequestDto("2020-06-14-10.00.00",35455L,1L);
        Rate rateStored = new Rate(1,1,35455, LocalDateTime.parse("2020-06-14-00.00.00", dateTimeFormatter),LocalDateTime.parse("2020-12-31-23.59.59", dateTimeFormatter), 0,35.50,"EUR");
        RateResponseDto rateResponseDto = new RateResponseDto(1,35455,1,LocalDateTime.parse("2020-06-14-00.00.00", dateTimeFormatter),LocalDateTime.parse("2020-12-31-23.59.59", dateTimeFormatter),35.50);
        given(repositoryMock.findByStartDateAndEndDateAndBrandIdAndProductId(
                requestDto.getBrandId(),
                requestDto.getProductId(),
                LocalDateTime.parse(requestDto.getDate(), dateTimeFormatter)))
            .willReturn(rateStored);

        //when
        Rate rate = rateService.getRateByApplicationDate(requestDto);

        //then
        assertEquals(rateStored.getProductId(), rateResponseDto.getProductId());
        assertEquals(rateStored.getBrandId(), rateResponseDto.getBrandId());
        assertEquals(rateStored.getPriceList(), rateResponseDto.getPriceList());
        assertEquals(rateStored.getStartDate(), rateResponseDto.getStartDate());
        assertEquals(rateStored.getEndDate(), rateResponseDto.getEndDate());
    }
}