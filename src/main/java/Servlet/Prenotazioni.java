package Servlet;


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
import java.sql.SQLException;
import java.util.ArrayList;


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
        ArrayList<Prenotazione> pr_all = DAO_Prenotazioni.Elenca_Prenotazioni_utente((String)s.getAttribute("account"));
        ArrayList<Ripetizione> rt = DAO_Ripetizioni.Elenca_Ripetizioni();

        switch (tipo) {

            case "libere":

                Rem_tab_rt_free(out, rt);
                break;
            case "prenotate":

                Rem_tab_pr(out, pr_all, s);
                break;
            case "tutte":

                Rem_tab_pr_all(out, pr_all, s);
                break;
            case "Tutti_utenti":

                ArrayList<Prenotazione> pr = DAO_Prenotazioni.Elenca_Prenotazioni();
                Rem_tab_pr_all_Users(out, pr);
                break;
            default:

                if(tipo.length()>0) {

                    String[] Cs_rem = tipo.split(",");

                    for (int i = 0; i < Cs_rem.length; i++) {

                        String[] Cs_split = Cs_rem[i].split("/");
                        String Nome_pr = Cs_split[0];
                        String Cognome_pr = Cs_split[1];
                        String Corso_pr = Cs_split[2];
                        String Giorno_pr = Cs_split[3];
                        String Ora_pr = Cs_split[4];
                        String azione = Cs_split[5];

                        if (azione.equals("prenota")) {

                            DAO_Ripetizioni.Set_Repetitions_lock(Nome_pr, Cognome_pr, Corso_pr, Giorno_pr, Ora_pr);
                            DAO_Prenotazioni.Registered_Booking((String) s.getAttribute("account"), Corso_pr, Giorno_pr, Ora_pr, Nome_pr, Cognome_pr);

                        } else if (azione.equals("cancella")) {

                            DAO_Prenotazioni.Cancel_booking((String) s.getAttribute("account"), Corso_pr, Giorno_pr, Ora_pr, Nome_pr, Cognome_pr);
                            DAO_Ripetizioni.Set_Repetitions_Unlock(Nome_pr, Cognome_pr, Corso_pr, Giorno_pr, Ora_pr);

                        } else {

                            DAO_Prenotazioni.Success_booking((String) s.getAttribute("account"), Corso_pr, Giorno_pr, Ora_pr, Nome_pr, Cognome_pr);
                            DAO_Ripetizioni.Set_Repetitions_Unlock(Nome_pr, Cognome_pr, Corso_pr, Giorno_pr, Ora_pr);

                        }
                    }
                    Rem_tab_pr_utente(out, pr_all, s);
                }
                else{

                    Rem_tab_pr_utente(out, pr_all, s);
                }
                break;
        }

    }

    private void Rem_tab_rt_free(PrintWriter out, ArrayList<Ripetizione> rt) {

        if(rt.size()==0){

            out.print("<h1>Ci dispiace, non ci sono ripetizioni disponibili al momento</h1>");

        }
        else {


            out.print("<div id=\"table-scroll\" style=\"height:400px;\n" +
                    "  overflow:auto;\">" +
                    "<table class=\"table table-striped\">\n" +
                    "  <thead>\n" +
                    "    <tr>\n" +
                    "      <th scope=\"col\">Docente</th>\n" +
                    "      <th scope=\"col\">Corso</th>\n" +
                    "      <th scope=\"col\">Giorno</th>\n" +
                    "      <th scope=\"col\">Ora</th>\n" +
                    "      <th scope=\"col\">Prenota</th>\n" +
                    "    </tr>\n" +
                    "  </thead>\n" +
                    "  <tbody>\n");

            for (Ripetizione r : rt) {

                if (r.getStato().equals("libero")) {

                    out.print("<tr>\n" +
                            "      <td>" + r.getNome() + " " + r.getCognome() + "</td>\n" +
                            "      <td>" + r.getCorso() + "</td>\n" +
                            "      <td>" + r.getGiorno() + "</td>\n" +
                            "      <td>" + r.getOra() + "</td>\n" +
                            "      <td><input type=\"checkbox\" value=\"" + r.getNome() + "/" + r.getCognome() + "/" + r.getCorso() + "/" + r.getGiorno() + "/" + r.getOra() + "/prenota\" name=\"Reserv_rem\"></td>\n" +
                            "    </tr>\n");
                }
            }

            out.print("</tbody>\n" +
                    "</table></div> ");
        }
        out.close();
    }

    private void Rem_tab_pr(PrintWriter out, ArrayList<Prenotazione> pr, HttpSession s) {

        if(pr.size()==0){

            out.print("<h1>Non hai ancora effettuato alcuna prenotazione!</h1>");

        }
        else {
            out.print("<div id=\"table-scroll\" style=\"height:400px;\n" +
                    "  overflow:auto;\">" +
                    "<table class=\"table table-striped\">\n" +
                    "  <thead>\n" +
                    "    <tr>\n" +
                    "      <th scope=\"col\">Docente</th>\n" +
                    "      <th scope=\"col\">Corso</th>\n" +
                    "      <th scope=\"col\">Giorno</th>\n" +
                    "      <th scope=\"col\">Ora</th>\n" +
                    "      <th scope=\"col\">Cancella</th>\n" +
                    "    </tr>\n" +
                    "  </thead>\n" +
                    "  <tbody>\n");

            for (Prenotazione p : pr) {

                if (p.getStato().equals("attiva")) {

                    out.print("<tr>\n" +
                            "      <td>" + p.getNome_docente() + " " + p.getCognome_docente() + "</td>\n" +
                            "      <td>" + p.getCorso() + "</td>\n" +
                            "      <td>" + p.getGiorno() + "</td>\n" +
                            "      <td>" + p.getOra() + "</td>\n" +
                            "      <td><input type=\"checkbox\" value=\"" + p.getNome_docente() + "/" + p.getCognome_docente() + "/" + p.getCorso() + "/" + p.getGiorno() + "/" + p.getOra() + "/cancella\" name=\"Reserv_rem\"></td>\n" +
                            "    </tr>\n");
                }
            }

            out.print("</tbody>\n" +
                    "</table></div> ");
        }
        out.close();
    }

    private void Rem_tab_pr_utente(PrintWriter out, ArrayList<Prenotazione> pr, HttpSession s){

        if(pr.size()==0){

            out.print("<h1>Non hai ancora effettuato alcuna prenotazione!</h1>");

        }
        else {

            out.print("<div id=\"table-scroll\" style=\"height:400px;\n" +
                    "  overflow:auto;\">" +
                    "<table class=\"table table-striped\">\n" +
                    "  <thead>\n" +
                    "    <tr>\n" +
                    "      <th scope=\"col\">Docente</th>\n" +
                    "      <th scope=\"col\">Corso</th>\n" +
                    "      <th scope=\"col\">Giorno</th>\n" +
                    "      <th scope=\"col\">Ora</th>\n" +
                    "      <th scope=\"col\">Stato</th>\n" +
                    "    </tr>\n" +
                    "  </thead>\n" +
                    "  <tbody>\n");

            for (Prenotazione p : pr) {

                out.print("<tr>\n" +
                        "      <td>" + p.getNome_docente() + " " + p.getCognome_docente() + "</td>\n" +
                        "      <td>" + p.getCorso() + "</td>\n" +
                        "      <td>" + p.getGiorno() + "</td>\n" +
                        "      <td>" + p.getOra() + "</td>\n" +
                        "      <td>" + p.getStato() + "</td>\n" +
                        "    </tr>\n");

            }

            out.print("</tbody>\n" +
                    "</table></div> ");
        }
        out.close();

    }

    private void Rem_tab_pr_all (PrintWriter out, ArrayList<Prenotazione> pr, HttpSession s){

        if(pr.size()==0){

            out.print("<h1>Non hai ancora effettuato alcuna prenotazione!</h1>");

        }
        else {

            out.print("<div id=\"table-scroll\" style=\"height:400px;\n" +
                    "  overflow:auto;\">" +
                    "<table class=\"table table-striped\">\n" +
                    "  <thead>\n" +
                    "    <tr>\n" +
                    "      <th scope=\"col\">Docente</th>\n" +
                    "      <th scope=\"col\">Corso</th>\n" +
                    "      <th scope=\"col\">Giorno</th>\n" +
                    "      <th scope=\"col\">Ora</th>\n" +
                    "      <th scope=\"col\">Stato/Effettuata</th>\n" +
                    "    </tr>\n" +
                    "  </thead>\n" +
                    "  <tbody>\n");

            for (Prenotazione p : pr) {

                out.print("<tr>\n" +
                        "      <td>" + p.getNome_docente() + " " + p.getCognome_docente() + "</td>\n" +
                        "      <td>" + p.getCorso() + "</td>\n" +
                        "      <td>" + p.getGiorno() + "</td>\n" +
                        "      <td>" + p.getOra() + "</td>\n");
                if (p.getStato().equals("attiva") && s.getAttribute("Ruolo").equals("Cliente")) {

                    out.print("<td><input type=\"checkbox\" value=\"" + p.getNome_docente() + "/" + p.getCognome_docente() + "/" + p.getCorso() + "/" + p.getGiorno() + "/" + p.getOra() + "/effettuata\" name=\"Reserv_rem\"></td>\n");
                } else {
                    out.print("<td>" + p.getStato() + "</td>\n");
                }
                out.print("</tr>\n");
            }

            out.print("</tbody>\n" +
                    "</table></div> ");
        }
        out.close();

    }

    private void Rem_tab_pr_all_Users(PrintWriter out, ArrayList<Prenotazione> pr){

        out.print("<div id=\"table-scroll\" style=\"height:400px;\n" +
                "  overflow:auto;\">" +
                "<table class=\"table table-striped\">\n" +
                "  <thead>\n" +
                "    <tr>\n" +
                "      <th scope=\"col\">Utente</th>\n" +
                "      <th scope=\"col\">Docente</th>\n" +
                "      <th scope=\"col\">Corso</th>\n" +
                "      <th scope=\"col\">Giorno</th>\n" +
                "      <th scope=\"col\">Ora</th>\n" +
                "      <th scope=\"col\">Stato</th>\n" +
                "    </tr>\n" +
                "  </thead>\n" +
                "  <tbody>\n");

        for(Prenotazione p: pr){

            out.print("<tr>\n" +
                    "      <td>" + p.getUtente() +  "</td>\n" +
                    "      <td>" + p.getNome_docente()  + " " + p.getCognome_docente() +"</td>\n" +
                    "      <td>" + p.getCorso() + "</td>\n" +
                    "      <td>" + p.getGiorno() + "</td>\n" +
                    "      <td>" + p.getOra() + "</td>\n" +
                    "      <td>" + p.getStato() + "</td>\n");
            out.print("</tr>\n");

        }
        out.print("</tbody>\n" +
                "</table></div> ");
        out.close();

    }


}

