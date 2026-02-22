package model.dao;

import db.DB;
import model.dao.impl.AuthorDaoJDBC;
import model.dao.impl.BookDaoJDBC;
import model.dao.impl.CustomerDaoJDBC;

public class DaoFactory {

    public static CustomerDao createCustomerDao () {

        return new CustomerDaoJDBC(DB.getConnection());

    }

    public static BookDao createBookDao () {

        return new BookDaoJDBC(DB.getConnection());

    }

    public static AuthorDao createAuthorDao() {

        return new AuthorDaoJDBC(DB.getConnection());

    }

}
