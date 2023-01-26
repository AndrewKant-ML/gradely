package it.uniroma2.dicii.ispw.gradely.model;

public abstract class AbstractRole {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
