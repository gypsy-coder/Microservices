package com.assignment.serviceone.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResponseDto {

    @JsonProperty("dataInDtoList")
    private List<ResponseChildDto> responseChildDtoList;

    public ResponseDto() {
    }

    public ResponseDto(List<ResponseChildDto> responseChildDtoList) {
        this.responseChildDtoList = responseChildDtoList;
    }

    public List<ResponseChildDto> getResponseChildDtoList() {
        return responseChildDtoList;
    }

    public void setResponseChildDtoList(List<ResponseChildDto> responseChildDtoList) {
        this.responseChildDtoList = responseChildDtoList;
    }

    @Override
    public String toString() {
        return "ResponseDto{" +
                "responseChildDtoList=" + responseChildDtoList +
                '}';
    }
}
