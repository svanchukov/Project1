package ru.svanchukov.springcourse.models;

import jakarta.validation.constraints.*;

public class Person {

    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 100, message = "Name should be between 2 and 30 characters")
    private String fullName;

    @Min(value = 1990, message = "Age sould be greater than 1990")
    private int yearOfBirth;

    // Конструктор по умолчанию
    public Person() {
    }

    // Конструктор с параметрами id, name, age и email
    public Person(String fullName, int yearOfBirth) {
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotEmpty(message = "Name should not be empty") @Size(min = 2, max = 100, message = "Name should be between 2 and 30 characters") String getFullName() {
        return fullName;
    }

    public void setFullName(@NotEmpty(message = "Name should not be empty") @Size(min = 2, max = 100, message = "Name should be between 2 and 30 characters") String fullName) {
        this.fullName = fullName;
    }

    @Min(value = 1990, message = "Age sould be greater than 0")
    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(@Min(value = 1990, message = "Age sould be greater than 0") int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

}
