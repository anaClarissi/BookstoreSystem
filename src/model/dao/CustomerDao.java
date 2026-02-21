package model.dao;

import model.entities.Customer;

import java.util.List;

public interface CustomerDao {

    void insert(Customer customer);

    void upadate(Customer customer);

    void deleteById(Integer id);

    Customer findById(Integer id);

    List<Customer> findAll();

}
