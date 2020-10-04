package com.assignment.service.two.service;

import com.assignment.service.two.model.DataInDto;
import com.assignment.serviceone.PersonProto;

import java.util.List;

public interface ReadService {
    List<DataInDto> readAll();
}
