package com.assignment.service.two.utility;

import com.assignment.service.two.model.DataInDto;
import com.assignment.service.two.model.Persons;
import com.assignment.serviceone.PersonProto;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class XMLHelper {

    public static void writeToXML(DataInDto person){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Persons.class);

            Unmarshaller unMarshaller = jaxbContext.createUnmarshaller();
            File xml = new File("persons.xml");
            Persons persons = new Persons();
            boolean isUpdate = false;

            if(xml.length() > 0){
               persons = (Persons) unMarshaller.unmarshal(xml);
            }

            if(persons.getPerson() != null && persons.getPerson().size() > 0){
                for(DataInDto data : persons.getPerson()){
                    System.out.println("from XML:"+ data);
                    if(data.getName().equalsIgnoreCase(person.getName())){
                        isUpdate = true;
                        data.setAge(person.getAge());
                        data.setDob(person.getDob());
                        data.setSalary(person.getSalary());
                    }
                }
            }else{
                persons.setPerson(new ArrayList<DataInDto>());
            }

            if(!isUpdate){
                persons.getPerson().add(person);
            }


            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            marshaller.marshal(persons, new File("persons.xml"));
            marshaller.marshal(persons, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static List<DataInDto> readFromXML(){
        List<DataInDto> dataInDtoList = new ArrayList<>();
        JAXBContext jaxbContext = null;
        File xml = new File("persons.xml");
        Persons persons = new Persons();
        try {
            jaxbContext = JAXBContext.newInstance(Persons.class);
            Unmarshaller unMarshaller = jaxbContext.createUnmarshaller();
            if(xml.length() > 0){
                persons = (Persons) unMarshaller.unmarshal(xml);
                if(persons.getPerson() != null && persons.getPerson().size() > 0) {
                    dataInDtoList.addAll(persons.getPerson());
                }
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return dataInDtoList;
    }
}
