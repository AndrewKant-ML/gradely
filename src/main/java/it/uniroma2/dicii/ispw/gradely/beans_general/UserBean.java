package it.uniroma2.dicii.ispw.gradely.beans_general;

import it.uniroma2.dicii.ispw.gradely.enums.UserRoleEnum;

public class UserBean {
    private String name;
    private String surname;
    private String codiceFiscale;
    private String email;
    private UserRoleEnum role;
    private String matricola;

    public UserBean(String name, String surname, String codiceFiscale, String email, UserRoleEnum role, String matricola) {
        this.name = name;
        this.surname = surname;
        this.codiceFiscale = codiceFiscale;
        this.email = email;
        this.role = role;
        this.matricola = matricola;
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

    public Integer getRole() {
        return this.role.type;
    }

    public void setRole(UserRoleEnum role) {
        this.role = role;
    }

    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }
}
