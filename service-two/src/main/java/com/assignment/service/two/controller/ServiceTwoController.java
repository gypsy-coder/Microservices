package com.assignment.service.two.controller;


import com.assignment.service.two.model.DataInDto;
import com.assignment.service.two.model.ResponseDto;
import com.assignment.service.two.service.ReadService;
import com.assignment.serviceone.PersonProto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ServiceTwoController {

    @Autowired
    ReadService readService;

    @RequestMapping(value = "/read/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> readAll(){
        List<DataInDto> dataInDtoList = readService.readAll();
        return new ResponseEntity<ResponseDto>(new ResponseDto(dataInDtoList), HttpStatus.OK);
    }
}
