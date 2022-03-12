package Servlet;
import DAO.*;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import javax.print.Doc;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import static DAO.DAO.*;

@WebServlet ("/reg")
public class Registrazione extends HttpServlet {

    public void init() {
        String message = "Hello World!";
        DAO.registerDriver();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            ProcessRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            ProcessRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void ProcessRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {


        response.setContentType("text/html");
        String account=request.getParameter("account");
        String pw=request.getParameter("pw");
        String radio = request.getParameter("Ruolo");

        HttpSession s = request.getSession();
        PrintWriter out = response.getWriter();


        if(account.equals("")){

            out.println("<div class=\"alert alert-danger\" role=\"alert\">Account obbligatorio</div>");
            out.close();
        }
        if(pw.equals("")){

            out.println("<div class=\"alert alert-danger\" role=\"alert\">Password obbligatoria</div>");
            out.close();
        }

        s.setAttribute("account", account);
        s.setAttribute("Ruolo", radio);

        String ris = DAO_utente.Registered_User(account, pw, radio);

        if (ris.equals("Cliente")) {

            out.print("Cliente|"+s.getAttribute("account"));
            out.flush();
            out.close();

        }
        else if(ris.equals("Amministratore")){

            out.print("Amministratore|"+s.getAttribute("account"));
            out.flush();
            out.close();
        }
        else{

            out.println("<div class=\"alert alert-danger\" role=\"alert\">Attenzione: Nome account già registrato!</div>");
            out.close();

        }


    }

    public void destroy() {}
}