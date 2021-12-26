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

                Docente d = new Docente(rs.getString("Cognome"), rs.getString("Nome"));
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


                if(d.contains(new Docente(Nome, Cognome))){

                    DAO.Disconnected();
                    return false;
                }

            st.executeUpdate("Insert into docente values('" + Nome + "', '" + Cognome + "')");

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

        DAO.Disconnected();
        return true;

    }

    public static boolean Remove_Teachers(String nome, String cognome) {

        ArrayList<Docente> d = Elenca_Docenti();

        try {

            DAO.registerDriver();
            Statement st = getConn1().createStatement();


                if(d.contains(new Docente(nome, cognome))){

                    st.executeUpdate("delete from docente where Cognome like'" + cognome + "' and Nome like ''" + nome +"''");
                    DAO.Disconnected();
                    return true;
                }



        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        DAO.Disconnected();
        return false;

    }

}
