package model.entities;

import java.util.Objects;

public class Customer {

    private Integer id;
    private String name;
    private String email;

    public Customer () {}

    public Customer(Integer id, String name, String email) {

        this.id = id;

        this.name = name;

        this.email = email;

    }

    public Integer getId() {

        return id;

    }

    public void setId(Integer id) {

        this.id = id;

    }

    public String getName() {

        return name;

    }

    public void setName(String name) {

        this.name = name;

    }

    public String getEmail() {

        return email;

    }

    public void setEmail(String email) {

        this.email = email;

    }

    @Override
    public boolean equals(Object object) {

        if (object == null || getClass() != object.getClass()) return false;

        Customer customer = (Customer) object;

        return Objects.equals(id, customer.id) && Objects.equals(name, customer.name) && Objects.equals(email, customer.email);

    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, email);

    }

    @Override
    public String toString() {

        return getId() + " | " + getName() + " | " + getEmail();

    }

}
