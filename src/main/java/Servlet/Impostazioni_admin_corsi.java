package Servlet;

import DAO.*;
import Model.Corso;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import static DAO.DAO.*;

@WebServlet ("/Impostazioni_admin_corsi")
public class Impostazioni_admin_corsi extends HttpServlet {


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
        HttpSession s = request.getSession();

        String corso = request.getParameter("corso");
        String s_cfu = request.getParameter("cfu");
        ArrayList<Corso> cs = DAO_Corsi.Elenca_corsi();

        if(s.getAttribute("Ruolo").equals("Amministratore")) {

            if (corso.equals("remove") && s_cfu.equals("0")) {

                Rem_tab(out, cs);
            } else if (s_cfu.equals("1000")) {

                if (corso.length() == 0) {

                    Rem_tab(out, cs);

                } else {

                    String[] Cs_rem = corso.split(",");
                    for (int i = 0; i < Cs_rem.length; i++) {

                        String[] Cs_split = Cs_rem[i].split("/");
                        String titolo_rem = Cs_split[0];
                        int cfu_rem = Integer.parseInt(Cs_split[1]);

                        DAO_Corsi.Remove_Courses(titolo_rem, cfu_rem);

                    }

                    Rem_tab(out, cs);
                }
            } else {

                if (corso.equals("")) {

                    out.println("<div class=\"alert alert-danger\" role=\"alert\">Nome del corso obbligatorio</div>");
                    out.close();
                } else if (s_cfu.equals("") || (Integer.parseInt(s_cfu)<=0 || Integer.parseInt(s_cfu)>=30)) {

                    out.println("<div class=\"alert alert-danger\" role=\"alert\">Numero di CFU non corretto</div>");
                    out.close();
                } else {

                    if (DAO_Corsi.Registered_Courses(corso, Integer.parseInt(s_cfu))) {

                        out.println("<div class=\"alert alert-success\" role=\"alert\">Corso inserito correttamente!</div>");
                        out.close();

                    } else {

                        out.println("<div class=\"alert alert-danger\" role=\"alert\">ATTENZIONE! Corso gi?? inserito precedentemente</div>");
                        out.close();

                    }
                }
            }

        }
        else{

            out.println("<div class=\"alert alert-danger\" role=\"alert\">ATTENZIONE! Accesso negato</div>");
            out.close();


        }





    }

    private void Rem_tab(PrintWriter out, ArrayList<Corso> cs) {

        out.print("<div id=\"table-scroll\" " +
                "style=\"height:400px;\n" +
                "overflow:auto;" +
                "width:1200px;" +
                "margin-left:10%\">" +
                    "<table class=\"table table-striped\">\n" +
                    "  <thead>\n" +
                    "    <tr>\n" +
                    "      <th scope=\"col\">Corso</th>\n" +
                    "      <th scope=\"col\">CFU</th>\n" +
                    "      <th scope=\"col\">Elimina</th>\n" +
                    "    </tr>\n" +
                    "  </thead>\n" +
                    "  <tbody>\n");

            for (Corso c : cs) {

                out.print("<tr>\n" +
                        "      <td>" + c.getTitolo() + "</td>\n" +
                        "      <td>" + c.getCFU() + "</td>\n" +
                        "      <td><input type=\"checkbox\" value=\"" + c.getTitolo() + "/" + c.getCFU() + "\" name=\"corso_rem\"></td>\n" +
                        "    </tr>\n");
            }

            out.print("</tbody>\n" +
                    "</table></div> ");

        out.close();
    }

    public void destroy() {

        DAO.Disconnected();
    }
}

