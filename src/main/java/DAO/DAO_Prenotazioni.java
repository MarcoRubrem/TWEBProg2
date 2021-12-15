package DAO;

import Model.Prenotazione;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

                Prenotazione p = new Prenotazione(rs.getString("utente"), rs.getString("corso"), rs.getString("data_ora"), rs.getString("nome_docente"), rs.getString("cognome_docente"), rs.getString("stato"));
                out.add(p);
            }

        }catch(SQLException e){

            System.out.print(e.getMessage());
        }

        DAO.Disconnected();
        return out;
    }
}
