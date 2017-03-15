package com.epam.library.domain;

import java.time.LocalDate;

/**
 * Created by 123 on 14.03.2017.
 */
public class Book extends Entity{
    private int bookId;
    private String title;
    private String author;
    private String brief;
    private int dateOfPublishing;

    public Book(){
    }

    public Book(String title, String author, String brief, int dateOfPublishing) {
        this.title = title;
        this.author = author;
        this.brief = brief;
        this.dateOfPublishing = dateOfPublishing;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
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

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public int getDateOfPublishing() {
        return dateOfPublishing;
    }

    public void setDateOfPublishing(int dateOfPublishing) {
        this.dateOfPublishing = dateOfPublishing;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", brief='" + brief + '\'' +
                ", dateOfPublishing=" + dateOfPublishing +
                '}';
    }
}
