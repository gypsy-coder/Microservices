package com.assignment.serviceone.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice(basePackages = "com.assignment.serviceone")
public class ControllerExceptionHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e){
        Class<?> type = e.getRequiredType();
        String message;
        if(type.isEnum()){
            message = "The parameter "+ e.getName() +" must have a value among : "+ Arrays.stream(type.getEnumConstants()).map(x->x.toString()).collect(Collectors.joining(","));
        }else{
            message = "The parameter "+e.getName() +" must be of type "+ type.getTypeName();
        }
        return new ResponseEntity<Object>(message, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<String> details = new ArrayList<>();
        e.getBindingResult().getAllErrors().forEach(error -> details.add(error.getDefaultMessage()));
        return new ResponseEntity<Object>(details, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException e){
        return new ResponseEntity<Object>(e.getLocalizedMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
