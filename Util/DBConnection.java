// util/DBConnection.java
package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String DB_URL  = "jdbc:mysql://localhost:3305/LIBRARY_MANAGEMENT";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "MySQLroot@809";

    private DBConnection(){}

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }
}
