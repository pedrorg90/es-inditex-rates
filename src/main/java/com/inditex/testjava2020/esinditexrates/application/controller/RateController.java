package com.inditex.testjava2020.esinditexrates.application.controller;

import com.inditex.testjava2020.esinditexrates.application.dto.RateRequestDto;
import com.inditex.testjava2020.esinditexrates.application.dto.RateResponseDto;
import com.inditex.testjava2020.esinditexrates.application.mapper.RateMapper;
import com.inditex.testjava2020.esinditexrates.domain.service.IRateService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/rates")
public class RateController {

    Logger logger = LoggerFactory.getLogger(getClass());

    private final IRateService rateService;

    private final RateMapper rateMapper;

    public RateController(IRateService rateService, RateMapper rateMapper) {
        this.rateService = rateService;
        this.rateMapper = rateMapper;
    }

    @Operation(summary = "Gets the top rate of product/brand pair, given its application date", description = "The application date format must be yyyy-MM-dd-HH.mm.ss")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = RateResponseDto.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Bad request", content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Not found", content = { @Content(mediaType = "application/json") })
    })
    @RequestMapping(value="/top", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RateResponseDto> top(@Valid RateRequestDto rateRequestDto){
        logger.info("Controller /top with request: {}",rateRequestDto);
        try {
            RateResponseDto rateResponseDto = rateMapper.toResponseDto(rateService.getRateByApplicationDate(rateRequestDto));
            if (Objects.isNull(rateResponseDto)) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(rateResponseDto);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
