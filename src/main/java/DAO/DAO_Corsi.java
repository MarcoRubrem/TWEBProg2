package DAO;

import Model.Corso;
import Model.Prenotazione;
import Model.Utente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import static DAO.DAO.getConn1;

public class DAO_Corsi {

    public static ArrayList<Corso> Elenca_corsi(){

        ResultSet rs;
        ArrayList<Corso> out = new ArrayList<>();

        try {

            DAO.registerDriver();
            Statement st = getConn1().createStatement();
            rs = st.executeQuery("SELECT * FROM corso");

            while (rs.next()) {

                Corso c = new Corso(rs.getString("titolo"), rs.getInt("CFU"));
                out.add(c);
            }

        }catch(SQLException e){

            System.out.print(e.getMessage());
        }
        DAO.Disconnected();
        return out;
    }

    public static boolean Registered_Courses(String titolo, int CFU){

        ArrayList<Corso> c = Elenca_corsi();

        try {

            DAO.registerDriver();
            Statement st = getConn1().createStatement();

            for(Corso cs: c){

                if(cs.getTitolo().equals(titolo)){

                    DAO.Disconnected();
                    return false;
                }
            }

            st.executeUpdate("Insert into corso values('" + titolo + "', '" + CFU + "')");

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

        DAO.Disconnected();
        return true;

    }

    public static void Aggiungi_corso(String titolo, int CFU) {


        try {

            DAO.registerDriver();
            Statement st = getConn1().createStatement();
            if (!(Registered_Courses(titolo, CFU))) {
                int rs2 = st.executeUpdate("Insert into corso values('" + titolo + "', '" + CFU + "')");
            }else{
                System.out.println("corso già presente nel database");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void Rimuovi_Corso(String titolo) {

        ResultSet rs;
        ArrayList<Corso> out = new ArrayList<>();

        try {

            DAO.registerDriver();
            Statement st = getConn1().createStatement();
            rs = st.executeQuery("SELECT * FROM corso");

           // while (rs.next()) {
           //     System.out.println(rs.getString("Titolo") + " " + rs.getInt("CFU"));
           // }

            int rs2 = st.executeUpdate("delete from corso where titolo like'" + titolo + "'");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }





}
