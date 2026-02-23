package model.dao;

import model.entities.Book;

import java.util.List;

public interface BookDao {

    void insert(Book book);

    void upadate(Book book);

    void deleteById(Integer id);

    Book findById(Integer id);

    List<Book> findAll();
    
}
