package Servlet;


import DAO.DAO;
import DAO.DAO_Corsi;
import DAO.DAO_Prenotazioni;
import DAO.DAO_Ripetizioni;
import Model.Prenotazione;
import Model.Ripetizione;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static DAO.DAO.getConn1;
import static DAO.DAO.registerDriver;

@WebServlet("/Prenotazioni")
public class Prenotazioni extends HttpServlet {


    public void init() {
        String message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            processRequest(request, response);
        } catch (SQLException | ServletException e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try {
            processRequest(request, response);
        } catch (SQLException | ServletException e) {
            e.printStackTrace();
        }


    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession s = request.getSession();

        String tipo = request.getParameter("tipo");
        ArrayList<Prenotazione> pr = DAO_Prenotazioni.Elenca_Prenotazioni();
        ArrayList<Prenotazione> pr_all = DAO_Prenotazioni.Elenca_Prenotazioni_utente((String)s.getAttribute("account"));
        ArrayList<Ripetizione> rt = DAO_Ripetizioni.Elenca_Ripetizioni();

        if (tipo.equals("libere")) {

            Rem_tab_rt_free(out, rt);
        }
        else if (tipo.equals("prenotate")) {

            Rem_tab_pr(out, pr, s);
        }
        else if (tipo.equals("tutte")) {

            Rem_tab_pr_all(out, pr_all, s);
        }
        else{

            String[] Cs_rem = tipo.split(",");
            String[] Cs_split = new String[Cs_rem.length];
            for (int i = 0; i < Cs_rem.length; i++) {

                Cs_split = Cs_rem[i].split("/");
            }
            System.out.println(Cs_split[Cs_split.length-1]);

            if(Cs_split[Cs_split.length-1].equals("prenota")) {

                String Nome_pr = Cs_split[0];
                String Cognome_pr = Cs_split[1];
                String Corso_pr = Cs_split[2];
                String Giorno_pr = Cs_split[3];
                String Ora_pr = Cs_split[4];

                DAO_Ripetizioni.Set_Repetitions_lock(Nome_pr, Cognome_pr, Corso_pr, Giorno_pr, Ora_pr);
                DAO_Prenotazioni.Registered_Booking((String)s.getAttribute("account"), Corso_pr, Giorno_pr, Ora_pr, Nome_pr, Cognome_pr);


            }
            else if(Cs_split[Cs_split.length-1].equals("cancella")){

                String Nome_pr = Cs_split[0];
                String Cognome_pr = Cs_split[1];
                String Corso_pr = Cs_split[2];
                String Giorno_pr = Cs_split[3];
                String Ora_pr = Cs_split[4];

                DAO_Prenotazioni.Cancel_booking((String)s.getAttribute("account"), Corso_pr, Giorno_pr, Ora_pr, Nome_pr, Cognome_pr);
                //DAO_Ripetizioni.Set_Repetitions_Unlock(Nome_pr, Cognome_pr, Corso_pr, Giorno_pr, Ora_pr);
            }
            else{

                String Nome_pr = Cs_split[0];
                String Cognome_pr = Cs_split[1];
                String Corso_pr = Cs_split[2];
                String Giorno_pr = Cs_split[3];
                String Ora_pr = Cs_split[4];

                DAO_Prenotazioni.Success_booking((String)s.getAttribute("account"), Corso_pr, Giorno_pr, Ora_pr, Nome_pr, Cognome_pr);
                //DAO_Ripetizioni.Set_Repetitions_Unlock(Nome_pr, Cognome_pr, Corso_pr, Giorno_pr, Ora_pr);
            }
        }

    }

