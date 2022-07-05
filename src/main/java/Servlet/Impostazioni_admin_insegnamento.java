package Servlet;

import DAO.*;


import Model.Insegnamento;

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

@WebServlet("/Impostazioni_admin_insegnamento")
public class Impostazioni_admin_insegnamento extends HttpServlet {


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
        String corso = request.getParameter("Corso");
        ArrayList<Insegnamento> di = DAO_Insegnamento.Elenca_Insegnamenti();
        HttpSession s = request.getSession();


        if(s.getAttribute("Ruolo").equals("Amministratore")) {

            if (nome.equals("remove") && cognome.equals("0")) {

                Rem_tab(out, di);
            } else{

                if (nome.length() == 0) {

                    Rem_tab(out, di);

                } else {

                    String[] Di_rem = nome.split(",");

                    for (int i = 0; i < Di_rem.length; i++) {

                        String[] Di_split = Di_rem[i].split("/");
                        String nome_rem = Di_split[0];
                        String cognome_rem = Di_split[1];
                        String corso_rem = Di_split[2];


                        DAO_Insegnamento.Remove_Lessons(nome_rem, cognome_rem, corso_rem );

                    }

                    Rem_tab(out, di);
                }
            }

        }else{

            out.println("<div class=\"alert alert-danger\" role=\"alert\">ATTENZIONE! Accesso negato</div>");
            out.close();


        }





    }

    private void Rem_tab(PrintWriter out, ArrayList<Insegnamento> di) {

        out.print("<div id=\"table-scroll\" " +
                "style=\"height:400px;\n" +
                "overflow:auto;" +
                "width:1200px;" +
                "margin-left:10%\">" +
                "<table class=\"table table-striped\">\n" +
                "  <thead>\n" +
                "    <tr>\n" +
                "      <th scope=\"col\">Nome</th>\n" +
                "      <th scope=\"col\">Cognome</th>\n" +
                "      <th scope=\"col\">Corso</th>\n" +
                "      <th scope=\"col\">Elimina</th>\n" +
                "    </tr>\n" +
                "  </thead>\n" +
                "  <tbody>\n");

        for (Insegnamento d : di) {

            out.print("<tr>\n" +
                    "      <td>" + d.getNome_docente() + "</td>\n" +
                    "      <td>" + d.getCognome_docente() + "</td>\n" +
                    "      <td>" + d.getCorso() + "</td>\n" +
                    "      <td><input type=\"checkbox\" value=\"" + d.getNome_docente() + "/" + d.getCognome_docente() + "/" + d.getCorso() + "\"\\\" name=\"docente_rem\"></td>\n" +
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