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

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class Index_logged extends HttpServlet {


    public void init() {
        String message = "Hello World!";
        DAO.registerDriver();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        processRequest(request, response);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        HttpSession s = request.getSession();

        String account = request.getParameter("account");
        String pw = request.getParameter("pw");
        String urllogin = null;
        String urlhome = null;

        if (account != null && pw != null) {
            s.setAttribute("account", account);
            s.setAttribute("pw", pw);
        }

        urlhome = response.encodeURL("hello-servlet");

        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Home</title>\n" +
                "\n" +
                "    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css\" integrity=\"sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh\" crossorigin=\"anonymous\">\n" +
                "    <!-- JQuery -->\n" +
                "    <script src=\"https://code.jquery.com/jquery-3.4.1.slim.min.js\" integrity=\"sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n\" crossorigin=\"anonymous\"></script>\n" +
                "    <!-- Popper.js -->\n" +
                "    <script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js\" integrity=\"sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo\" crossorigin=\"anonymous\"></script>\n" +
                "    <!-- JavaScript -->\n" +
                "    <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js\" integrity=\"sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6\" crossorigin=\"anonymous\"></script>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<nav class=\"navbar navbar-expand-lg navbar-light bg-light\">\n" +
                "    <a class=\"navbar-brand\" href=\""+urlhome+"\">Prenotazioni UniTO</a>\n" +
                "    <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n" +
                "        <span class=\"navbar-toggler-icon\"></span>\n" +
                "    </button>\n" +
                "\n" +
                "    <div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">\n" +
                "        <ul class=\"navbar-nav mr-auto\">\n" +
                "            <li class=\"nav-item active\">\n" +
                "                <a class=\"nav-link\" href=\""+urlhome+"\">Home <span class=\"sr-only\">(current)</span></a>\n" +
                "            </li>\n" +
                "            <li class=\"nav-item dropdown\">\n" +
                "                <a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"navbarDropdown\" role=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\n" +
                "                    Elenchi\n" +
                "                </a>\n" +
                "                <div class=\"dropdown-menu\" aria-labelledby=\"navbarDropdown\">\n" +
                "                    <a class=\"dropdown-item\" href=\"#\">Corsi</a>\n" +
                "                    <a class=\"dropdown-item\" href=\"#\">Ripetizioni</a>\n" +
                "                    <a class=\"dropdown-item\" href=\"#\">Prenotazioni</a>\n" +
                "                </div>\n" +
                "            </li>\n" +
                "        </ul>\n" +
                "        <form class=\"form-inline my-2 my-lg-0\">\n" +
                "            <a href=\"Registrazione.jsp\" class=\"btn btn-outline-success\" type=\"button\">Registrati</a>\n" +
                "            <button type=\"button\" class=\"btn btn-danger\">Ciao "+s.getAttribute("account")+"</button>\n" +
                "        </form>\n" +
                "    </div>\n" +
                "</nav>\n" +
                "\n" +
                "\n" +
                "\n" +
                "</body>\n" +
                "</html>");
    }

    public void destroy() {
    }
}