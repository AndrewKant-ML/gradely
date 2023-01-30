package it.uniroma2.dicii.ispw.gradely.model.role;

import it.uniroma2.dicii.ispw.gradely.model.role.professor.Professor;
import it.uniroma2.dicii.ispw.gradely.model.role.secretary.Secretary;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

public abstract class AbstractRole {
    protected User user;

    protected AbstractRole(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Professor professor(){return null;}
    public Student student(){return null;}
    public Secretary secretary(){return null;}

}
