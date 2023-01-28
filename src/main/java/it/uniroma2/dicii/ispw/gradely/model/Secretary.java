package it.uniroma2.dicii.ispw.gradely.model;

public class Secretary extends AbstractRole {
    public Secretary(User user) {
        setUser(user);
    }

    @Override
    public Secretary secretary() {
        return this;
    }
}
