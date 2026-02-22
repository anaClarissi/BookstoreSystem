package model.dao.impl;

import db.DB;
import db.DbExeption;
import db.DbIntegrityExeption;
import model.dao.AuthorDao;
import model.entities.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDaoJDBC implements AuthorDao {

    private Connection connection;

    public AuthorDaoJDBC(Connection connection) {

        this.connection = connection;

    }


    @Override
    public void insert(Author author) {

        PreparedStatement statement = null;

        try {

            statement = connection.prepareStatement(
                    "INSERT INTO author "
                    + "(Name, BirthDate) "
                    + "VALUES "
                    + "(?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, author.getName());

            statement.setObject(2, author.getBirthDate());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {

                ResultSet resultSet = statement.getGeneratedKeys();

                if (resultSet.next()) {

                    int id = resultSet.getInt(1);

                    author.setId(id);

                }

                DB.closeResultSet(resultSet);

            } else {

                throw new DbIntegrityExeption("Error -> No rows Affected!");

            }

        } catch (SQLException e) {

            throw new DbExeption("Error -> AuthorDaoJDBC insert() <- " + e.getMessage());

        } finally {

            DB.closeStatment(statement);

        }

    }

    @Override
    public void update(Author author) {

        PreparedStatement statement = null;

        try {

            statement = connection.prepareStatement("UPDATE author SET Name = ?, BirthDate = ? WHERE Id = ?");

            statement.setString(1, author.getName());

            statement.setObject(2, author.getBirthDate());

            statement.setInt(3, author.getId());

            statement.executeUpdate();


        } catch (SQLException e) {

            throw new DbExeption("Error -> AuthorDaoJDBC update() <- " + e.getMessage());

        } finally {

            DB.closeStatment(statement);

        }

    }

    @Override
    public void deleteById(Integer id) {

        PreparedStatement statement = null;

        try {

            statement = connection.prepareStatement("DELETE FROM author WHERE Id = ?");

            statement.setInt(1, id);

            int rows = statement.executeUpdate();

            if (rows == 0) {

                throw new DbIntegrityExeption("Auhtor does not exist!");

            }

        } catch (SQLException e) {

            throw new DbExeption("Error AuthorDaoJDBC deleteById() <- " + e.getMessage());

        } finally {

            DB.closeStatment(statement);

        }

    }

    @Override
    public Author findById(Integer id) {

        PreparedStatement statement = null;

        ResultSet resultSet = null;

        try {

            statement = connection.prepareStatement("SELECT * FROM author WHERE Id = ?");

            statement.setInt(1, id);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {

                Author author = instantiateAuthor(resultSet);

                return author;

            }

            return null;

        } catch (SQLException e) {

            throw new DbExeption("Error -> AuthorDaoJDBC findById() <- " + e.getMessage());

        } finally {

            DB.closeStatment(statement);

            DB.closeResultSet(resultSet);

        }

    }

    @Override
    public List<Author> findAll() {

        PreparedStatement statement = null;

        ResultSet resultSet = null;

        try {

            statement = connection.prepareStatement("SELECT * FROM author");

            resultSet = statement.executeQuery();

            List<Author> list = new ArrayList<>();

            while (resultSet.next()) {

                Author author = instantiateAuthor(resultSet);

                list.add(author);

            }

            return list;

        } catch (SQLException e) {

            throw new DbExeption("Error -> AuthorDaoJDBC findAll() <- " + e.getMessage());

        } finally {

            DB.closeStatment(statement);

            DB.closeResultSet(resultSet);

        }

    }

    private Author instantiateAuthor(ResultSet resultSet) throws SQLException {

        Author author = new Author();

        author.setId(resultSet.getInt("Id"));

        author.setName(resultSet.getString("Name"));

        author.setBirthDate(resultSet.getDate("BirthDate").toLocalDate());

        return author;

    }

}
