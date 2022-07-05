package Servlet;

import DAO.DAO;
import DAO.DAO_Ripetizioni;
import Model.Insegnamento;
import DAO.DAO_Insegnamento;
import Model.Ripetizione;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


 @WebServlet("/Home")
public class Home extends HttpServlet {

     public void init() {
         DAO.registerDriver();
     }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

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

            ArrayList<Ripetizione> rt = DAO_Ripetizioni.Elenca_Ripetizioni();
            Rem_tab_rt_free(out, rt);

        }


    }

    private void Rem_tab_rt_free(PrintWriter out, ArrayList<Ripetizione> rt) {

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
                         "    </tr>\n");
             }
         }

         out.print("</tbody>\n" +
                 "</table></div> ");

         out.close();
     }

     public void destroy() {

         DAO.Disconnected();
     }
}
