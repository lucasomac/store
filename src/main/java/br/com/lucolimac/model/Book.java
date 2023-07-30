package br.com.lucolimac.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class Book extends Product {
    private String author;
    private Integer numberOfPages;

    public Book(String author, Integer numberOfPages) {
        this.author = author;
        this.numberOfPages = numberOfPages;
    }

    public Book() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }
}
