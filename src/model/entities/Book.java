package model.entities;

import model.enums.BookStatus;

import java.time.LocalDateTime;
import java.util.Objects;

public class Book {

    private Integer id;
    private String title;
    private Author author;
    private BookStatus status;
    private LocalDateTime registrationDate;
    private LocalDateTime updateDate;

    public Book () {}

    public Book(Integer id, String title, Author author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.status = BookStatus.AVAILABLE;
        this.registrationDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
    }

    public Integer getId() {

        return id;

    }

    public void setId(Integer id) {

        this.id = id;

    }

    public String getTitle() {

        return title;

    }

    public void setTitle(String title) {

        this.title = title;

    }

    public Author getAuthor() {

        return author;

    }

    public void setAuthor(Author author) {

        this.author = author;

    }

    public BookStatus getStatus() {

        return status;

    }

    public void setStatus(BookStatus status) {

        this.status = status;

    }

    public LocalDateTime getRegistrationDate() {

        return registrationDate;

    }

    public void setRegistrationDate(LocalDateTime registrationDate) {

        this.registrationDate = registrationDate;

    }

    public LocalDateTime getUpdateDate() {

        return updateDate;

    }

    public void setUpdateDate(LocalDateTime updateDate) {

        this.updateDate = updateDate;

    }

    @Override
    public boolean equals(Object object) {

        if (object == null || getClass() != object.getClass()) return false;

        Book book = (Book) object;

        return Objects.equals(id, book.id) && Objects.equals(title, book.title) && Objects.equals(author, book.author) && status == book.status;

    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, author, status);

    }

    @Override
    public String toString() {

        return getId() + " | "
                + getTitle() + " | "
                + getStatus() + " | "
                + "Author: "
                + getAuthor().getId()
                + " - "
                + getAuthor().getName();
    }

}
