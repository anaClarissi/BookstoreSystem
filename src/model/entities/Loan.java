package model.entities;

import java.time.LocalDateTime;
import java.util.Objects;

public class Loan {

    private Integer id;
    private Book book;
    private Customer customer;
    private LocalDateTime loanDate;
    private LocalDateTime returnDate;

    public Loan () {}

    public Loan(Integer id, Book book, Customer customer, LocalDateTime loanDate, LocalDateTime returnDate) {

        this.id = id;

        this.book = book;

        this.customer = customer;

        this.loanDate = loanDate;

        this.returnDate = returnDate;

    }

    public Integer getId() {

        return id;

    }

    public void setId(Integer id) {

        this.id = id;

    }

    public Book getBook() {

        return book;

    }

    public void setBook(Book book) {

        this.book = book;

    }

    public Customer getCustomer() {

        return customer;

    }

    public void setCustomer(Customer customer) {

        this.customer = customer;

    }

    public LocalDateTime getLoanDate() {

        return loanDate;

    }

    public void setLoanDate(LocalDateTime loanDate) {

        this.loanDate = loanDate;

    }

    public LocalDateTime getReturnDate() {

        return returnDate;

    }

    public void setReturnDate(LocalDateTime returnDate) {

        this.returnDate = returnDate;

    }

    @Override
    public boolean equals(Object object) {

        if (object == null || getClass() != object.getClass()) return false;

        Loan loan = (Loan) object;

        return Objects.equals(id, loan.id) && Objects.equals(book, loan.book) && Objects.equals(customer, loan.customer) && Objects.equals(loanDate, loan.loanDate) && Objects.equals(returnDate, loan.returnDate);

    }

    @Override
    public int hashCode() {

        return Objects.hash(id, book, customer, loanDate, returnDate);

    }

    @Override
    public String toString() {

        return getId() + " | "
                + getBook().getTitle() + " | "
                + getCustomer().getName() + " | "
                + getLoanDate() + " | "
                + getReturnDate();

    }

}
