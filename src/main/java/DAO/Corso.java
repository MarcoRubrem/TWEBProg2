package DAO;

public class Corso {

    private String titolo;
    private int CFU;

    public Corso(String titolo, int CFU) {
        this.titolo = titolo;
        this.CFU = CFU;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public int getCFU() {
        return CFU;
    }

    public void setCFU(int CFU) {
        this.CFU = CFU;
    }

    @Override
    public String toString() {
        return "Corso{" +
                "titolo='" + titolo + '\'' +
                ", CFU=" + CFU +
                '}';
    }
}
