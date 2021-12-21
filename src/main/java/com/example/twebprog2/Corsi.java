package com.example.twebprog2;

import DAO.DAO_Corsi;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

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

        if(titolo.equals("")){

            out.println("<div class=\"alert alert-danger\" role=\"alert\">Titolo del corso obbligatorio</div>");
            out.close();
        }
        if(CFU.equals("") || Integer.parseInt(CFU) <= 0){

            out.println("<div class=\"alert alert-danger\" role=\"alert\">Numero di crediti non corretto</div>");
            out.close();
        }

        if(!DAO_Corsi.Registered_Courses(titolo, Integer.parseInt(CFU))){

            out.println("<div class=\"alert alert-danger\" role=\"alert\">Attenzione! Il corso inserito è già stato registrato!</div>");
            out.close();

        }
        else{

            out.println("<div class=\"alert alert-success\" role=\"alert\">Il corso è stato registrato correttamente!</div>");
            out.close();
        }

    }
}
