package com.inditex.testjava2020.esinditexrates.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.inditex.testjava2020.esinditexrates.constant.InternalResponseConstant;
import com.inditex.testjava2020.esinditexrates.dto.RateRequestDto;
import com.inditex.testjava2020.esinditexrates.dto.RateResponseDto;
import com.inditex.testjava2020.esinditexrates.dto.ResponseDto;
import com.inditex.testjava2020.esinditexrates.service.IRateService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebMvcTest
public class RateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IRateService rateService;

    static DateTimeFormatter dateTimeFormatter;


    @BeforeAll
    static void beforeAll(){
        dateTimeFormatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
    }

    @Nested
    class isCorrectRateTests {

        @Test
        public void givenApplicationDateBrandIdProductIdCase1_WhenGetTopRate_ThenReturnCorrectRate() throws Exception {
            //given
            RateRequestDto rateRequestDto = new RateRequestDto("2020-06-14-10.00.00", 35455L, 1L);
            RateResponseDto rateResponseDto = new RateResponseDto(1, 35455, 1, LocalDateTime.parse("2020-06-14-00.00.00", dateTimeFormatter), LocalDateTime.parse("2020-12-31-23.59.59", dateTimeFormatter), 35.50);
            ResponseDto responseDto = rateResponseDto.getResponse(InternalResponseConstant.INTERNAL_CODE_OK, InternalResponseConstant.MESSAGE_OK);

            given(rateService.getRateByApplicationDate(rateRequestDto)).willReturn(responseDto);

            //when
            ResultActions response = mockMvc.perform(get("/rates/top?date=2020-06-14-10.00.00&brandId=1&productId=35455", rateRequestDto));

            //then
            response.andExpect(status().isOk())
                    .andDo(print())
                    .andExpect(jsonPath("$.internalCode").value(responseDto.getInternalCode()))
                    .andExpect(jsonPath("$.data.brandId").value(rateResponseDto.getBrandId()))
                    .andExpect(jsonPath("$.data.productId").value(rateResponseDto.getProductId()))
                    .andExpect(jsonPath("$.data.priceList").value(rateResponseDto.getPriceList()))
                    .andExpect(jsonPath("$.data.startDate").value(rateResponseDto.getStartDate().format(dateTimeFormatter)))
                    .andExpect(jsonPath("$.data.endDate").value(rateResponseDto.getEndDate().format(dateTimeFormatter)))
                    .andExpect(jsonPath("$.data.price").value(rateResponseDto.getPrice()));


        }

        @Test
        public void givenApplicationDateBrandIdProductIdCase2_WhenGetTopRate_ThenReturnCorrectRate() throws Exception {
            //given
            RateRequestDto rateRequestDto = new RateRequestDto("2020-06-14-16.00.00", 35455L, 1L);
            RateResponseDto rateResponseDto = new RateResponseDto(1, 35455, 2, LocalDateTime.parse("2020-06-14-15.00.00", dateTimeFormatter), LocalDateTime.parse("2020-06-14-18.30.00", dateTimeFormatter), 25.45);
            ResponseDto responseDto = rateResponseDto.getResponse(InternalResponseConstant.INTERNAL_CODE_OK, InternalResponseConstant.MESSAGE_OK);

            given(rateService.getRateByApplicationDate(rateRequestDto)).willReturn(responseDto);

            //when
            ResultActions response = mockMvc.perform(get("/rates/top?date=2020-06-14-16.00.00&brandId=1&productId=35455", rateRequestDto));

            //then
            response.andExpect(status().isOk())
                    .andDo(print())
                    .andExpect(jsonPath("$.internalCode").value(responseDto.getInternalCode()))
                    .andExpect(jsonPath("$.data.brandId").value(rateResponseDto.getBrandId()))
                    .andExpect(jsonPath("$.data.productId").value(rateResponseDto.getProductId()))
                    .andExpect(jsonPath("$.data.priceList").value(rateResponseDto.getPriceList()))
                    .andExpect(jsonPath("$.data.startDate").value(rateResponseDto.getStartDate().format(dateTimeFormatter)))
                    .andExpect(jsonPath("$.data.endDate").value(rateResponseDto.getEndDate().format(dateTimeFormatter)))
                    .andExpect(jsonPath("$.data.price").value(rateResponseDto.getPrice()));
        }

        @Test
        public void givenApplicationDateBrandIdProductIdCase3_WhenGetTopRate_ThenReturnCorrectRate() throws Exception {
            //given
            RateRequestDto rateRequestDto = new RateRequestDto("2020-06-14-21.00.00", 35455L, 1L);
            RateResponseDto rateResponseDto = new RateResponseDto(1, 35455, 1, LocalDateTime.parse("2020-06-14-00.00.00", dateTimeFormatter), LocalDateTime.parse("2020-12-31-23.59.59", dateTimeFormatter), 35.50);
            ResponseDto responseDto = rateResponseDto.getResponse(InternalResponseConstant.INTERNAL_CODE_OK, InternalResponseConstant.MESSAGE_OK);

            given(rateService.getRateByApplicationDate(rateRequestDto)).willReturn(responseDto);

            //when
            ResultActions response = mockMvc.perform(get("/rates/top?date=2020-06-14-21.00.00&brandId=1&productId=35455", rateRequestDto));

            //then
            response.andExpect(status().isOk())
                    .andDo(print())
                    .andExpect(jsonPath("$.internalCode").value(responseDto.getInternalCode()))
                    .andExpect(jsonPath("$.data.brandId").value(rateResponseDto.getBrandId()))
                    .andExpect(jsonPath("$.data.productId").value(rateResponseDto.getProductId()))
                    .andExpect(jsonPath("$.data.priceList").value(rateResponseDto.getPriceList()))
                    .andExpect(jsonPath("$.data.startDate").value(rateResponseDto.getStartDate().format(dateTimeFormatter)))
                    .andExpect(jsonPath("$.data.endDate").value(rateResponseDto.getEndDate().format(dateTimeFormatter)))
                    .andExpect(jsonPath("$.data.price").value(rateResponseDto.getPrice()));
        }

        @Test
        public void givenApplicationDateBrandIdProductIdCase4_WhenGetTopRate_ThenReturnCorrectRate() throws Exception {
            //given
            RateRequestDto rateRequestDto = new RateRequestDto("2020-06-15-10.00.00", 35455L, 1L);
            RateResponseDto rateResponseDto = new RateResponseDto(1, 35455, 3, LocalDateTime.parse("2020-06-15-00.00.00", dateTimeFormatter), LocalDateTime.parse("2020-06-15-11.00.00", dateTimeFormatter), 30.50);
            ResponseDto responseDto = rateResponseDto.getResponse(InternalResponseConstant.INTERNAL_CODE_OK, InternalResponseConstant.MESSAGE_OK);

            given(rateService.getRateByApplicationDate(rateRequestDto)).willReturn(responseDto);

            //when
            ResultActions response = mockMvc.perform(get("/rates/top?date=2020-06-15-10.00.00&brandId=1&productId=35455", rateRequestDto));

            //then
            response.andExpect(status().isOk())
                    .andDo(print())
                    .andExpect(jsonPath("$.internalCode").value(responseDto.getInternalCode()))
                    .andExpect(jsonPath("$.data.brandId").value(rateResponseDto.getBrandId()))
                    .andExpect(jsonPath("$.data.productId").value(rateResponseDto.getProductId()))
                    .andExpect(jsonPath("$.data.priceList").value(rateResponseDto.getPriceList()))
                    .andExpect(jsonPath("$.data.startDate").value(rateResponseDto.getStartDate().format(dateTimeFormatter)))
                    .andExpect(jsonPath("$.data.endDate").value(rateResponseDto.getEndDate().format(dateTimeFormatter)))
                    .andExpect(jsonPath("$.data.price").value(rateResponseDto.getPrice()));
        }

        @Test
        public void givenApplicationDateBrandIdProductIdCase5_WhenGetTopRate_ThenReturnCorrectRate() throws Exception {
            //given
            RateRequestDto rateRequestDto = new RateRequestDto("2020-06-16-21.00.00", 35455L, 1L);
            RateResponseDto rateResponseDto = new RateResponseDto(1, 35455, 4, LocalDateTime.parse("2020-06-15-16.00.00", dateTimeFormatter), LocalDateTime.parse("2020-12-31-23.59.59", dateTimeFormatter), 38.95);
            ResponseDto responseDto = rateResponseDto.getResponse(InternalResponseConstant.INTERNAL_CODE_OK, InternalResponseConstant.MESSAGE_OK);

            given(rateService.getRateByApplicationDate(rateRequestDto)).willReturn(responseDto);

            //when
            ResultActions response = mockMvc.perform(get("/rates/top?date=2020-06-16-21.00.00&brandId=1&productId=35455", rateRequestDto));

            //then
            response.andExpect(status().isOk())
                    .andDo(print())
                    .andExpect(jsonPath("$.internalCode").value(responseDto.getInternalCode()))
                    .andExpect(jsonPath("$.data.brandId").value(rateResponseDto.getBrandId()))
                    .andExpect(jsonPath("$.data.productId").value(rateResponseDto.getProductId()))
                    .andExpect(jsonPath("$.data.priceList").value(rateResponseDto.getPriceList()))
                    .andExpect(jsonPath("$.data.startDate").value(rateResponseDto.getStartDate().format(dateTimeFormatter)))
                    .andExpect(jsonPath("$.data.endDate").value(rateResponseDto.getEndDate().format(dateTimeFormatter)))
                    .andExpect(jsonPath("$.data.price").value(rateResponseDto.getPrice()));
        }
    }

    @Nested
    class isWrongCases{

        @Test
        public void givenWrongDateFormat_WhenGetTopRate_ThenReturnInputNotValid() throws Exception {
            //given
            RateRequestDto rateRequestDto = new RateRequestDto("2020-06-16 21:00:00",35455L,1L);
            ResponseDto responseDto = new ResponseDto(InternalResponseConstant.INTERNAL_CODE_INVALID_INPUT,InternalResponseConstant.MESSAGE_DATE_FORMAT_ERROR);
            given(rateService.getRateByApplicationDate(rateRequestDto)).willReturn(responseDto);

            //when
            ResultActions response = mockMvc.perform(get("/rates/top?date=2020-06-16 21:00:00&brandId=1&productId=35455",rateRequestDto));
            //then
            response.andExpect(status().isOk())
                    .andDo(print())
                    .andExpect(jsonPath("$.internalCode").value(InternalResponseConstant.INTERNAL_CODE_INVALID_INPUT))
                    .andExpect(jsonPath("$.message").value(InternalResponseConstant.MESSAGE_DATE_FORMAT_ERROR));
        }

        @Test
        public void givenWrongApplicationDate_WhenGetTopRate_ThenReturnNotFound() throws Exception {
            //given
            RateRequestDto rateRequestDto = new RateRequestDto("2024-06-16-21.00.00",35455L,1L);
            ResponseDto responseDto = new ResponseDto(InternalResponseConstant.INTERNAL_CODE_NOT_FOUND,InternalResponseConstant.MESSAGE_NOT_FOUND);
            given(rateService.getRateByApplicationDate(rateRequestDto)).willReturn(responseDto);

            //when
            ResultActions response = mockMvc.perform(get("/rates/top?date=2024-06-16-21.00.00&brandId=1&productId=35455",rateRequestDto));
            //then
            response.andExpect(status().isOk())
                    .andDo(print())
                    .andExpect(jsonPath("$.internalCode").value(InternalResponseConstant.INTERNAL_CODE_NOT_FOUND))
                    .andExpect(jsonPath("$.message").value(InternalResponseConstant.MESSAGE_NOT_FOUND));
        }

        @Test
        public void givenWrongProductId_WhenGetTopRate_ThenReturnNotFound() throws Exception {
            //given
            RateRequestDto rateRequestDto = new RateRequestDto("2020-06-16-21.00.00",1111L,1L);
            ResponseDto responseDto = new ResponseDto(InternalResponseConstant.INTERNAL_CODE_NOT_FOUND,InternalResponseConstant.MESSAGE_NOT_FOUND);
            given(rateService.getRateByApplicationDate(rateRequestDto)).willReturn(responseDto);

            //when
            ResultActions response = mockMvc.perform(get("/rates/top?date=2020-06-16-21.00.00&brandId=1&productId=1111",rateRequestDto));
            //then
            response.andExpect(status().isOk())
                    .andDo(print())
                    .andExpect(jsonPath("$.internalCode").value(InternalResponseConstant.INTERNAL_CODE_NOT_FOUND))
                    .andExpect(jsonPath("$.message").value(InternalResponseConstant.MESSAGE_NOT_FOUND));
        }

        @Test
        public void givenWrongBrandId_WhenGetTopRate_ThenReturnNotFound() throws Exception {
            //given
            RateRequestDto rateRequestDto = new RateRequestDto("2020-06-16-21.00.00",35455L,11L);
            ResponseDto responseDto = new ResponseDto(InternalResponseConstant.INTERNAL_CODE_NOT_FOUND,InternalResponseConstant.MESSAGE_NOT_FOUND);
            given(rateService.getRateByApplicationDate(rateRequestDto)).willReturn(responseDto);

            //when
            ResultActions response = mockMvc.perform(get("/rates/top?date=2020-06-16-21.00.00&brandId=11&productId=35455",rateRequestDto));
            //then
            response.andExpect(status().isOk())
                    .andDo(print())
                    .andExpect(jsonPath("$.internalCode").value(InternalResponseConstant.INTERNAL_CODE_NOT_FOUND))
                    .andExpect(jsonPath("$.message").value(InternalResponseConstant.MESSAGE_NOT_FOUND));
        }

        @Test
        public void givenMissingDate_WhenGetTopRate_ThenReturnBadRequest() throws Exception {
            //given
            RateRequestDto rateRequestDto = new RateRequestDto(null,35455L,1L);
            ResponseDto responseDto = new ResponseDto(InternalResponseConstant.INTERNAL_CODE_NOT_FOUND,InternalResponseConstant.MESSAGE_NOT_FOUND);
            given(rateService.getRateByApplicationDate(rateRequestDto)).willReturn(responseDto);

            //when
            ResultActions response = mockMvc.perform(get("/rates/top?brandId=11&productId=35455",rateRequestDto));
            //then
            response.andExpect(status().is4xxClientError());
        }

        @Test
        public void givenMissingBrandId_WhenGetTopRate_ThenReturnBadRequest() throws Exception {
            //given
            RateRequestDto rateRequestDto = new RateRequestDto("2020-06-16-21.00.00",35455L,null);
            //when
            ResultActions response = mockMvc.perform(get("/rates/top?date=2020-06-16-21.00.00&productId=35455",rateRequestDto));
            //then
            response.andExpect(status().is4xxClientError());
        }

        @Test
        public void givenMissingProductId_WhenGetTopRate_ThenReturnBadRequest() throws Exception {
            //given
            RateRequestDto rateRequestDto = new RateRequestDto("2020-06-16-21.00.00",null,1L);
            //when
            ResultActions response = mockMvc.perform(get("/rates/top?date=2020-06-16-21.00.00&brandId=1",rateRequestDto));
            //then
            response.andExpect(status().is4xxClientError());
        }
    }


}
