package server.factories;
import java.sql.*;

import javax.swing.JOptionPane;

public class DBConnectionFactory {
    private static Connection dbConn = null;


    public static Connection getDatabaseConnection() {

//		Connection conn = null;

        if (dbConn == null) {

            try {
                String url = "jdbc:mysql://localhost:3307/geer_db";
                String username = "root";
                String password = "usbw";

                dbConn = DriverManager.getConnection(url,username, password);

                System.out.println("Connection to database established.");
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else if(dbConn != null) {
            return dbConn;
        }

        return dbConn;
    }

    public static void main(String [] args) {

        dbConn = getDatabaseConnection();

        if(dbConn != null) {
            JOptionPane.showMessageDialog(null, "Connected to Local Server", "JDBC Connection Status", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
