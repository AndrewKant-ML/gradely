package it.uniroma2.dicii.ispw.gradely.beans_general;

public class LoginBean {

    private String tokenKey;
    private UserBean userBean;

    public LoginBean(String tokenKey, UserBean userBean) {
        this.tokenKey = tokenKey;
        this.userBean = userBean;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
}
