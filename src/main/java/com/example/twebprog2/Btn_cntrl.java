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

@WebServlet ("/btn")
public class Btn_cntrl extends HttpServlet {


    public void init() {
        String message = "Hello World!";
        DAO.registerDriver();
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

        HttpSession s = request.getSession();
        PrintWriter out = response.getWriter();

        ServletContext ctx = getServletContext();
        String action = request.getParameter("btn");
        RequestDispatcher rd = null;
        
        if (action!=null) {
            if (action.equals("home")) {
                rd = ctx.getRequestDispatcher("/index.jsp");
            }
            else if (action.equals("Corsi")) {
                rd = ctx.getRequestDispatcher("/Corsi");
            }
            else if (action.equals("Registrazione")) {
                rd = ctx.getRequestDispatcher("/Registrazione.jsp");
            }
            else if (action.equals("Login")){
                rd = ctx.getRequestDispatcher("/Login.jsp");

            }
            else if (action.equals("Logout")){
                s.invalidate();
                rd = ctx.getRequestDispatcher("/index.jsp");

            }

        }
        rd.forward(request, response);



    }


    public void destroy() {
    }
}