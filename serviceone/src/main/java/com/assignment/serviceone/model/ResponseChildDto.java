package com.assignment.serviceone.model;
import java.time.LocalDate;

public class ResponseChildDto {

    private String name;
    private String dob;
    private double salary;
    private int age;

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
        return "ResponseChildDto{" +
                "name='" + name + '\'' +
                ", dob=" + dob +
                ", salary=" + salary +
                ", age=" + age +
                '}';
    }
}

