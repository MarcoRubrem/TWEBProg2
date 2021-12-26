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
            rs = st.executeQuery("SELECT * FROM prenotazione where utente like '"+ utente +"'");

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

    public static ArrayList<Prenotazione> Elenca_Prenotazioni(){


        ResultSet rs;
        ArrayList<Prenotazione> out = new ArrayList<>();


        try {

            DAO.registerDriver();
            Statement st = getConn1().createStatement();
            rs = st.executeQuery("SELECT * FROM prenotazione");

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

    public static boolean Registered_Booking(String utente, String corso, String giorno, Time ora, String nomeD, String cognomeD){

        ArrayList<Prenotazione> p = Elenca_Prenotazioni_utente(utente);
        String t = ora.toString();


        try {

            DAO.registerDriver();
            Statement st = getConn1().createStatement();

                for(Prenotazione pz: p){

                    if(pz.getOra().toString().equals(ora.toString()) && pz.getCorso().equals(corso) && pz.getGiorno().equals(giorno) && pz.getNome_docente().equals(nomeD) && pz.getCognome_docente().equals(cognomeD) && pz.getStato().equals("attiva")) {

                        DAO.Disconnected();
                        return false;
                    }

                    if(pz.getOra().toString().equals(ora.toString()) && pz.getCorso().equals(corso) && pz.getGiorno().equals(giorno) && pz.getNome_docente().equals(nomeD) && pz.getCognome_docente().equals(cognomeD) && pz.getStato().equals("effettuata")){

                        st.executeUpdate("update prenotazione set stato = 'attiva' where corso like '"+ corso +"' AND giorno like '"+ giorno +"' and ora like '"+ getHours(t) +"%' and nome_docente like '"+ nomeD +"' and cognome_docente like '"
                                + cognomeD +"' and stato like 'effettuata' and utente like '"+ utente +"'");
                        DAO.Disconnected();
                        return true;

                    }
                }

            st.executeUpdate("Insert into prenotazione values('" + utente + "', '" + corso + "', '" + giorno + "', '" + ora + "', '" + nomeD + "', '" + cognomeD + "', 'attiva') " );

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

        DAO.Disconnected();
        return true;

    }

    public static boolean Cancel_booking(String utente, String corso, String giorno, Time ora, String nomeD, String cognomeD) {

        ArrayList<Prenotazione> p = Elenca_Prenotazioni_utente(utente);
        String t = ora.toString();

        try {

            DAO.registerDriver();
            Statement st = getConn1().createStatement();

            for(Prenotazione pz: p){

                if(pz.getOra().toString().equals(ora.toString()) && pz.getCorso().equals(corso) && pz.getGiorno().equals(giorno) && pz.getNome_docente().equals(nomeD) && pz.getCognome_docente().equals(cognomeD) && pz.getStato().equals("attiva")) {

                    st.executeUpdate("update prenotazione set stato = 'disdetta' where corso like '"+ corso +"' AND giorno like '"+ giorno +"' and ora like '"+ getHours(t) +"%' and nome_docente like '"+ nomeD +"' and cognome_docente like '"
                            + cognomeD +"' and stato like 'attiva' and utente like '"+ utente +"'");
                    DAO.Disconnected();
                    return true;
                }
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        DAO.Disconnected();
        return false;

    }

    public static boolean Success_booking(String utente, String corso, String giorno, Time ora, String nomeD, String cognomeD) {

        ArrayList<Prenotazione> p = Elenca_Prenotazioni_utente(utente);
        String t = ora.toString();

        try {

            DAO.registerDriver();
            Statement st = getConn1().createStatement();

            for(Prenotazione pz: p){

                if(pz.getOra().toString().equals(ora.toString()) && pz.getCorso().equals(corso) && pz.getGiorno().equals(giorno) && pz.getNome_docente().equals(nomeD) && pz.getCognome_docente().equals(cognomeD) && pz.getStato().equals("attiva")) {

                    st.executeUpdate("update prenotazione set stato = 'effettuata' where corso like '"+ corso +"' AND giorno like '"+ giorno +"' and ora like '"+ getHours(t) +"%' and nome_docente like '"+ nomeD +"' and cognome_docente like '"
                            + cognomeD +"' and stato like 'attiva' and utente like '"+ utente +"'");
                    DAO.Disconnected();
                    return true;
                }
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        DAO.Disconnected();
        return false;

    }
    


    public static String getHours(String t){

        String [] parts = t.split(":");
        return parts[0];
    }
}
