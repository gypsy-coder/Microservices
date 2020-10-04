package com.assignment.service.two.utility;

import com.assignment.service.two.model.DataInDto;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CSVHelper {

    private static final String CSV_SEPARATOR = ",";
    public static void writeToCSV(DataInDto person)
    {
        try
        {
            File inputFile = new File("person.csv");
            CSVReader reader = new CSVReader(new FileReader(inputFile));
            List<String[]> csvBody = reader.readAll();
            boolean isUpdate = false;
            int totalRows = csvBody.size();
            String[] newRow = new String[4];

            for(int i=0 ; i < totalRows; ++i) {
                if (csvBody.get(i)[0].equalsIgnoreCase(person.getName().trim())) {
                    csvBody.get(i)[1] = String.valueOf(person.getAge());
                    csvBody.get(i)[2] = String.valueOf(person.getSalary());
                    csvBody.get(i)[3] = person.getDob().trim();
                    isUpdate = true;
                }
            }

            if(!isUpdate){
                newRow[0] = person.getName().trim();
                newRow[1] = String.valueOf(person.getAge());
                newRow[2]  = String.valueOf(person.getSalary());
                newRow[3]  = person.getDob().trim();
                csvBody.add(newRow);
            }

            reader.close();
            CSVWriter writer = new CSVWriter(new FileWriter(inputFile));
            writer.writeAll(csvBody);
            writer.flush();
            writer.close();
        }
        catch (UnsupportedEncodingException e) {}
        catch (FileNotFoundException e){}
        catch (IOException e){} catch (CsvException e) {
            e.printStackTrace();
        }
    }

    public static List<DataInDto> readFromCSV(){
        List<DataInDto> dataInDtoList = new ArrayList<>();
        try {
            File inputFile = new File("person.csv");
            CSVReader reader = new CSVReader(new FileReader(inputFile));
            List<String[]> csvBody = reader.readAll();
            int totalRows = csvBody.size();
            dataInDtoList = csvBody.stream()
                    .map(row -> new DataInDto(row[0], Integer.parseInt(row[1]), Double.parseDouble(row[2]), row[3]))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }

        return dataInDtoList;
    }
}
