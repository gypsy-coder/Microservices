package com.assignment.service.two.model;


import com.assignment.serviceone.PersonProto;
import com.opencsv.bean.CsvBindByName;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@XmlRootElement(name = "Person")
public class DataInDto {

    @CsvBindByName
    private String name;

    @CsvBindByName
    private String dob;

    @CsvBindByName
    private double salary;

    @CsvBindByName
    private int age;

    public DataInDto(){}

    public DataInDto(PersonProto.Person person){
        this.name = person.getName();
        this.age = person.getAge();
        this.salary = person.getSalary();
        this.dob  = this.formatDob(person.getDob());
    }

    public DataInDto(String name, int age, double salary, String dob) {
        this.name = name;
        this.dob = dob;
        this.salary = salary;
        this.age = age;
    }

    private String formatDob(String dob){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dt = LocalDate.parse(dob,dtf);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return dt.format(formatter);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "DataInDto{" +
                "name='" + name + '\'' +
                ", dob=" + dob +
                ", salary=" + salary +
                ", age=" + age +
                '}';
    }
}
