package com.example.twebprog2;

import DAO.DAO;
import DAO.DAO_Corsi;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static DAO.DAO.getConn1;

@WebServlet("/Corsi")
public class Corsi extends HttpServlet {

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
        String titolo = request.getParameter("titolo");
        String CFU = request.getParameter("CFU");
        ResultSet rs;

        if(titolo.equals("rm") && CFU.equals("0")){

            try {

                DAO.registerDriver();

                Statement st = getConn1().createStatement();
                rs = st.executeQuery("SELECT * FROM corso");

                out.println("<table class=\"table table-striped\">\n" +
                        "  <thead>\n" +
                        "    <tr>\n" +
                        "      <th scope=\"col\">Titolo</th>\n" +
                        "      <th scope=\"col\">CFU</th>\n" +
                        "      <th scope=\"col\">Rimuovi</th>\n" +
                        "    </tr>\n" +
                        "  </thead>\n" +
                        "  <tbody>");
                while(rs.next()) {

                    out.println("    <tr>\n" +
                            "      <td>"+rs.getString("titolo")+"</td>\n" +
                            "      <td>"+rs.getString("CFU")+"</td>\n" +
                            "      <td><input type=\"checkbox\" id=\""+rs.getString("titolo")+"\" name=\""+rs.getString("titolo")+"\" value=\""+rs.getString("titolo")+"\"></td>\n" +
                            "    </tr>");
                }

                out.println("  </tbody>\n" +
                        "</table>");

            }catch (SQLException e){

                System.out.println(e.getMessage());
            }
            out.close();
            DAO.Disconnected();
        }
        else {

            if (titolo.equals("")) {

                out.println("<div class=\"alert alert-danger\" role=\"alert\">Titolo del corso obbligatorio</div>");
                out.close();
            }
            if (CFU.equals("") || Integer.parseInt(CFU) <= 0) {

                out.println("<div class=\"alert alert-danger\" role=\"alert\">Numero di crediti non corretto</div>");
                out.close();
            }

            if (!DAO_Corsi.Registered_Courses(titolo, Integer.parseInt(CFU))) {

                out.println("<div class=\"alert alert-danger\" role=\"alert\">Attenzione! Il corso inserito è già stato registrato!</div>");
                out.close();

            } else {

                out.println("<div class=\"alert alert-success\" role=\"alert\">Il corso è stato registrato correttamente!</div>");
                out.close();
            }
        }

    }
}
