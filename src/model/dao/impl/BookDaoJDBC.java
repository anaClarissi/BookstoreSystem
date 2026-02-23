package model.dao.impl;

import db.DB;
import db.DbExeption;
import db.DbIntegrityExeption;
import model.dao.BookDao;
import model.entities.Author;
import model.entities.Book;
import model.enums.BookStatus;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookDaoJDBC implements BookDao {

    private Connection connection;

    public BookDaoJDBC(Connection connection) {

        this.connection = connection;

    }

    @Override
    public void insert(Book book) {

        PreparedStatement statement = null;

        try {

            statement = connection.prepareStatement(
                    "INSERT INTO book "
                    + "(Title, AuthorId, Status, RegistrationDate, UpdateDate) "
                    + "VALUES "
                    + "(?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, book.getTitle());

            statement.setInt(2, book.getAuthor().getId());

            statement.setString(3, String.valueOf(book.getStatus()));

            statement.setObject(4, book.getRegistrationDate());

            statement.setObject(5, book.getUpdateDate());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {

                ResultSet resultSet = statement.getGeneratedKeys();

                if (resultSet.next()) {

                    int id = resultSet.getInt(1);

                    book.setId(id);

                }

                DB.closeResultSet(resultSet);

            } else {

                throw new DbIntegrityExeption("Error -> No rows Affected!");

            }

        } catch (SQLException e) {

            throw new DbExeption("Error -> BookDaoJDBC insert() <- " + e.getMessage());

        } finally {

            DB.closeStatment(statement);

        }

    }

    @Override
    public void upadate(Book book) {

        PreparedStatement statement = null;

        try {

            statement = connection.prepareStatement(
                    "UPDATE book SET "
                    + "Title = ?, AuthorId = ?, UpdateDate = ? "
                    + "WHERE Id = ?");

            statement.setString(1, book.getTitle());

            statement.setInt(2, book.getAuthor().getId());

            statement.setObject(3, LocalDateTime.now());

            statement.setInt(4, book.getId());

            statement.executeUpdate();

        } catch (SQLException e) {

            throw new DbExeption("Error -> BookDaoJDBC update() <- " + e.getMessage());

        } finally {

            DB.closeStatment(statement);

        }

    }

    @Override
    public void deleteById(Integer id) {

        PreparedStatement statement = null;

        try {

            statement = connection.prepareStatement("DELETE FROM book WHERE Id = ?");

            statement.setInt(1, id);

            int rows = statement.executeUpdate();

            if (rows == 0) {

                throw new DbIntegrityExeption("Error -> CustomerDaoJDBC deleteById() <- This ID does not exist!");

            }

        } catch (SQLException e) {

            throw new DbExeption("Error BookDaoJDBC deleteById() <- " + e.getMessage());

        } finally {

            DB.closeStatment(statement);

        }

    }

    @Override
    public Book findById(Integer id) {

        PreparedStatement statement = null;

        ResultSet resultSet = null;

        try {

            statement = connection.prepareStatement(
                    "SELECT book.*,author.Name AS AuthorName "
                    + "FROM book INNER JOIN author "
                    + "ON book.AuthorId = author.Id "
                    + "WHERE book.Id = ?");

            statement.setInt(1, id);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {

                Author author = instantiateAuthor(resultSet);

                Book book = instantiateBook(resultSet, author);

                return book;

            }

            return null;

        } catch (SQLException e) {

            throw new DbExeption("Error -> BookDaoJDBC findById() <- " + e.getMessage());

        } finally {

            DB.closeStatment(statement);

            DB.closeResultSet(resultSet);

        }

    }

    @Override
    public List<Book> findAll() {

        PreparedStatement statement = null;

        ResultSet resultSet = null;

        try {

            statement = connection.prepareStatement(
                    "SELECT book.*,author.Name AS AuthorName "
                        + "FROM book INNER JOIN author "
                        + "ON book.AuthorId = author.Id "
                        + "ORDER BY Name");

            resultSet = statement.executeQuery();

            List<Book> list = new ArrayList<>();

            Map<Integer, Author> map = new HashMap<>();

            while (resultSet.next()) {

                Author author = map.get(resultSet.getInt("AuthorId"));

                if (author == null) {

                    author = instantiateAuthor(resultSet);

                    map.put(resultSet.getInt("AuthorId"), author);

                }

                Book book = instantiateBook(resultSet, author);

                list.add(book);

            }

            return list;


        } catch (SQLException e) {

            throw new DbExeption("Error -> BookDaoJDBC findAll() <- " + e.getMessage());

        } finally {

            DB.closeStatment(statement);

            DB.closeResultSet(resultSet);

        }

    }

    private Book instantiateBook(ResultSet resultSet, Author author) throws SQLException {

        Book book = new Book();

        book.setId(resultSet.getInt("Id"));

        book.setTitle(resultSet.getString("Title"));

        book.setAuthor(author);

        book.setStatus(BookStatus.valueOf(resultSet.getString("Status")));

        book.setRegistrationDate(resultSet.getTimestamp("RegistrationDate").toLocalDateTime());

        book.setUpdateDate(resultSet.getTimestamp("UpdateDate").toLocalDateTime());

        return book;

    }

    private Author instantiateAuthor(ResultSet resultSet) throws SQLException {

        Author author = new Author();

        author.setId(resultSet.getInt("AuthorId"));

        author.setName(resultSet.getString("AuthorName"));

        return author;

    }

}
