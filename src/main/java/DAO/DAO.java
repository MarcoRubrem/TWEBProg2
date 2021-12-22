package DAO;

import Model.Corso;
import Model.Docente;
import Model.Ripetizione;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DAO {

    private String message;
    private static final String url1 = "jdbc:mysql://localhost:3306/miodb";
    private static final String user = "root";
    private static final String password = "";
    private static Connection conn1 = null;

    public static Connection getConn1() {
        return conn1;
    }

    public static void setConn1(Connection conn1) {
        DAO.conn1 = conn1;
    }

    public static ArrayList<Docente> Elenca_Docenti() {

        ArrayList<Docente> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }

            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM docente");

            while (rs.next()) {
                Docente r=new Docente(rs.getString("nome"), rs.getString("cognome"));
                out.add(r);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return out;
    }


    public static ArrayList<Ripetizione> Elenca_Ripetizioni() {

        ArrayList<Ripetizione> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }

            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM ripetizione");

            while (rs.next()) {
                Ripetizione r=new Ripetizione(rs.getString("nome"), rs.getString("cognome"), rs.getString("corso"));
                out.add(r);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return out;
    }

    public static ArrayList<Corso> Elenca_corsi() {

        ArrayList<Corso> out = new ArrayList<>();
        try {
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }

            Statement st = conn1.createStatement();
            //int rs2=st.executeUpdate("Insert into persona values('Aldo', 'Baglio', '874356')");
            ResultSet rs = st.executeQuery("SELECT * FROM corso");

            while (rs.next()) {
                Corso c = new Corso(rs.getString("titolo"), rs.getInt("CFU"));
                out.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return out;
    }

    public static void registerDriver() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            System.out.println("Driver correttamente registrato");
            conn1 = DriverManager.getConnection(url1, user, password);
            if (conn1 != null) {
                System.out.println("Connected to the database test");
            }
        } catch (SQLException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }

    public static void Disconnected() {

        if (conn1 != null) {
            try {
                conn1.close();
                System.out.println("DISCONNESSIONE AVVENUTA CON SUCCESSO");
            } catch (SQLException e2) {
                System.out.println(e2.getMessage());
            }
        }
    }

    public static void Aggiungi_corso() {

        Scanner sc = new Scanner(System.in);
        try {

            Statement st = conn1.createStatement();

            System.out.println("\nTitolo: ");
            String titolo = sc.nextLine();
            System.out.println("CFU: ");
            int CFU = sc.nextInt();

            int rs2 = st.executeUpdate("Insert into corso values('" + titolo + "', '" + CFU + "')");


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void Rimuovi_Corso() {

        Scanner sc = new Scanner(System.in);
        try {

            Statement st = conn1.createStatement();
            System.out.println("\nCorsi disponibili:");
            ResultSet rs = st.executeQuery("SELECT * FROM corso");

            while (rs.next()) {
                System.out.println(rs.getString("Titolo") + " " + rs.getInt("CFU"));
            }

            System.out.println("scegliere il titolo: ");
            String titolo = sc.nextLine();

            int rs2 = st.executeUpdate("delete from corso where titolo like'" + titolo + "'");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void Aggiungi_Docente() {

        Scanner sc = new Scanner(System.in);
        try {

            Statement st = conn1.createStatement();

            System.out.println("\nNome: ");
            String nome = sc.nextLine();
            System.out.println("Cognome: ");
            String Cognome = sc.nextLine();



            int rs2 = st.executeUpdate("Insert into docente values('" + nome + "', '" + Cognome + "'");


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void Rimuovi_Docente() {

        Scanner sc = new Scanner(System.in);
        try {

            Statement st = conn1.createStatement();
            System.out.println("\nDocenti disponibili:");
            ResultSet rs = st.executeQuery("SELECT * FROM docente");

            while (rs.next()) {
                System.out.println(rs.getString("nome") + " " + rs.getString("cognome"));
            }

            System.out.println("\nscegliere il cognome: ");
            String cognome = sc.nextLine();

            int rs2 = st.executeUpdate("delete from docente where cognome like '" + cognome + "'");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void Aggiungi_Ripetizione() {

        Scanner sc = new Scanner(System.in);
        try {

            Statement st = conn1.createStatement();

            System.out.println("\nDocenti disponibili:");
            ResultSet rs = st.executeQuery("SELECT * FROM docente");

            while (rs.next()) {
                System.out.println(rs.getString("nome") + " " + rs.getString("cognome"));
            }

            System.out.println("\nCorsi disponibili:");
            rs = st.executeQuery("SELECT * FROM corso");

            while (rs.next()) {
                System.out.println(rs.getString("Titolo"));
            }

            System.out.println("Nome docente: ");
            String nome = sc.nextLine();
            System.out.println("Cognome docente: ");
            String cognome = sc.nextLine();
            System.out.println("Corso: ");
            String Corso = sc.nextLine();

            int rs2 = st.executeUpdate("Insert into ripetizione values('" + nome + "', '" + cognome + "', '" + Corso + "')");


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void Rimuovi_Ripetizione() {

        Scanner sc = new Scanner(System.in);
        try {

            Statement st = conn1.createStatement();
            System.out.println("\nRipetizioni disponibili:");
            ResultSet rs = st.executeQuery("SELECT * FROM ripetizione");

            while (rs.next()) {
                System.out.println(rs.getString("nome") + " " + rs.getString("cognome") + " " + rs.getString("corso"));
            }

            System.out.println("\nscegliere il docente (cognome): ");
            String cognome = sc.nextLine();
            System.out.println("scegliere il Corso: ");
            String Corso = sc.nextLine();

            int rs2 = st.executeUpdate("delete from ripetizione where cognome like '" + cognome + "' and corso like '" + Corso + "'");


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void Elenca_Professori() {

        Scanner sc = new Scanner(System.in);
        try {

            Statement st = conn1.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM corso");

            while (rs.next()) {
                System.out.println(rs.getString("titolo"));
            }
            System.out.println("\nSelezionare il corso: ");
            String Corso = sc.nextLine();

            rs = st.executeQuery("SELECT d.nome, d.cognome FROM docente d join ripetizione on(d.nome=ripetizione.nome and d.cognome=ripetizione.cognome) where corso like '"+Corso+"'");

            while (rs.next()) {
                System.out.println(rs.getString("nome") + " " + rs.getString("cognome"));
            }
            System.out.print("\n");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void prenotazione(){

        Scanner sc = new Scanner(System.in);
        try {

            Statement st = conn1.createStatement();

            System.out.println("\nInserire il proprio nome utente: ");
            String utente=sc.nextLine();
            System.out.println("\nSelezionare il corso: ");
            ResultSet rs = st.executeQuery("SELECT * FROM corso");

            while (rs.next()) {
                System.out.println(rs.getString("titolo"));
            }
            System.out.println("\nScelta: ");
            String Corso = sc.nextLine();
            System.out.println("\nSelezionare il docente: ");

            rs = st.executeQuery("SELECT d.nome, d.cognome FROM docente d join ripetizione on(d.nome=ripetizione.nome and d.cognome=ripetizione.cognome) where corso like '"+Corso+"'");
            while (rs.next()) {
                System.out.println(rs.getString("nome") + " " + rs.getString("cognome"));
            }

            System.out.println("\nnome: ");
            String docente_nome = sc.nextLine();
            System.out.println("cognome: ");
            String docente_cognome = sc.nextLine();

            int mese = 0;
            while(mese<1 || mese>12) {
                System.out.println("Inserire il mese: ");
                mese = sc.nextInt();
            }


        }catch(SQLException e){

            System.out.println(e.getMessage());
        }
    }
}