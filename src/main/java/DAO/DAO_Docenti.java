package DAO;


import Model.Corso;
import Model.Docente;


import java.sql.*;
import java.util.ArrayList;

import static DAO.DAO.*;

public class DAO_Docenti {

    public static ArrayList<Docente> Elenca_Docenti() {

        ResultSet rs;
        ArrayList<Docente> out = new ArrayList<>();

        try {

            DAO.registerDriver();
            Statement st = getConn1().createStatement();
            rs = st.executeQuery("SELECT * FROM docente");

            while (rs.next()) {

                Docente d = new Docente(rs.getString("Nome"), rs.getString("Cognome"));
                out.add(d);
            }

        }catch(SQLException e){

            System.out.print(e.getMessage());
        }
        DAO.Disconnected();
        return out;
    }

    public static boolean Registered_teacher(String Nome, String Cognome){

        ArrayList<Docente> d = Elenca_Docenti();

        try {

            DAO.registerDriver();
            Statement st = getConn1().createStatement();

            st.executeUpdate("INSERT INTO `docente`(`Nome`, `Cognome`) VALUES ('"+Nome+"','"+Cognome+"')");
            return true;

        } catch (Exception e) {

            System.out.println(e.getMessage());
            DAO.Disconnected();
            return false;
        }

    }

    public static boolean Remove_Teachers(String nome, String cognome) {

        ArrayList<Docente> d = Elenca_Docenti();

        try {

            DAO.registerDriver();
            Statement st = getConn1().createStatement();

            st.executeUpdate("delete from docente where Cognome like'" + cognome + "' and Nome like '" + nome +"'");
            DAO.Disconnected();
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            DAO.Disconnected();
            return false;
        }

    }

}
