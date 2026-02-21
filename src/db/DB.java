package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {

    private static Connection connection = null;

    private static Properties loadProperties () {

        try (FileInputStream fileInputStream = new FileInputStream("db.properties")) {

            Properties properties = new Properties();

            properties.load(fileInputStream);

            return properties;

        } catch (IOException e) {

            throw new DbExeption("Error -> DB.loadProperties <- " + e.getMessage());

        }

    }

    public static Connection getConnection () {

        if (connection == null) {

            try {

                Properties properties = loadProperties();

                String url = properties.getProperty("dburl");

                connection = DriverManager.getConnection(url, properties);

            } catch (SQLException e) {

                throw new DbExeption("Error -> DB.getConnection <- " + e.getMessage());

            }

        }

        return connection;

    }

    public static void closeConnection () {

        if (connection != null) {

            try {

                connection.close();

            } catch (SQLException e) {

                throw new DbExeption("Error -> DB.closeConnection <- " + e.getMessage());

            }

        }

    }

    public static void closeStatment (Statement statement) {

        if (statement != null) {

            try {

                statement.close();

            } catch (SQLException e) {

                throw new DbExeption("Error -> DB.closeSatatment <- " + e.getMessage());

            }

        }

    }

    public static void closeResultSet (ResultSet resultSet) {

        if (resultSet != null) {

            try {

                resultSet.close();

            } catch (SQLException e) {

                throw new DbExeption("Error -> DB.closeResultSet <- " + e.getMessage());

            }

        }

    }

}
