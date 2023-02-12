package it.uniroma2.dicii.ispw.gradely.beans_general;

public class StudentBean {

    private String codiceFiscale;

    private String matricola;

    public StudentBean(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public StudentBean(String codiceFiscale, String matricola) {
        this.codiceFiscale = codiceFiscale;
        this.matricola = matricola;
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
}
