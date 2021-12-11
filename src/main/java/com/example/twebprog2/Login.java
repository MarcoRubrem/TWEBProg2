package com.example.twebprog2;

import DAO.*;

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

@WebServlet ("/login")
public class Login extends HttpServlet {


    public void init() {
        String message = "Hello World!";
        DAO.registerDriver();
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

        if(account==""){

            out.println("<div class=\"alert alert-danger\" role=\"alert\">Account obbligatorio</div>");
            out.close();
        }
        if(pw==""){

            out.println("<div class=\"alert alert-danger\" role=\"alert\">Password obbligatoria</div>");
            out.close();
        }


        ResultSet rs;
        boolean logged=false;
        HttpSession s = request.getSession();

        if(account!=null && pw!=null){

            s.setAttribute("account", account);
            s.setAttribute("pw", pw);
        }


        try {
            Statement st = getConn1().createStatement();
            rs = st.executeQuery("SELECT * FROM utente");

            while (rs.next()) {

                if (rs.getString("account").equals(s.getAttribute("account")) && rs.getString("password").equals(s.getAttribute("pw"))) {

                    logged = true;
                }
            }

            if (logged) {

                out.print("loggato");
                out.flush();
                out.close();

            }
            else{

                out.println("<div class=\"alert alert-danger\" role=\"alert\">Attenzione: Nome account o password non corretti!</div>");
                out.close();


            }


        }catch(SQLException e){

            System.out.print(e.getMessage());
        }

    }


    public void destroy() {
    }
}