package com.assignment.serviceone.model;


import com.assignment.serviceone.validation.Adult;
import com.assignment.serviceone.validation.ParseDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class DataInDto {

    @NotNull
    @Size(min=1, max = 100, message = "Name should not have more than 100 chars")
    private String name;

    @NotNull
    @Past
    @Adult
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = ParseDeserializer.class)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dob;

    @DecimalMin(value = "0.0", message = "Salary must be greater than or equal to 0.0")
    private double salary;

    @NotNull
    @Min(value = 18, message = "Age can't be less than 18")
    @Max(value = 120, message = "Age can't be greater than 120")
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
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
