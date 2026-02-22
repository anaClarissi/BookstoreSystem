package model.dao;

import model.entities.Author;

import java.util.List;

public interface AuthorDao {

    void insert(Author author);

    void update(Author author);

    void deleteById(Integer id);

    Author findById(Integer id);

    List<Author> findAll();

}
