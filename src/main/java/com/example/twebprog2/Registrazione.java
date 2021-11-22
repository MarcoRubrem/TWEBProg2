package com.example.twebprog2;
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

        HttpSession s = request.getSession();
        boolean copia = false;
        PrintWriter out = response.getWriter();


        if(account==""){

            out.println("Account obbligatorio");
            out.close();
        }
        if(pw==""){

            out.println("Password obbligatoria");
            out.close();
        }

        if(account!=null && pw!=null){

            s.setAttribute("account", account);
            s.setAttribute("pw", pw);
        }

         try {

             Statement st = DAO.getConn1().createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM utente");

             while (rs.next()) {

                 if (rs.getString("account").equals(s.getAttribute("account")) && rs.getString("password").equals(s.getAttribute("pw"))) {

                     copia = true;
                 }
             }

             if (copia) {

                 out.println("Attenzione! Nome Account già registrato.");
                 out.close();
             } else {

                 int rs2 = st.executeUpdate("Insert into utente values('" + account + "', '" + pw + "', '" + radio + "')");
                 out.print("loggato");
                 out.close();

             }
         } catch (SQLException e) {

             System.out.println(e.getMessage());
         }
    }

    public void destroy() {}
}