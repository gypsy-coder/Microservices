package com.assignment.service.two.service;

import com.assignment.service.two.model.DataInDto;
import com.assignment.service.two.model.Persons;
import com.assignment.service.two.utility.CSVHelper;
import com.assignment.service.two.utility.XMLHelper;
import com.assignment.serviceone.PersonProto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReadServiceImpl implements ReadService {
    @Override
    public List<DataInDto> readAll() {
        List<DataInDto> dataInDtoList = new ArrayList<DataInDto>();
        //Add all from XML
        dataInDtoList.addAll(XMLHelper.readFromXML());
        //Add all from CSV
        dataInDtoList.addAll(CSVHelper.readFromCSV());
        return dataInDtoList;
    }

    private List<PersonProto.Person> buildPersonProtoList(List<DataInDto> dataInDtoList,PersonProto.Person.SaveContent saveContent){
         return dataInDtoList.stream().map(dataDto -> PersonProto.Person.newBuilder()
                .setName(dataDto.getName())
                .setAge(dataDto.getAge())
                .setSalary(dataDto.getSalary())
                .setDob(dataDto.getDob().toString())
                .setSaveType(saveContent)
                .build())
                .collect(Collectors.toList());
    }
}
