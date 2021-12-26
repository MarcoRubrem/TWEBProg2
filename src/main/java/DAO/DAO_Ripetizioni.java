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
                Ripetizione r=new Ripetizione(rs.getString("nome"), rs.getString("cognome"), rs.getString("corso"), rs.getString("giorno"), rs.getTime("ora"), rs.getString("stato"));
                out.add(r);
            }
        }catch(SQLException e){

            System.out.print(e.getMessage());
        }
        DAO.Disconnected();
        return out;
    }

    public static boolean Registered_repetition (String Nome, String Cognome, String corso, String giorno, Time ora){

        String stato = "disponibile";
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
        }

        DAO.Disconnected();
        return true;

    }

    public static void Crea_Ripetizione(String nome, String cognome, String corso, String giorno, Time ora, String stato) {

        try {

            DAO.registerDriver();
            Statement st = getConn1().createStatement();
            if (!(Registered_repetition(nome, cognome, corso, giorno, ora))) {
                int rs2 = st.executeUpdate("Insert into corso values('" + nome + "', '" + cognome + "', '" + corso + "', '" + giorno + "', '" + ora + "', '" + stato + "')");
            }else{
                System.out.println("prenotazione già presente nel database");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void Rimuovi_Ripetizione(String utente, String giorno, Time ora) {

        ResultSet rs;
        ArrayList<Ripetizione> out = new ArrayList<>();

        try {

            DAO.registerDriver();
            Statement st = getConn1().createStatement();

            // cambio lo stato da disponibile a prenotato
           // rs = st.executeQuery("SELECT * FROM docente");
           // int rs2 = st.executeUpdate("delete from docente where Cognome like'" + cognome + "' and Nome like ''" + nome +"''");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

}
