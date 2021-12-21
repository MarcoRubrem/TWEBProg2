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

    public static String Registered_User(String account, String pw, String role){

        ArrayList<Utente> u = Elenca_utenti();

        try {

            DAO.registerDriver();
            Statement st = getConn1().createStatement();

            for(Utente us: u){

                if(us.getAccount().equals(account)){

                    DAO.Disconnected();
                    return "User already registered";
                }
            }

            st.executeUpdate("Insert into utente values('" + account + "', '" + pw + "', '" + role + "')");

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

        DAO.Disconnected();
        return role;

    }

    public static String Logged_user(String account, String pw){

        ArrayList<Utente> u = Elenca_utenti();

            for(Utente us: u){

                if(us.getAccount().equals(account) && us.getPassword().equals(pw)){

                    return us.getRuolo();
                }
            }

        return "user_not_found";
    }
}
