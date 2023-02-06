package it.uniroma2.dicii.ispw.gradely.use_cases.enroll_to_degree_course.beans;

public class UserData {

    private String userName;
    private String userSurname;
    private String userEmail;
    private String userCodiceFiscale;
    private String userMatricola;
    private Integer userRole;

    public UserData(String userName, String userSurname, String userEmail, String userCodiceFiscale, String userMatricola, Integer userRole) {
        this.userName = userName;
        this.userSurname = userSurname;
        this.userEmail = userEmail;
        this.userCodiceFiscale = userCodiceFiscale;
        this.userMatricola = userMatricola;
        this.userRole = userRole;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserCodiceFiscale() {
        return userCodiceFiscale;
    }

    public void setUserCodiceFiscale(String userCodiceFiscale) {
        this.userCodiceFiscale = userCodiceFiscale;
    }

    public String getUserMatricola() {
        return userMatricola;
    }

    public void setUserMatricola(String userMatricola) {
        this.userMatricola = userMatricola;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }
}
