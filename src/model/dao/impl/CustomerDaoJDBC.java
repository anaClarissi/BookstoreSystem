package model.dao.impl;

import db.DB;
import db.DbExeption;
import model.dao.CustomerDao;
import model.entities.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomerDaoJDBC implements CustomerDao {

    private Connection connection;

    public CustomerDaoJDBC(Connection connection) {

        this.connection = connection;

    }

    @Override
    public void insert(Customer customer) {

        PreparedStatement statement = null;

        try {

            statement = connection.prepareStatement(
                    "INSERT INTO customer "
                        + "(Name, Email) "
                        + "VALUES "
                        + "(?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            if (isInList(customer)) {

                throw new DbExeption("Error -> Customer already exist!");

            }

            statement.setString(1, customer.getName());

            statement.setString(2, customer.getEmail());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {

                ResultSet resultSet = statement.getGeneratedKeys();

                if (resultSet.next()) {

                    int id = resultSet.getInt(1);

                    customer.setId(id);

                }

                DB.closeResultSet(resultSet);

            } else {

                throw new DbExeption("Error -> No rows Affected!");

            }


        } catch (SQLException e) {

            throw new DbExeption("Error -> CustomerDaoJDBC insert() <- " + e.getMessage());

        } finally {

            DB.closeStatment(statement);

        }

    }

    @Override
    public void upadate(Customer customer) {

        PreparedStatement statement = null;

        try {

            statement = connection.prepareStatement("UPDATE customer SET Name = ?, Email = ? WHERE Id = ?");

            statement.setString(1, customer.getName());

            statement.setString(2, customer.getEmail());

            statement.setInt(3, customer.getId());

            statement.executeUpdate();

        } catch (SQLException e) {

            throw new DbExeption("Error -> CustomerDaoJDBC update() <- " + e.getMessage());

        } finally {

            DB.closeStatment(statement);

        }

    }

    @Override
    public void deleteById(Integer id) {

        PreparedStatement statement = null;

        try {

            statement = connection.prepareStatement("DELETE FROM customer WHERE Id = ?");

            statement.setInt(1, id);

            int rows = statement.executeUpdate();

            if (rows == 0) {

                throw new DbExeption("Error -> CustomerDaoJDBC deleteById() <- This ID does not exist!");

            }

        } catch (SQLException e) {

            throw new DbExeption("Error -> CustomerDaoJDBC deleteById() <- " + e.getMessage());

        } finally {

            DB.closeStatment(statement);

        }

    }

    @Override
    public Customer findById(Integer id) {

        PreparedStatement statement = null;

        ResultSet resultSet = null;

        try {

            statement = connection.prepareStatement("SELECT * FROM customer WHERE Id = ?");

            statement.setInt(1, id);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {

                Customer customer = instantiateCustomer(resultSet);

                return customer;

            }

            return null;

        } catch (SQLException e) {

            throw new DbExeption("Error -> CustomerDaoJDBC findById() <- " + e.getMessage());

        } finally {

            DB.closeStatment(statement);

            DB.closeResultSet(resultSet);

        }


    }

    @Override
    public List<Customer> findAll() {

        PreparedStatement statement = null;

        ResultSet resultSet = null;

        try {

            statement = connection.prepareStatement("SELECT * FROM customer");

            resultSet = statement.executeQuery();

            List<Customer> list = new ArrayList<>();

            while (resultSet.next()) {

                Customer customer = instantiateCustomer(resultSet);

                list.add(customer);

            }

            return list;

        } catch (SQLException e) {

            throw new DbExeption("Error -> CustomerDaoJDBC findAll() <- " + e.getMessage());

        } finally {

            DB.closeStatment(statement);

            DB.closeResultSet(resultSet);

        }

    }

    private boolean isInList(Customer customer) {

        return findAll().stream().anyMatch(c -> c.getEmail().equals(customer.getEmail()));

    }

    private Customer instantiateCustomer(ResultSet resultSet) throws SQLException {

        Customer customer = new Customer();

        customer.setId(resultSet.getInt("Id"));

        customer.setName(resultSet.getString("Name"));

        customer.setEmail(resultSet.getString("Email"));

        return customer;

    }

}
