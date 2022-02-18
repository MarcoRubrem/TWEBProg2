package Servlet;

import DAO.*;
import Model.Corso;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import javax.print.Doc;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import static DAO.DAO.*;

@WebServlet ("/Impostazioni_admin_corsi")
public class Impostazioni_admin_corsi extends HttpServlet {


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

        String corso = request.getParameter("corso");
        String s_cfu = request.getParameter("cfu");
        ArrayList<Corso> cs = DAO_Corsi.Elenca_corsi();

        if(corso.equals("remove") && s_cfu.equals("0")){

            ResultSet rs;

            DAO.registerDriver();
            Statement st = getConn1().createStatement();

            rs = st.executeQuery("select * from corso");

            out.print("<table class=\"table table-striped\">\n" +
                    "  <thead>\n" +
                    "    <tr>\n" +
                    "      <th scope=\"col\">Corso</th>\n" +
                    "      <th scope=\"col\">CFU</th>\n" +
                    "      <th scope=\"col\">Elimina</th>\n" +
                    "    </tr>\n" +
                    "  </thead>\n" +
                    "  <tbody>\n");

            for(Corso c: cs){

                out.print("<tr>\n" +
                        "      <td>"+c.getTitolo()+"</td>\n" +
                        "      <td>"+c.getCFU()+"</td>\n" +
                        "      <td><input type=\"checkbox\" value=\""+c.getTitolo()+"/"+c.getCFU()+"\" name=\"corso_rem\"></td>\n" +
                        "    </tr>\n");
            }

            out.print("</tbody>\n" +
                    "</table> ");
            DAO.Disconnected();
        }
        else if(s_cfu.equals("1000")){

            String[] Cs_rem = corso.split(",");
            for(int i=0; i< Cs_rem.length; i++){

                /*
                Cs_rem è un array in cui ogni elemento è formato da Titolo/CFU
                Bisogna estrapolare semplicemente questi due valori per ogni
                elemento e poi richiamare il metodo DAO_Corsi.Remove_coourses(titolo, CFU)
                Così abbiamo finito il metodo per la rimozione e basta applicarlo anche a
                Ripetizioni e docenti ;)
                */

            }



        }
        else{

            if (corso.equals("")) {

                out.println("<div class=\"alert alert-danger\" role=\"alert\">Nome del corso obbligatorio</div>");
                out.close();
            }
            if (s_cfu.equals("")) {

                out.println("<div class=\"alert alert-danger\" role=\"alert\">Numero di CFU obbligatorio</div>");
                out.close();
            }

            int cfu = Integer.parseInt(s_cfu);

            if (cfu<=0) {

                out.println("<div class=\"alert alert-danger\" role=\"alert\">Numero di CFU non corretto!</div>");
                out.close();
            }

            if (DAO_Corsi.Registered_Courses(corso, cfu)) {

                out.println("<div class=\"alert alert-success\" role=\"alert\">Corso inserito correttamente!</div>");
                out.close();

            } else {

                out.println("<div class=\"alert alert-danger\" role=\"alert\">ATTENZIONE! Corso già inserito precedentemente</div>");
                out.close();

            }
        }





    }
}