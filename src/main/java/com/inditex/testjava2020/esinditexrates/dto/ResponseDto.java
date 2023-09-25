package com.inditex.testjava2020.esinditexrates.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto {
    private int internalCode;

    private String message;

    private Object data;

    public ResponseDto(int internalCode, String message){
        this.internalCode = internalCode;
        this.message = message;
        this.data = null;
    }
}
