 package com.example.twebprog2;

import DAO.DAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
import java.util.Objects;

import static DAO.DAO.getConn1;

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
        ResultSet rs;

        if(param.equals("Ripetizioni")){

            try {
                DAO.registerDriver();
                Statement st = getConn1().createStatement();
                rs = st.executeQuery("SELECT * FROM ripetizione");

                out.println("<table class=\"table table-striped\">\n" +
                        "  <thead>\n" +
                        "    <tr>\n" +
                        "      <th scope=\"col\">Nome Docente</th>\n" +
                        "      <th scope=\"col\">Cognome Docente</th>\n" +
                        "      <th scope=\"col\">Corso</th>\n" +
                        "    </tr>\n" +
                        "  </thead>\n" +
                        "  <tbody>");
                while(rs.next()) {

                    out.println("    <tr>\n" +
                            "      <td>"+rs.getString("nome")+"</td>\n" +
                            "      <td>"+rs.getString("cognome")+"</td>\n" +
                            "      <td>"+rs.getString("corso")+"</td>\n" +
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

        if(param.equals("Corsi")){


            out.close();

        }

        if(param.equals("Docenti")){


            out.close();

        }


    }
}
