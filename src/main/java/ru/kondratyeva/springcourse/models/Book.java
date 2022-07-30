package ru.kondratyeva.springcourse.models;

import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLID;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


public class Book {
    private int id;
    @NotNull(message = "Поле не должно быть пустым")
    private String name;
    @NotNull(message = "Поле не должно быть пустым")
    private String author;
    @Min(value=0,message="Год должен быть больше 0")
    private int year;
    public Book(){

    }

    public Book(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
