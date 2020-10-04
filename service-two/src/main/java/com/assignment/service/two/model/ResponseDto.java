package com.assignment.service.two.model;

import java.util.List;

public class ResponseDto {

    private List<DataInDto> dataInDtoList;

    public ResponseDto() {
    }

    public ResponseDto(List<DataInDto> dataInDtoList) {
        this.dataInDtoList = dataInDtoList;
    }

    public List<DataInDto> getDataInDtoList() {
        return dataInDtoList;
    }

    public void setDataInDtoList(List<DataInDto> dataInDtoList) {
        this.dataInDtoList = dataInDtoList;
    }
}
