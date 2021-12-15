package DAO;

import Model.Prenotazione;
import Model.Utente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static DAO.DAO.getConn1;

public class DAO_utente {

    public static ArrayList<Utente> Elenca_utenti(){

        ResultSet rs;
        ArrayList<Utente> out = new ArrayList<>();

        try {

            DAO.registerDriver();
            Statement st = getConn1().createStatement();
            rs = st.executeQuery("SELECT * FROM utente");

            while (rs.next()) {

                Utente u = new Utente(rs.getString("account"), rs.getString("password"), rs.getString("ruolo"));
                out.add(u);
            }

        }catch(SQLException e){

            System.out.print(e.getMessage());
        }
        DAO.Disconnected();
        return out;
    }

    public static String Logged_user(String account, String pw){

        ResultSet rs;

        try {

            DAO.registerDriver();
            Statement st = getConn1().createStatement();
            rs = st.executeQuery("SELECT * FROM utente");

            while (rs.next()) {

                if(rs.getString("account").equals(account) && rs.getString("password").equals(pw)){

                    return rs.getString("ruolo");

                }
            }

        }catch(SQLException e) {

            System.out.print(e.getMessage());
        }finally {

            DAO.Disconnected();

        }

        return "user_not_found";
    }




}
