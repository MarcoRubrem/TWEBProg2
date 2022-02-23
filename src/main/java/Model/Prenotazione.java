package Model;

import java.sql.Time;

public class Prenotazione {

    private String Utente;
    private String Corso;
    private String Giorno;
    private String ora;
    private String nome_docente;
    private String cognome_docente;
    private String Stato;

    public Prenotazione(String utente, String corso, String giorno, String ora, String nome_docente, String cognome_docente, String stato) {
        Utente = utente;
        Corso = corso;
        Giorno = giorno;
        this.ora = ora;
        this.nome_docente = nome_docente;
        this.cognome_docente = cognome_docente;
        Stato = stato;
    }

    public String getUtente() {
        return Utente;
    }

    public void setUtente(String utente) {
        Utente = utente;
    }

    public String getCorso() {
        return Corso;
    }

    public void setCorso(String corso) {
        Corso = corso;
    }

    public String getGiorno() {
        return Giorno;
    }

    public void setGiorno(String giorno) {
        Giorno = giorno;
    }

    public String getOra() {return ora;}

    public void setOra(String ora) {this.ora = ora;}

    public String getNome_docente() {
        return nome_docente;
    }

    public void setNome_docente(String nome_docente) {
        this.nome_docente = nome_docente;
    }

    public String getCognome_docente() {
        return cognome_docente;
    }

    public void setCognome_docente(String cognome_docente) {
        this.cognome_docente = cognome_docente;
    }

    public String getStato() {
        return Stato;
    }

    public void setStato(String stato) {
        Stato = stato;
    }
}
