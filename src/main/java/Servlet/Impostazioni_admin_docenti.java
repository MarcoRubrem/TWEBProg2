package Servlet;
import DAO.*;
import Model.Corso;
import Model.Docente;

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

@WebServlet ("/Impostazioni_admin_docenti")
public class Impostazioni_admin_docenti extends HttpServlet {


    public void init() {
        String message = "Hello World!";
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
        PrintWriter out = response.getWriter();

        String nome = request.getParameter("Nome");
        String cognome = request.getParameter("Cognome");
        ArrayList<Docente> dc = DAO_Docenti.Elenca_Docenti();

        if(nome.equals("remove") && cognome.equals("0")){

            ResultSet rs;

            registerDriver();
            Statement st = getConn1().createStatement();

            rs = st.executeQuery("select * from docente");

            Rem_tab(out, dc);
        }
        else if(cognome.equals("1000")){

            if(nome.length()==0){

                ResultSet rs;

                registerDriver();
                Statement st = getConn1().createStatement();

                rs = st.executeQuery("select * from docente");

                Rem_tab(out, dc);

            }
            else {

                String[] Dc_rem = nome.split(",");

                for (int i = 0; i < Dc_rem.length; i++) {

                    String[] Dc_split = Dc_rem[i].split("/");
                    String nome_rem = Dc_split[0];
                    String cognome_rem = Dc_split[1];


                    DAO_Docenti.Remove_Teachers(nome_rem, cognome_rem);

                }

                ResultSet rs;

                registerDriver();
                Statement st = getConn1().createStatement();

                rs = st.executeQuery("select * from docente");

                Rem_tab(out, dc);
            }
        }
        else{

            if (nome.equals("")) {

                out.println("<div class=\"alert alert-danger\" role=\"alert\">Nome obbligatorio</div>");
                out.close();
            }
            if (cognome.equals("")) {

                out.println("<div class=\"alert alert-danger\" role=\"alert\">Cognome obbligatorio</div>");
                out.close();
            }

            if (DAO_Docenti.Registered_teacher(nome, cognome)) {

                out.println("<div class=\"alert alert-success\" role=\"alert\">Docente inserito correttamente!</div>");
                out.close();

            } else {

                out.println("<div class=\"alert alert-danger\" role=\"alert\">ATTENZIONE! Docente già inserito precedentemente</div>");
                out.close();

            }
        }





    }

    private void Rem_tab(PrintWriter out, ArrayList<Docente> dc) {
        out.print("<table class=\"table table-striped\">\n" +
                "  <thead>\n" +
                "    <tr>\n" +
                "      <th scope=\"col\">Nome</th>\n" +
                "      <th scope=\"col\">Cognome</th>\n" +
                "      <th scope=\"col\">Elimina</th>\n" +
                "    </tr>\n" +
                "  </thead>\n" +
                "  <tbody>\n");

        for(Docente d: dc){

            out.print("<tr>\n" +
                    "      <td>"+d.getNome()+"</td>\n" +
                    "      <td>"+d.getCognome()+"</td>\n" +
                    "      <td><input type=\"checkbox\" value=\""+d.getNome()+"/"+d.getCognome()+"\" name=\"docente_rem\"></td>\n" +
                    "    </tr>\n");
        }

        out.print("</tbody>\n" +
                "</table> ");
        out.close();
        DAO.Disconnected();
    }
}