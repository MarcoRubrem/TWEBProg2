package Servlet;

import Model.Insegnamento;
import DAO.DAO_Insegnamento;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


 @WebServlet("/Home")
public class Home extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String param = request.getParameter("param");


        if(param.equals("Ripetizioni")){

                out.println("<table class=\"table table-striped\">\n" +
                        "  <thead>\n" +
                        "    <tr>\n" +
                        "      <th scope=\"col\">Ora</th>\n" +
                        "      <th scope=\"col\">Lunedì</th>\n" +
                        "      <th scope=\"col\">Martedì</th>\n" +
                        "      <th scope=\"col\">Mercoledì</th>\n" +
                        "      <th scope=\"col\">Giovedì</th>\n" +
                        "      <th scope=\"col\">Venerdì</th>\n" +
                        "    </tr>\n" +
                        "  </thead>\n" +
                        "  <tbody>");
            out.println(" <tr>\n" +
                    "      <td>15:00</td>\n" +
                    "      <td>Programmazione III</td>\n" +
                    "      <td>Tecnologie WEB</td>\n" +
                    "      <td>Probabilità e statistica</td>\n" +
                    "      <td>Basi di Dati</td>\n" +
                    "      <td>Sistemi Operativi</td>\n" +
                    "    </tr>");
            out.println(" <tr>\n" +
                    "      <td>16:00</td>\n" +
                    "      <td>Linguaggi formali e Traduttori</td>\n" +
                    "      <td>Architettura degli elaboratori</td>\n" +
                    "      <td>Linguaggi formali e Traduttori</td>\n" +
                    "      <td>Tecnologie WEB</td>\n" +
                    "      <td>Interazione Uomo-Macchina</td>\n" +
                    "    </tr>");
            out.println(" <tr>\n" +
                    "      <td>17:00</td>\n" +
                    "      <td>Analisi</td>\n" +
                    "      <td>Programmazione II</td>\n" +
                    "      <td>Basi di Dati</td>\n" +
                    "      <td>Programmazione II</td>\n" +
                    "      <td>Reti</td>\n" +
                    "    </tr>");
            out.println(" <tr>\n" +
                    "      <td>18:00</td>\n" +
                    "      <td>Reti</td>\n" +
                    "      <td>Fisica</td>\n" +
                    "      <td>Interazione Uomo-Macchina</td>\n" +
                    "      <td>Sistemi Operativi</td>\n" +
                    "      <td>Programmazione III</td>\n" +
                    "    </tr>");
            out.println("  </tbody>\n" +
                    "</table>");
            out.close();

        }

        else if(param.equals("Insegnamenti")){

            ArrayList<Insegnamento> i = DAO_Insegnamento.Elenca_Insegnamenti();

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
                    "    </tr>\n" +
                    "  </thead>\n" +
                    "  <tbody>\n");

            for(Insegnamento is: i){

                out.print("<tr>\n" +
                            "      <td>" + is.getNome_docente() + " " + is.getCognome_docente() + "</td>\n" +
                            "      <td>" + is.getCorso() + "</td>\n" +
                            "    </tr>\n");
            }
            out.print("</tbody>\n" +
                    "</table></div> ");
            out.close();
        }


    }
}
