package com.assignment.serviceone.controller;


import com.assignment.serviceone.config.RabbitConfiguration;
import com.assignment.serviceone.model.DataInDto;
import com.assignment.serviceone.model.ResponseDto;
import com.assignment.serviceone.service.MessageService;
import com.assignment.serviceone.utility.ServiceOneConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.ConstraintValidatorContext;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api")
public class ConsumerController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MessageService messageService;

    @Autowired
    private RestTemplate restTemplate;

    private static final String SERVICE_TWO_GET_ALL = "http://localhost:8089/servicetwo/api/read/all";

    @RequestMapping(value = "/read/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> readAll() {
        ResponseDto responseDto = restTemplate.getForObject(SERVICE_TWO_GET_ALL, ResponseDto.class);
        System.out.println("ServiceTwo Output Array::" + responseDto);
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/store")
    public ResponseEntity<String> storeData(@Valid @RequestBody DataInDto data, @RequestParam(name = "fileType", required = true) ServiceOneConstants.FILE_TYPE fileType){
        try {
            if(validateDateAndAge(data)){
                System.out.println("DataDTO in::"+ data);
                messageService.sendMessage(data, fileType);
            }else return new ResponseEntity<String>("Age doesn't match with DOB!", HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            return new ResponseEntity<String>(HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }


    @PutMapping("/update")
    public ResponseEntity<String> updateData(@Valid @RequestBody DataInDto data, @RequestParam(name = "fileType", required = true) ServiceOneConstants.FILE_TYPE fileType){
        try {
            if(validateDateAndAge(data)) {
                messageService.sendMessage(data, fileType);
            } else return new ResponseEntity<String>("Age doesn't match with DOB!", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<String>(HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    private boolean validateDateAndAge(DataInDto data){
            return data.getDob() != null && LocalDate.now().minusYears(data.getAge()).getYear() == data.getDob().getYear();
    }

}
