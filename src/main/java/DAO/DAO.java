package DAO;

import Model.Corso;
import Model.Docente;
import Model.Ripetizione;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DAO {

    private String message;
    private static final String url1 = "jdbc:mysql://localhost:3306/dbprog";
    private static final String user = "root";
    private static final String password = "tweb";
    private static Connection conn1 = null;

    public static Connection getConn1() {
        return conn1;
    }
    public static String getUrl1() { return url1; }
    public static String getUser() { return user; }
    public static String getPassword() { return password; }

    public static void setConn1(Connection conn1) {
        DAO.conn1 = conn1;
    }


    public static void registerDriver() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            System.out.println("Driver correttamente registrato");
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }
        } catch (SQLException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }

    public static void Disconnected() {

        if (conn1 != null) {
            try {
                conn1.close();
                System.out.println("DISCONNESSIONE AVVENUTA CON SUCCESSO");
            } catch (SQLException e2) {
                System.out.println(e2.getMessage());
            }
        }
    }


}