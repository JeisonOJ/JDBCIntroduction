package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {
    public static Connection connection = null;

    public static Connection openConnection() {
        String URL = "jdbc:mysql://localhost:3306/jdbc_introduction";
        String user = "root";
        String password = "Rlwl2023.";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, user, password);
            System.out.println("Connected Sucessfully");
        } catch (ClassNotFoundException e) {
            System.out.println("Error >>  The driver is not install");
        } catch (SQLException e) {
            System.out.println("Error >>  The database doesn't connect");
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
}
