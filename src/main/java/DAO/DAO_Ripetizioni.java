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


            Statement st = getConn1().createStatement();
            rs = st.executeQuery("SELECT * FROM ripetizione order by ora");

            while (rs.next()) {
                Ripetizione r = new Ripetizione(rs.getString("nome"), rs.getString("cognome"), rs.getString("corso"), rs.getString("giorno"), rs.getTime("ora"), rs.getString("stato"));
                out.add(r);
            }
        }catch(SQLException e){

            System.out.print(e.getMessage());
        }

        return out;
    }

    public static boolean Registered_repetition (String Nome, String Cognome, String corso, String giorno, String ora){

        String stato = "libero";
        ArrayList<Ripetizione> c = Elenca_Ripetizioni();

        try {


            Statement st = getConn1().createStatement();

            for(Ripetizione cs: c){

                if(cs.getNome().equals(Nome) && cs.getCognome().equals(Cognome) && cs.getCorso().equals(corso) && cs.getGiorno().equals(giorno) && cs.getOra().equals(ora)){


                    return false;
                }
            }

            st.executeUpdate("Insert into ripetizione values('" + Nome + "', '" + Cognome + "', '" + corso + "', '" + giorno + "', '" + ora + ":00:00', '" + stato + "')");
            DAO_Insegnamento.Registered_Lessons(Nome, Cognome, corso);
            DAO_Docenti.Registered_teacher(Nome, Cognome);

        } catch (Exception e) {

            System.out.println(e.getMessage());

            return false;
        }


        return true;

    }

    public static boolean Remove_Repetitions(String Nome, String Cognome, String corso, String giorno, String ora) {

        try {


            Statement st = getConn1().createStatement();
            st.executeUpdate("delete from ripetizione where nome like'" + Nome + "' and cognome like '" + Cognome +"' and corso like '" + corso +"' and giorno like '" + giorno +"'and ora like '" + ora +"'");

            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());

            return false;
        }

    }

    public static void Set_Repetitions_lock(String Nome, String Cognome, String corso, String giorno, String ora){

        try {


            Statement st = getConn1().createStatement();
            st.executeUpdate("UPDATE `ripetizione` SET `stato`='occupato' WHERE nome like'" + Nome + "' and cognome like '" + Cognome +"' and corso like '" + corso +"' and giorno like '" + giorno +"'and ora like '" + ora +"'");


        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

    }

    public static void Set_Repetitions_Unlock(String Nome, String Cognome, String corso, String giorno, String ora) {

        try {


            Statement st = getConn1().createStatement();

            st.executeUpdate("UPDATE `ripetizione` SET `stato`='libero' WHERE nome like'" + Nome + "' and cognome like '" + Cognome +"' and corso like '" + corso +"' and giorno like '" + giorno +"'and ora like '" + ora +"'");


        }catch(SQLException e){

            System.out.println(e.getMessage());

        }

    }

}
