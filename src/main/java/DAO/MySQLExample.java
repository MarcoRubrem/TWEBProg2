package DAO;

import java.util.ArrayList;
import java.util.Scanner;

import DAO.Persona;
import DAO.*;

import static DAO.DAO.Elenca_Ripetizioni;

/* Questo esempio dimostra come connettersi a un DB MySQL
   e recuperare dati da tabella. Per un completo, che
   mostra vari tipi di connessione, vedere:
   http://www.codejava.net/java-se/jdbc/connect-to-mysql-database-via-jdbc#CodeExample
 */
public class MySQLExample {

    public static void main(String[] args) {

        int scelta = 0;
        DAO.registerDriver();

        System.out.println("BENVENUTO NEL SITO DI RIPETIZIONI DI UNITO");
        while (scelta < 9) {
            System.out.println("COSA SI DESIDERA FARE?");
            System.out.println("1)Aggiungere un corso;");
            System.out.println("2)Rimuovere un corso;");
            System.out.println("3)Aggiungere un docente;");
            System.out.println("4)Rimuovere un docente;");
            System.out.println("5)Aggiungere una ripetizione");
            System.out.println("6)Rimuovere una ripetizione");
            System.out.println("7)Elencare Professori disponibili per ogni corso");
            System.out.println("8)Effettua Prenotazione");
            System.out.println("9)Disconnessione");
            System.out.print("Scelta: ");

            Scanner sc = new Scanner(System.in);

            switch (scelta = sc.nextInt()) {

                case 1:
                    DAO.Aggiungi_corso();
                    break;
                case 2:
                    DAO.Rimuovi_Corso();
                    break;
                case 3:
                    DAO.Aggiungi_Docente();
                    break;
                case 4:
                    DAO.Rimuovi_Docente();
                    break;
                case 5:
                    DAO.Aggiungi_Ripetizione();
                    break;
                case 6:
                    DAO.Rimuovi_Ripetizione();
                    break;
                case 7:
                    DAO.Elenca_Professori();
                    break;
                case 8:
                    DAO.prenotazione();
                    break;
                case 9:
                    DAO.Disconnected();
                    break;
                default:
                    System.out.println("ERRORE. DISCONNESSIONE");
                    DAO.Disconnected();

            }

        }

        System.out.println("FINE");
    }
}

