package Model;

public class Ripetizione {

    private String nome;
    private String cognome;
    private String titolo;

    public Ripetizione(String nome, String cognome, String titolo) {
        this.nome = nome;
        this.cognome = cognome;
        this.titolo = titolo;
    }

    @Override
    public String toString() {
        return "Ripetizione{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", titolo='" + titolo + '\'' +
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

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }
}
