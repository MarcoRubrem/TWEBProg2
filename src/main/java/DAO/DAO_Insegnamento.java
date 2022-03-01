package DAO;

import Model.Docente;
import Model.Insegnamento;
import Model.Prenotazione;
import Model.Ripetizione;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

import static DAO.DAO.getConn1;

public class DAO_Insegnamento {

    public static ArrayList<Insegnamento> Elenca_Insegnamenti(){


        ResultSet rs;
        ArrayList<Insegnamento> out = new ArrayList<>();


        try {

            DAO.registerDriver();
            Statement st = getConn1().createStatement();
            rs = st.executeQuery("SELECT * FROM insegnamento");

            while (rs.next()) {

                Insegnamento i = new Insegnamento(rs.getString("Nome_docente"), rs.getString("Cognome_docente"), rs.getString("Corso"));
                out.add(i);
            }

        }catch(SQLException e){

            System.out.print(e.getMessage());
            DAO.Disconnected();
        }

        DAO.Disconnected();
        return out;
    }

    public static boolean Registered_Lessons (String Nome, String Cognome, String corso){

        ArrayList<Insegnamento> i = Elenca_Insegnamenti();

        try {

            DAO.registerDriver();
            Statement st = getConn1().createStatement();

            for(Insegnamento is: i){

                if(is.getNome_docente().equals(Nome) && is.getCognome_docente().equals(Cognome) && is.getCorso().equals(corso)){

                    DAO.Disconnected();
                    return false;
                }
            }

            st.executeUpdate("Insert into insegnamento values('" + Nome + "', '" + Cognome + "', '" + corso + "')");

        } catch (Exception e) {

            System.out.println(e.getMessage());
            DAO.Disconnected();
            return false;
        }

        DAO.Disconnected();
        return true;

    }
}
