package com.assignment.serviceone.service;

import com.assignment.serviceone.model.DataInDto;
import com.assignment.serviceone.utility.ServiceOneConstants;

import javax.validation.Valid;

public interface MessageService {

    void sendMessage(@Valid DataInDto data, ServiceOneConstants.FILE_TYPE fileType);
}
