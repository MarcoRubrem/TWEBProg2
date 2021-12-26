package DAO;

import Model.Docente;
import Model.Prenotazione;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

import static DAO.DAO.getConn1;

public class DAO_Prenotazioni{

    public static ArrayList<Prenotazione> Elenca_Prenotazioni_utente(String utente){


        ResultSet rs;
        ArrayList<Prenotazione> out = new ArrayList<>();


        try {
            DAO.registerDriver();
            Statement st = getConn1().createStatement();
            rs = st.executeQuery("SELECT * FROM prenotazione where " +
                    "prenotazione.utente = "+utente);

            while (rs.next()) {

                Prenotazione p = new Prenotazione(rs.getString("utente"), rs.getString("corso"), rs.getString("giorno"), rs.getTime("ora"), rs.getString("nome_docente"), rs.getString("cognome_docente"), rs.getString("stato"));
                out.add(p);
            }

        }catch(SQLException e){

            System.out.print(e.getMessage());
        }

        DAO.Disconnected();
        return out;
    }

    public static boolean Registered_prenotazioni (String utente, String corso, String giorno, Time ora, String nomeD, String cognomeD, String stato){

        ArrayList<Prenotazione> c = Elenca_Prenotazioni_utente(utente);

        try {

            DAO.registerDriver();
            Statement st = getConn1().createStatement();

            for(Prenotazione cs: c){

                if(cs.getUtente().equals(utente) && cs.getCorso().equals(corso) && cs.getGiorno().equals(giorno) && cs.getOra().equals(ora) && cs.getNome_docente().equals(nomeD) && cs.getCognome_docente().equals(cognomeD) && cs.getStato().equals(stato)){

                    DAO.Disconnected();
                    return false;
                }
            }

            st.executeUpdate("Insert into docente values('" + utente + "', '" + corso + "', '" + giorno + "', '" + ora + "', '" + nomeD + "', '" + cognomeD + "', '" + stato + "') " );

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

        DAO.Disconnected();
        return true;

    }

    public static void Crea_Prenotazione(String utente, String corso, String giorno, Time ora, String nomeD, String cognomeD, String stato) {

        try {

            DAO.registerDriver();
            Statement st = getConn1().createStatement();
            if (!(Registered_prenotazioni(utente, corso, giorno, ora, nomeD, cognomeD, stato))) {
                int rs2 = st.executeUpdate("Insert into corso values('" + utente + "', '" + corso + "', '" + giorno + "', '" + ora + "', '" + nomeD + "', '" + cognomeD + "', '" + stato + "')");
            }else{
                System.out.println("prenotazione già presente nel database");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void Cancella_Prenotazione(String utente, String giorno, Time ora) {

        ResultSet rs;
        ArrayList<Prenotazione> out = new ArrayList<>();

        try {

            DAO.registerDriver();
            Statement st = getConn1().createStatement();

            // prendo la tupla che ha i dati in input e cambio lo stato da prenotata a cancellata
            //rs = st.executeQuery("SELECT * FROM prenotazione");
            //int rs2 = st.executeUpdate("update from prenotazione where utente like'" + utente + "' and giorno like '" + giorno +"' and ora like '" + ora + "' ");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
