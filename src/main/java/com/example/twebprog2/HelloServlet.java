 package com.example.twebprog2;

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

@WebServlet("/ServletSessions")
public class HelloServlet extends HttpServlet {
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
        String userName = request.getParameter("utente");
        String sessionID = request.getParameter("sessione");
        HttpSession s = request.getSession();
        String jsessionID = s.getId(); // estraggo il session ID
        System.out.println("JSessionID:" + jsessionID);
        System.out.println("sessionID ricevuto:" + sessionID);
        System.out.println("userName ricevuto:" + userName);

        ServletContext ctx = getServletContext();
        RequestDispatcher rd;
        rd = ctx.getRequestDispatcher("/Registrazione.jsp");
        rd.forward(request, response);


    }
}