    private void Rem_tab_rt_free(PrintWriter out, ArrayList<Ripetizione> rt) {


        out.print("<table class=\"table table-striped\">\n" +
                "  <thead>\n" +
                "    <tr>\n" +
                "      <th scope=\"col\">Nome Docente</th>\n" +
                "      <th scope=\"col\">Cognome Docente</th>\n" +
                "      <th scope=\"col\">Corso</th>\n" +
                "      <th scope=\"col\">Giorno</th>\n" +
                "      <th scope=\"col\">Ora</th>\n" +
                "      <th scope=\"col\">Prenota</th>\n" +
                "    </tr>\n" +
                "  </thead>\n" +
                "  <tbody>\n");

        for(Ripetizione r: rt){

            if(r.getStato().equals("libero")) {

                out.print("<tr>\n" +
                        "      <td>" + r.getNome() + "</td>\n" +
                        "      <td>" + r.getCognome() + "</td>\n" +
                        "      <td>" + r.getCorso() + "</td>\n" +
                        "      <td>" + r.getGiorno() + "</td>\n" +
                        "      <td>" + r.getOra() + "</td>\n" +
                        "      <td><input type=\"checkbox\" value=\"" + r.getNome() + "/" + r.getCognome() + "/" + r.getCorso() + "/" + r.getGiorno() + "/" + r.getOra() + "/prenota\" name=\"Reserv_rem\"></td>\n" +
                        "    </tr>\n");
            }
        }

        out.print("</tbody>\n" +
                "</table> ");
        out.close();
        DAO.Disconnected();
    }

    private void Rem_tab_pr(PrintWriter out, ArrayList<Prenotazione> pr, HttpSession s) {
        out.print("<table class=\"table table-striped\">\n" +
                "  <thead>\n" +
                "    <tr>\n" +
                "      <th scope=\"col\">Nome Docente</th>\n" +
                "      <th scope=\"col\">Cognome Docente</th>\n" +
                "      <th scope=\"col\">Corso</th>\n" +
                "      <th scope=\"col\">Giorno</th>\n" +
                "      <th scope=\"col\">Ora</th>\n" +
                "      <th scope=\"col\">Cancella</th>\n" +
                "    </tr>\n" +
                "  </thead>\n" +
                "  <tbody>\n");

        for(Prenotazione p: pr){

            if(p.getUtente().equals(s.getAttribute("account")) && p.getStato().equals("attiva")) {

                out.print("<tr>\n" +
                        "      <td>" + p.getNome_docente() + "</td>\n" +
                        "      <td>" + p.getCognome_docente() + "</td>\n" +
                        "      <td>" + p.getCorso() + "</td>\n" +
                        "      <td>" + p.getGiorno() + "</td>\n" +
                        "      <td>" + p.getOra() + "</td>\n" +
                        "      <td><input type=\"checkbox\" value=\"" + p.getNome_docente() + "/" + p.getCognome_docente() + "/" + p.getCorso() + "/" + p.getGiorno() + "/" + p.getOra() + "/cancella\" name=\"Reserv_rem\"></td>\n" +
                        "    </tr>\n");
            }
        }

        out.print("</tbody>\n" +
                "</table> ");
        out.close();
        DAO.Disconnected();
    }

    private void Rem_tab_pr_all (PrintWriter out, ArrayList<Prenotazione> pr, HttpSession s){

        out.print("<table class=\"table table-striped\">\n" +
                "  <thead>\n" +
                "    <tr>\n" +
                "      <th scope=\"col\">Nome Docente</th>\n" +
                "      <th scope=\"col\">Cognome Docente</th>\n" +
                "      <th scope=\"col\">Corso</th>\n" +
                "      <th scope=\"col\">Giorno</th>\n" +
                "      <th scope=\"col\">Ora</th>\n" +
                "      <th scope=\"col\">Stato/Effettuata</th>\n" +
                "    </tr>\n" +
                "  </thead>\n" +
                "  <tbody>\n");

        for(Prenotazione p: pr){

                out.print("<tr>\n" +
                        "      <td>" + p.getNome_docente() + "</td>\n" +
                        "      <td>" + p.getCognome_docente() + "</td>\n" +
                        "      <td>" + p.getCorso() + "</td>\n" +
                        "      <td>" + p.getGiorno() + "</td>\n" +
                        "      <td>" + p.getOra() + "</td>\n");
                if(p.getStato().equals("attiva")){

                    out.print("<td><input type=\"checkbox\" value=\"" + p.getNome_docente() + "/" + p.getCognome_docente() + "/" + p.getCorso() + "/" + p.getGiorno() + "/" + p.getOra() + "/effettuata\" name=\"Reserv_rem\"></td>\n");
                }
                else{
                    out.print("<td>" + p.getStato() + "</td>\n");
                }
                out.print("</tr>\n");
        }

        out.print("</tbody>\n" +
                "</table> ");
        out.close();
        DAO.Disconnected();

    }
}

