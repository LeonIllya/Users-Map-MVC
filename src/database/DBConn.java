package database;

import utils.Users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {

    public static Connection connect() {

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(Users.DB_DRIVER +
                    Users.DB_BASE_URL + Users.DB_NAME);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
