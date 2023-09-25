package com.inditex.testjava2020.esinditexrates.controller;

import com.inditex.testjava2020.esinditexrates.dto.RateRequestDto;
import com.inditex.testjava2020.esinditexrates.dto.ResponseDto;
import com.inditex.testjava2020.esinditexrates.service.IRateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rates")
public class RateController {

    Logger logger = LoggerFactory.getLogger(getClass());

    private final IRateService rateService;

    @Autowired
    public RateController(IRateService rateService) {
        this.rateService = rateService;
    }

    @Operation(summary = "Gets the top rate of product/brand pair, given its application date", description = "The application date format must be yyyy-MM-dd-HH.mm.ss")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Not found", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class)) })
    })
    @RequestMapping(value="/top", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> top(@Valid RateRequestDto rateRequestDto){
        logger.info("Controller /top with request: {}",rateRequestDto);
        ResponseDto responseDto = rateService.getRateByApplicationDate(rateRequestDto);
        return ResponseEntity.ok(responseDto);
    }
}
