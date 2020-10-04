package com.assignment.service.two.model;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement(name = "Persons")
public class Persons {

    private List<DataInDto> person;

    public Persons() {
    }

    public Persons(List<DataInDto> person) {
        this.person = person;
    }

    public List<DataInDto> getPerson() {
        return person;
    }

    public void setPerson(List<DataInDto> person) {
        this.person = person;
    }
}
