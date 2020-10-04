package com.assignment.service.two.config;

import com.assignment.service.two.model.DataInDto;
import com.assignment.service.two.utility.CSVHelper;
import com.assignment.service.two.utility.XMLHelper;
import com.assignment.serviceone.PersonProto;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class Receiver {

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(PersonProto.Person message){

        DataInDto dataInDto = new DataInDto(message);
        System.out.println("Received <" + dataInDto + ">");
        System.out.println("FileType:"+message.getSaveType().getFileType().toString());
        if(message.getSaveType().getFileType().toString().equalsIgnoreCase("CSV")){
            CSVHelper.writeToCSV(dataInDto);
        }else{
            XMLHelper.writeToXML(dataInDto);
        }

        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
