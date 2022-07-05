package Servlet;

import DAO.*;
import Model.Ripetizione;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import static DAO.DAO.*;

@WebServlet ("/Impostazioni_admin_ripetizioni")
public class Impostazioni_admin_ripetizioni extends HttpServlet {


    public void init() {
        DAO.registerDriver();
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

        String nome = request.getParameter("Nome");
        String cognome = request.getParameter("Cognome");
        String corso = request.getParameter("corso");
        String giorno = request.getParameter("giorno");
        String ora = request.getParameter("ora");


        ArrayList<Ripetizione> rt = DAO_Ripetizioni.Elenca_Ripetizioni();
        HttpSession s = request.getSession();


        if(s.getAttribute("Ruolo").equals("Amministratore")) {

            if (nome.equals("remove") && cognome.equals("0")) {

                Rem_tab(out, rt);
            } else if (cognome.equals("1000")) {

                if (nome.length() == 0) {

                    Rem_tab(out, rt);

                } else {


                    String[] rt_rem = nome.split(",");

                    for (int i = 0; i < rt_rem.length; i++) {

                        String[] rt_split = rt_rem[i].split("/");
                        String nome_rem = rt_split[0];
                        String cognome_rem = rt_split[1];
                        String corso_rem = rt_split[2];
                        String giorno_rem = rt_split[3];
                        String ora_rem = rt_split[4];

                        DAO_Ripetizioni.Remove_Repetitions(nome_rem, cognome_rem, corso_rem, giorno_rem, ora_rem);

                    }

                    Rem_tab(out, rt);
                }
            } else {

                if (nome.equals("")) {

                    out.println("<div class=\"alert alert-danger\" role=\"alert\">Nome obbligatorio</div>");
                    out.close();
                } else if (cognome.equals("")) {

                    out.println("<div class=\"alert alert-danger\" role=\"alert\">Cognome obbligatorio</div>");
                    out.close();
                } else if (corso.equals("")) {

                    out.println("<div class=\"alert alert-danger\" role=\"alert\">Corso obbligatorio</div>");
                    out.close();
                } else {

                    if (DAO_Ripetizioni.Registered_repetition(nome, cognome, corso, giorno, ora)) {

                        out.println("<div class=\"alert alert-success\" role=\"alert\">Ripetizione inserita correttamente!</div>");
                        out.close();

                    } else {

                        out.println("<div class=\"alert alert-danger\" role=\"alert\">ATTENZIONE! Ripetizione già registrata</div>");
                        out.close();

                    }
                }

            }
        }else{

                out.println("<div class=\"alert alert-danger\" role=\"alert\">ATTENZIONE! Accesso negato</div>");
                out.close();


        }

    }

    private void Rem_tab(PrintWriter out, ArrayList<Ripetizione> rt) {

        out.print("<div id=\"table-scroll\" " +
                "style=\"height:400px;\n" +
                "overflow:auto;" +
                "width:1200px;" +
                "margin-left:10%\">" +
                    "<table class=\"table table-striped\">\n" +
                    "  <thead>\n" +
                    "    <tr>\n" +
                    "      <th scope=\"col\">Docente</th>\n" +
                    "      <th scope=\"col\">Corso</th>\n" +
                    "      <th scope=\"col\">Giorno</th>\n" +
                    "      <th scope=\"col\">Ora</th>\n" +
                    "      <th scope=\"col\">Elimina</th>\n" +
                    "    </tr>\n" +
                    "  </thead>\n" +
                    "  <tbody>\n");

            for (Ripetizione r : rt) {

                out.print("<tr>\n" +
                        "      <td>" + r.getNome() + " " + r.getCognome() + "</td>\n" +
                        "      <td>" + r.getCorso() + "</td>\n" +
                        "      <td>" + r.getGiorno() + "</td>\n" +
                        "      <td>" + r.getOra() + "</td>\n" +
                        "      <td><input type=\"checkbox\" value=\"" + r.getNome() + "/" + r.getCognome() + "/" + r.getCorso() + "/" + r.getGiorno() + "/" + r.getOra() + "\" name=\"rip_rem\"></td>\n" +
                        "    </tr>\n");
            }

            out.print("</tbody>\n" +
                    "</table> " +
                    "</div>");

        out.close();
    }

    public void destroy() {

        DAO.Disconnected();
    }
}