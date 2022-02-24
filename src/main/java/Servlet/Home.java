package Servlet;

import DAO.DAO;
import Model.Ripetizione;
import DAO.DAO_Ripetizioni;

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
import java.util.ArrayList;
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
                    "      <td></td>\n" +
                    "      <td></td>\n" +
                    "      <td></td>\n" +
                    "      <td></td>\n" +
                    "      <td></td>\n" +
                    "    </tr>");
            out.println(" <tr>\n" +
                    "      <td>16:00</td>\n" +
                    "      <td></td>\n" +
                    "      <td></td>\n" +
                    "      <td></td>\n" +
                    "      <td></td>\n" +
                    "      <td></td>\n" +
                    "    </tr>");
            out.println(" <tr>\n" +
                    "      <td>17:00</td>\n" +
                    "      <td></td>\n" +
                    "      <td></td>\n" +
                    "      <td></td>\n" +
                    "      <td></td>\n" +
                    "      <td></td>\n" +
                    "    </tr>");
            out.println(" <tr>\n" +
                    "      <td>18:00</td>\n" +
                    "      <td></td>\n" +
                    "      <td></td>\n" +
                    "      <td></td>\n" +
                    "      <td></td>\n" +
                    "      <td></td>\n" +
                    "    </tr>");
            out.println("  </tbody>\n" +
                    "</table>");

        }

        if(param.equals("Corsi")){



        }

        if(param.equals("Docenti")){


        }


    }
}
