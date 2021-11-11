package DAO;

public class Utente {

    private String account;
    private String password;
    private String ruolo;

    public Utente(String account, String password, String ruolo) {
        this.account = account;
        this.password = password;
        this.ruolo = ruolo;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", ruolo='" + ruolo + '\'' +
                '}';
    }
}
