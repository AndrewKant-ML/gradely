package it.uniroma2.dicii.ispw.gradely.beans_general;

public class StudentBean {

    private String codiceFiscale;
    private String matricola;
    private UserBean user;

    public StudentBean(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public StudentBean(String codiceFiscale, String matricola) {
        this.codiceFiscale = codiceFiscale;
        this.matricola = matricola;
    }

    public StudentBean(String codiceFiscale, String matricola, UserBean user) {
        this.codiceFiscale = codiceFiscale;
        this.matricola = matricola;
        this.user = user;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }
}
