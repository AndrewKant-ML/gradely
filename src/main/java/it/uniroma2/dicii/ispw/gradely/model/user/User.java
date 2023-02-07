package it.uniroma2.dicii.ispw.gradely.model.user;

import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.WrongPasswordException;
import it.uniroma2.dicii.ispw.gradely.model.role.AbstractRole;

import java.time.LocalDate;

public class User {
    private String name;
    private String surname;
    private String codiceFiscale;
    private String email;
    private String password;
    private LocalDate registrationDate;
    private AbstractRole role;

    protected User(String name, String surname, String codiceFiscale, String email, String password, LocalDate registrationDate) {
        this.name = name;
        this.surname = surname;
        this.codiceFiscale = codiceFiscale;
        this.email = email;
        this.password = password;
        this.registrationDate = registrationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getSurname(){
        return surname;
    }

    public void setSurname(String surname){
        this.surname = surname;
    }

    public String getCodiceFiscale(){
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale){
        this.codiceFiscale = codiceFiscale;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public AbstractRole getRole() {
        return role;
    }

    public void setRole(AbstractRole role) {
        this.role = role;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Boolean checkPassword(String password) throws WrongPasswordException {
        if (this.password.equals(password)) return true;
        else throw new WrongPasswordException(ExceptionMessagesEnum.WRONG_PASSWORD.message);
    }

    public void changePassword(String oldPass, String newPass) throws WrongPasswordException {
        if (this.password.equals(oldPass)) {
            this.password = newPass;
        } else throw new WrongPasswordException(ExceptionMessagesEnum.WRONG_PASSWORD.message);
    }

    String getPassword(){
        return this.password;
    }
    void setPassword(String password){
        this.password = password;
    }
}
