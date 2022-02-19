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

            if(c.contains(new Corso(titolo, CFU))){

                DAO.Disconnected();
                return false;
            }

            st.executeUpdate("Insert into corso values('" + titolo + "', '" + CFU + "')");

        } catch (Exception e) {

            System.out.println(e.getMessage());
            DAO.Disconnected();
            return false;
        }

        DAO.Disconnected();
        return true;

    }

    public static void Remove_Courses(String titolo, int CFU) {

        ArrayList<Corso> c = Elenca_corsi();

        try {

            DAO.registerDriver();
            Statement st = getConn1().createStatement();

            st.executeUpdate("delete from corso where titolo like'" + titolo + "' and CFU="+ CFU);
            DAO.Disconnected();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            DAO.Disconnected();
        }

        DAO.Disconnected();

    }





}
