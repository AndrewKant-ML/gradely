package it.uniroma2.dicii.ispw.gradely.model;

import java.util.List;

public class User {
    private String name;
    private String surname;
    private String codiceFiscale;
    private String email;
    private String password;
    private List<AbstractRole> roles;

    public User(String name, String surname, String codiceFiscale, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.codiceFiscale = codiceFiscale;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<AbstractRole> getRoles() {
        return roles;
    }

    public void setRoles(List<AbstractRole> roles) {
        this.roles = roles;
    }
}
