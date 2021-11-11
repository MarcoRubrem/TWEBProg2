package com.example.twebprog2;

import DAO.*;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import javax.print.Doc;
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
        String account = request.getParameter("account");
        String pw = request.getParameter("pw");
        ResultSet rs = null;
        String urllogin = null;
        String urlhome = null;
        boolean logged=false;
        HttpSession s = request.getSession();
        PrintWriter out = response.getWriter();

        if (account != null && pw != null) {
            s.setAttribute("account", account);
            s.setAttribute("pw", pw);
        }

        try {

            Statement st = getConn1().createStatement();
            rs = st.executeQuery("SELECT * FROM utente");

            while (rs.next()) {

                if (rs.getString("account").equals(s.getAttribute("account")) && rs.getString("password").equals(s.getAttribute("pw"))) {

                    logged=true;

                }
            }
            if(!logged) {

                try {

                    response.setContentType("text/plain");
                    out.println("ATTENZIONE: Account o password non corretti");
                    out.flush();
                }finally {
                    out.close();
                }
            }
            else {

                request.getRequestDispatcher("index.jsp").forward(request, response);

            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            out.close();
        }



    }


    public void destroy() {
    }
}