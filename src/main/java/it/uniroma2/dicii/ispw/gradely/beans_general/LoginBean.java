package it.uniroma2.dicii.ispw.gradely.beans_general;

public class LoginBean {

    private String tokenKey;
    private Integer userRole;

    public LoginBean(String tokenKey, Integer userRole) {
        this.tokenKey = tokenKey;
        this.userRole = userRole;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }
}
