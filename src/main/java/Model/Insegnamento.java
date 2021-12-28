package Model;

public class Insegnamento {

    private String nome_docente;
    private String cognome_docente;
    private String corso;

    public Insegnamento(String nome_docente, String cognome_docente, String corso) {
        this.nome_docente = nome_docente;
        this.cognome_docente = cognome_docente;
        this.corso = corso;
    }

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

    public String getCorso() {
        return corso;
    }

    public void setCorso(String corso) {
        this.corso = corso;
    }

    @Override
    public String toString() {
        return "Insegnamento{" +
                "nome_docente='" + nome_docente + '\'' +
                ", cognome_docente='" + cognome_docente + '\'' +
                ", corso='" + corso + '\'' +
                '}';
    }


}
