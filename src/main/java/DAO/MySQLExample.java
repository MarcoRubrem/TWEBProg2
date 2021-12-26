package DAO;

import Model.Prenotazione;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Scanner;

/* Questo esempio dimostra come connettersi a un DB MySQL
   e recuperare dati da tabella. Per un completo, che
   mostra vari tipi di connessione, vedere:
   http://www.codejava.net/java-se/jdbc/connect-to-mysql-database-via-jdbc#CodeExample
 */
public class MySQLExample {

    public static void main(String[] args) {

        int scelta = 0;
        Time t1, t2;

        t1 = Time.valueOf("15:00:00");


        DAO_Prenotazioni.Registered_Booking("aaaaa", "Fisica", "lunedì", Time.valueOf("15:00:00"), "Liliana", "Ardissono");
        //DAO_Prenotazioni.Success_booking("aaaaa", "Fisica", "lunedì", Time.valueOf("15:00:00"), "Liliana", "Ardissono");

        System.out.println("FINE");
    }
}

