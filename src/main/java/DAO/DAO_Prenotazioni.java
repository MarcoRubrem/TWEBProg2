package DAO;

import Model.Prenotazione;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static DAO.DAO.getConn1;

public class DAO_Prenotazioni{

    public static ArrayList<Prenotazione> Elenca_Prenotazioni_utente(String utente){

        DAO.registerDriver();
        ResultSet rs;
        ArrayList<Prenotazione> out = new ArrayList<>();


        try {
            Statement st = getConn1().createStatement();
            rs = st.executeQuery("SELECT * FROM prenotazione where " +
                    "prenotazione.utente = "+utente);

        }catch(SQLException e){

            System.out.print(e.getMessage());
        }

        return out;
    }
}
