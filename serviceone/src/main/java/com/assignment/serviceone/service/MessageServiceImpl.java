package com.assignment.serviceone.service;

import com.assignment.serviceone.PersonProto;
import com.assignment.serviceone.config.RabbitConfiguration;
import com.assignment.serviceone.model.DataInDto;

import com.assignment.serviceone.utility.ServiceOneConstants;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements  MessageService{

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public void sendMessage(DataInDto dataDto, ServiceOneConstants.FILE_TYPE file_type) {

        PersonProto.Person.SaveContent saveContent = PersonProto.Person.SaveContent.newBuilder()
                .setFileType(PersonProto.Person.FileType.CSV)
                .build();

        if(file_type.equals(ServiceOneConstants.FILE_TYPE.XML)){
            saveContent = PersonProto.Person.SaveContent.newBuilder()
                    .setFileType(PersonProto.Person.FileType.XML)
                    .build();
        }

        PersonProto.Person person = PersonProto.Person.newBuilder()
                .setName(dataDto.getName())
                .setAge(dataDto.getAge())
                .setSalary(dataDto.getSalary())
                .setDob(dataDto.getDob().toString())
                .setSaveType(saveContent)
                .build();


        System.out.println("Person proto::"+person.toString());
        rabbitTemplate.convertAndSend(RabbitConfiguration.topicExchange, "foo.bar.baz", person);
    }
}
