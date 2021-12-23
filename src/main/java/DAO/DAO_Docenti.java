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

    public static boolean Registered_teacher (String Cognome, String Nome){

        ArrayList<Docente> c = Elenca_Docenti();

        try {

            DAO.registerDriver();
            Statement st = getConn1().createStatement();

            for(Docente cs: c){

                if(cs.getCognome().equals(Cognome) && cs.getNome().equals(Nome)){

                    DAO.Disconnected();
                    return false;
                }
            }

            st.executeUpdate("Insert into docente values('" + Cognome + "', '" + Nome + "')");

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

        DAO.Disconnected();
        return true;

    }

    public static void Rimuovi_Docente(String nome, String cognome) {

        ResultSet rs;
        ArrayList<Docente> out = new ArrayList<>();

        try {

            DAO.registerDriver();
            Statement st = getConn1().createStatement();
            rs = st.executeQuery("SELECT * FROM docente");

           // while (rs.next()) {
           //     System.out.println(rs.getString("Cognome") + " " + rs.getInt("Nome"));
           // }

            int rs2 = st.executeUpdate("delete from docente where Cognome like'" + cognome + "' and Nome like ''" + nome +"''");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

}
