package application;

import db.DbExeption;
import model.dao.AuthorDao;
import model.dao.BookDao;
import model.dao.CustomerDao;
import model.dao.DaoFactory;
import model.entities.Author;
import model.entities.Book;
import model.entities.Customer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //crudCustomer();

        //crudBook();

        crudAuthor();

    }

    public static void crudAuthor () {

        Scanner scanner = new Scanner(System.in);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        AuthorDao authorDao = DaoFactory.createAuthorDao();

        CustomerDao customerDao = DaoFactory.createCustomerDao();

        while (true) {

            crud();

            int choice = scanner.nextInt();

            scanner.nextLine();

            // Find All
            if (choice == 1) {

                System.out.println();

                System.out.println("===== FindAll =====");

                List<Author> list = authorDao.findAll();

                for (Author obj : list) System.out.println(obj);

                System.out.println();

            }

            // Find By Id
            else if (choice == 2) {

                System.out.println();

                System.out.println("===== Find By Id =====");

                System.out.print("Enter the Id: ");

                int id = scanner.nextInt();

                System.out.println("===============");

                Author author = authorDao.findById(id);

                if (author == null) {

                    System.out.println("Author ID does not exist");

                } else {

                    System.out.println(author);

                }

                System.out.println();

            }

            // Delete By id
            else if (choice == 3) {

                System.out.println();

                try {

                    System.out.println("===== Delete By Id =====");

                    System.out.print("Enter the Id: ");
                    int id = scanner.nextInt();

                    System.out.println("===============");

                    authorDao.deleteById(id);

                } catch (DbExeption e) {

                    System.out.println("Author does not exist");

                }

                System.out.println();

            }

            // Insert
            else if (choice == 4) {

                System.out.println();

                try {

                    System.out.println("===== Insert =====");

                    System.out.print("Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Birth Date (dd/MM/yyyy): ");
                    LocalDate birthDate = LocalDate.parse(scanner.next(), formatter);

                    authorDao.insert(new Author(null, name, birthDate));

                } catch (DbExeption e) {

                    System.out.println("Customer does not exist");

                }

                System.out.println();

            }

            // Update
            else if (choice == 5) {

                System.out.println();

                try {

                    System.out.println("===== Update =====");

                    System.out.print("Enter the Id: ");
                    int id = scanner.nextInt();

                    Author author = authorDao.findById(id);

                    System.out.println("==============");
                    System.out.println("1 - Update Name");
                    System.out.println("2 - Update BirthDate");
                    System.out.println("==============");
                    System.out.print("Enter: ");

                    int update = scanner.nextInt();

                    scanner.nextLine();

                    switch (update) {

                        case 1 -> {

                            System.out.print("Enter the new Name: ");
                            String name = scanner.nextLine();

                            author.setName(name);

                        }

                        case 2 -> {

                            try {

                                System.out.print("Enter the new Birth Date (dd/MM/yyyy): ");
                                LocalDate birthDate = LocalDate.parse(scanner.next(), formatter);

                                author.setBirthDate(birthDate);

                            } catch (DbExeption e) {

                                System.out.println(e.getMessage());

                            }

                        }

                    }

                    authorDao.update(author);

                    System.out.println();

                } catch (DbExeption e) {

                    System.out.println(e.getMessage());

                }

                System.out.println();

            }

            // Exit
            else {

                System.out.println();

                System.out.println("Exit...");

                break;

            }

        }

        scanner.close();

    }

    public static void crudBook () {

        Scanner scanner = new Scanner(System.in);

        BookDao bookDao = DaoFactory.createBookDao();

        while (true) {

            crud();

            int choice = scanner.nextInt();

            scanner.nextLine();

            // Find All
            if (choice == 1) {

                System.out.println();

                System.out.println("===== FindAll =====");

                List<Book> list = bookDao.findAll();

                for (Book obj : list) System.out.println(obj);

                System.out.println();

            }

            // Find By Id
            else if (choice == 2) {

                System.out.println();

                System.out.println("===== Find By Id =====");

                System.out.print("Enter the Id: ");

                int id = scanner.nextInt();

                System.out.println("===============");

                Book book = bookDao.findById(id);

                if (book == null) {

                    System.out.println("Book does not exist");

                } else {

                    System.out.println(book);

                }

                System.out.println();

            }

            // Delete
            else if (choice == 3) {

                System.out.println();

                try {

                    System.out.println("===== Delete By Id =====");

                    System.out.print("Enter the Id: ");
                    int id = scanner.nextInt();

                    System.out.println("===============");

                    bookDao.deleteById(id);

                } catch (DbExeption e) {

                    System.out.println("Book does not exist");

                }

                System.out.println();

            }

            /*
            else if (choice == 4) {

                System.out.println();

                try {

                    System.out.println("===== Insert =====");

                    System.out.print("Title: ");
                    String title = scanner.nextLine();

                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    customerDao.insert(new Customer(null, name, email));

                } catch (DbExeption e) {

                    System.out.println("Customer does not exist");

                }

                System.out.println();

            } */

            /*
            // Update
            else if (choice == 5) {

                System.out.println();

                try {

                    System.out.println("===== Update =====");

                    System.out.print("Enter the Id: ");
                    int id = scanner.nextInt();

                    Customer customer = customerDao.findById(id);

                    System.out.println("==============");
                    System.out.println("1 - Update Name");
                    System.out.println("2 - Update Email");
                    System.out.println("==============");
                    System.out.print("Enter: ");

                    int update = scanner.nextInt();

                    scanner.nextLine();

                    switch (update) {

                        case 1 -> {

                            System.out.print("Enter the new Name: ");
                            String name = scanner.nextLine();

                            customer.setName(name);

                        }

                        case 2 -> {

                            try {

                                System.out.print("Enter the new Email: ");
                                String email = scanner.next();

                                customer.setEmail(email);

                            } catch (DbExeption e) {

                                System.out.println(e.getMessage());

                            }

                        }

                    }

                    customerDao.upadate(customer);

                    System.out.println();

                } catch (DbExeption e) {

                    System.out.println(e.getMessage());

                }

                System.out.println();

            } */

            // Exit
            else {

                System.out.println();

                System.out.println("Exit...");

                break;

            }

        }

        scanner.close();

    }

    public static void crudCustomer () {

        Scanner scanner = new Scanner(System.in);

        CustomerDao customerDao = DaoFactory.createCustomerDao();

        while (true) {

            crud();

            int choice = scanner.nextInt();

            scanner.nextLine();

            // Find All
            if (choice == 1) {

                System.out.println();

                System.out.println("===== FindAll =====");

                List<Customer> list = customerDao.findAll();

                for (Customer obj : list) System.out.println(obj);

                System.out.println();

            }

            // Find By Id
            else if (choice == 2) {

                System.out.println();

                System.out.println("===== Find By Id =====");

                System.out.print("Enter the Id: ");

                int id = scanner.nextInt();

                System.out.println("===============");

                Customer customer = customerDao.findById(id);

                if (customer == null) {

                    System.out.println("Customer does not exist");

                } else {

                    System.out.println(customer);

                }

                System.out.println();

            }

            // Delete
            else if (choice == 3) {

                System.out.println();

                try {

                    System.out.println("===== Delete By Id =====");

                    System.out.print("Enter the Id: ");
                    int id = scanner.nextInt();

                    System.out.println("===============");

                    customerDao.deleteById(id);

                } catch (DbExeption e) {

                    System.out.println("Customer does not exist");

                }

                System.out.println();

            }

            // Insert
            else if (choice == 4) {

                    System.out.println();

                try {

                    System.out.println("===== Insert =====");

                    System.out.print("Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    customerDao.insert(new Customer(null, name, email));

                } catch (DbExeption e) {

                    System.out.println("Customer does not exist");

                }

                System.out.println();

            }

            // Update
            else if (choice == 5) {

                System.out.println();

                try {

                    System.out.println("===== Update =====");

                    System.out.print("Enter the Id: ");
                    int id = scanner.nextInt();

                    Customer customer = customerDao.findById(id);

                    System.out.println("==============");
                    System.out.println("1 - Update Name");
                    System.out.println("2 - Update Email");
                    System.out.println("==============");
                    System.out.print("Enter: ");

                    int update = scanner.nextInt();

                    scanner.nextLine();

                    switch (update) {

                        case 1 -> {

                            System.out.print("Enter the new Name: ");
                            String name = scanner.nextLine();

                            customer.setName(name);

                        }

                        case 2 -> {

                            try {

                                System.out.print("Enter the new Email: ");
                                String email = scanner.next();

                                customer.setEmail(email);

                            } catch (DbExeption e) {

                                System.out.println(e.getMessage());

                            }

                        }

                    }

                    customerDao.upadate(customer);

                    System.out.println();

                } catch (DbExeption e) {

                    System.out.println(e.getMessage());

                }

                System.out.println();

            }

            // Exit
            else {

                System.out.println();

                System.out.println("Exit...");

                break;

            }

        }

        scanner.close();

    }

    public static void crud() {

        System.out.println("==============");
        System.out.println("1 - Find All");
        System.out.println("2 - Find By Id");
        System.out.println("3 - Delete By Id");
        System.out.println("4 - Insert");
        System.out.println("5 - Update");
        System.out.println("6 - Exit");
        System.out.println("==============");
        System.out.print("Enter: ");

    }

}
