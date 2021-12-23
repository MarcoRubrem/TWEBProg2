package Model;

import java.sql.Time;

public class Ripetizione {

    private String nome;
    private String cognome;
    private String corso;
    private String giorno;
    private Time ora;
    private String stato;

    public Ripetizione(String nome, String cognome, String corso, String giorno, Time ora, String stato) {
        this.nome = nome;
        this.cognome = cognome;
        this.corso = corso;
        this.giorno = giorno;
        this.ora = ora;
        this.stato = stato;
    }

    @Override
    public String toString() {
        return "Ripetizione{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", corso='" + corso + '\'' +
                ", giorno='" + giorno + '\'' +
                ", ora='" + ora + '\'' +
                ", stato='" + stato + '\'' +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCorso() {
        return corso;
    }

    public void setCorso(String corso) {
        this.corso = corso;
    }

    public String getGiorno() {
        return giorno;
    }

    public void setGiorno(String giorno) {this.giorno = giorno;}

    public Time getOra() {return ora;}

    public void setOra(Time ora) {this.ora = ora;}

    public String getStato() {return stato;}

    public void setStato(String stato) {this.stato = stato;}
}
