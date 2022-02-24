package DAO;

import Model.Corso;
import Model.Docente;
import Model.Ripetizione;

import java.sql.*;
import java.util.ArrayList;

import static DAO.DAO.*;

public class DAO_Ripetizioni {

    public static ArrayList<Ripetizione> Elenca_Ripetizioni() {

        ResultSet rs;
        ArrayList<Ripetizione> out = new ArrayList<>();

        try {

            DAO.registerDriver();
            Statement st = getConn1().createStatement();
            rs = st.executeQuery("SELECT * FROM ripetizione");

            while (rs.next()) {
                Ripetizione r = new Ripetizione(rs.getString("nome"), rs.getString("cognome"), rs.getString("corso"), rs.getString("giorno"), rs.getTime("ora"), rs.getString("stato"));
                out.add(r);
            }
        }catch(SQLException e){

            System.out.print(e.getMessage());
        }
        DAO.Disconnected();
        return out;
    }

    public static boolean Registered_repetition (String Nome, String Cognome, String corso, String giorno, String ora){

        String stato = "libero";
        ArrayList<Ripetizione> c = Elenca_Ripetizioni();

        try {

            DAO.registerDriver();
            Statement st = getConn1().createStatement();

            for(Ripetizione cs: c){

                if(cs.getCognome().equals(Cognome) && cs.getNome().equals(Nome) && cs.getCorso().equals(corso) && cs.getGiorno().equals(giorno) && cs.getOra().equals(ora) && cs.getStato().equals(stato)){

                    DAO.Disconnected();
                    return false;
                }
            }

            st.executeUpdate("Insert into ripetizione values('" + Nome + "', '" + Cognome + "', '" + corso + "', '" + giorno + "', '" + ora + "', '" + stato + "')");

        } catch (Exception e) {

            System.out.println(e.getMessage());
            DAO.Disconnected();
            return false;
        }

        DAO.Disconnected();
        return true;

    }

    public static boolean Remove_Repetitions(String Nome, String Cognome, String corso, String giorno, String ora) {

        try {

            DAO.registerDriver();
            Statement st = getConn1().createStatement();
            st.executeUpdate("delete from ripetizione where nome like'" + Nome + "' and cognome like '" + Cognome +"' and corso like '" + corso +"' and giorno like '" + giorno +"'and ora like '" + ora +"'");
            DAO.Disconnected();
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            DAO.Disconnected();
            return false;
        }

    }

    public static void Set_Repetitions_lock(String Nome, String Cognome, String corso, String giorno, String ora){

        try {

            DAO.registerDriver();
            Statement st = getConn1().createStatement();
            st.executeUpdate("UPDATE `ripetizione` SET `stato`='occupato' WHERE nome like'" + Nome + "' and cognome like '" + Cognome +"' and corso like '" + corso +"' and giorno like '" + giorno +"'and ora like '" + ora +"'");
            DAO.Disconnected();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            DAO.Disconnected();
        }

    }

    public static void Set_Repetitions_Unlock(String Nome, String Cognome, String corso, String giorno, String ora) {

        try {

            DAO.registerDriver();
            Statement st = getConn1().createStatement();

            st.executeUpdate("UPDATE `ripetizione` SET `stato`='libero' WHERE nome like'" + Nome + "' and cognome like '" + Cognome +"' and corso like '" + corso +"' and giorno like '" + giorno +"'and ora like '" + ora +"'");
            DAO.Disconnected();

        }catch(SQLException e){

            System.out.println(e.getMessage());
            DAO.Disconnected();
        }

    }

}
