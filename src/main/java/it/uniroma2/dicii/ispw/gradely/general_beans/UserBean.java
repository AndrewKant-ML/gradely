package it.uniroma2.dicii.ispw.gradely.general_beans;

public class UserBean {

    private String name;
    private String surname;
    private String codiceFiscale;

    private String email;

    public UserBean(String name, String surname, String codiceFiscale, String email) {
        this.name = name;
        this.surname = surname;
        this.codiceFiscale = codiceFiscale;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public String getEmail() {
        return email;
    }
}
