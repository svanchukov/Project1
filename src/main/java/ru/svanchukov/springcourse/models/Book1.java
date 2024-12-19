package ru.svanchukov.springcourse.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Book1 {

    private int id;

    @NotEmpty(message = "The name of book can't be empty")
    @Size(min = 2, max = 100, message = "The name of book must be greater than 2 and lower than 100")
    private String title;

    @NotEmpty(message = "Author can't be empty")
    @Size(min = 2, max = 100, message = "The name of Author must be greater than 2 and lower than 100")
    private String author;

    @Min(value = 1500, message = "Year must be greater than 1500")
    private int year;

    public Book1() {
    }

    public Book1(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
