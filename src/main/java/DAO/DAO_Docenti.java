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


            Statement st = getConn1().createStatement();
            rs = st.executeQuery("SELECT * FROM docente");

            while (rs.next()) {

                Docente d = new Docente(rs.getString("Nome"), rs.getString("Cognome"));
                out.add(d);
            }

        }catch(SQLException e){

            System.out.print(e.getMessage());
        }

        return out;
    }

    public static boolean Registered_teacher(String Nome, String Cognome){

        ArrayList<Docente> d = Elenca_Docenti();

        try {


            Statement st = getConn1().createStatement();

            for(Docente dc: d){

                if(dc.getNome().equals(Nome) && dc.getCognome().equals(Cognome)) {


                    return false;
                }
            }

            st.executeUpdate("INSERT INTO `docente`(`Nome`, `Cognome`) VALUES ('"+Nome+"','"+Cognome+"')");
            return true;

        } catch (Exception e) {

            System.out.println(e.getMessage());

            return false;
        }

    }

    public static boolean Remove_Teachers(String nome, String cognome) {

        ArrayList<Docente> d = Elenca_Docenti();

        try {


            Statement st = getConn1().createStatement();

            st.executeUpdate("delete from docente where Cognome like'" + cognome + "' and Nome like '" + nome +"'");

            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());

            return false;
        }

    }

}
