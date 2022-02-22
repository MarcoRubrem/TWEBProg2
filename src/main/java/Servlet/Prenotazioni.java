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
        ArrayList<Ripetizione> rt = DAO_Ripetizioni.Elenca_Ripetizioni();

        if (tipo.equals("libere")) {

            Rem_tab_rt_free(out, rt);
        }
        else if (tipo.equals("prenotate")) {

            Rem_tab_pr(out, pr, s);
        }
        else if (tipo.equals("tutte")) {

            Rem_tab_pr_all(out, pr, s);
        }
        else{


            String[] Cs_rem = tipo.split(",");
            for (int i = 0; i < Cs_rem.length; i++) {

                String[] Cs_split = Cs_rem[i].split("/");
                String titolo_rem = Cs_split[0];
                int cfu_rem = Integer.parseInt(Cs_split[1]);

                DAO_Corsi.Remove_Courses(titolo_rem, cfu_rem);

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

            if(p.getUtente().equals(s.getAttribute("account"))) {

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
        }

        out.print("</tbody>\n" +
                "</table> ");
        out.close();
        DAO.Disconnected();

    }
}

