package Servlet;

import DAO.*;

import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet ("/login")
public class Login extends HttpServlet {


    public void init() {
        String message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
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

        String account = request.getParameter("account");
        String pw = request.getParameter("pw");
        HttpSession s = request.getSession();

        if(account.equals("")){

            out.println("<div class=\"alert alert-danger\" role=\"alert\">Account obbligatorio</div>");
            out.close();
        }
        if(pw.equals("")){

            out.println("<div class=\"alert alert-danger\" role=\"alert\">Password obbligatoria</div>");
            out.close();
        }

        s.setAttribute("account", account);
        String ris = DAO_utente.Logged_user(account, pw);


        if (ris.equals("user_not_found")) {

            out.println("<div class=\"alert alert-danger\" role=\"alert\">Attenzione: Nome account o password non corretti!</div>");
            out.close();

        }
        else{

            out.print(ris+"|"+s.getAttribute("account"));
            s.setAttribute("Ruolo", ris);
            out.flush();
            out.close();
        }

    }


    public void destroy() {
    }
}