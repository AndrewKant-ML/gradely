package it.uniroma2.dicii.ispw.gradely.model.role;

import it.uniroma2.dicii.ispw.gradely.enums.ExceptionMessagesEnum;
import it.uniroma2.dicii.ispw.gradely.enums.UserRoleEnum;
import it.uniroma2.dicii.ispw.gradely.exceptions.MissingAuthorizationException;
import it.uniroma2.dicii.ispw.gradely.model.role.professor.Professor;
import it.uniroma2.dicii.ispw.gradely.model.role.secretary.Secretary;
import it.uniroma2.dicii.ispw.gradely.model.role.student.Student;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

public abstract class AbstractRole {
    protected User user;

    protected AbstractRole(User user){
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCodiceFiscale(){
        return user.getCodiceFiscale();
    }

    public Professor getProfessorRole() throws MissingAuthorizationException {
        throw new MissingAuthorizationException(ExceptionMessagesEnum.MISSING_AUTH.message);
    }

    public Student getStudentRole() throws MissingAuthorizationException {
        throw new MissingAuthorizationException(ExceptionMessagesEnum.MISSING_AUTH.message);
    }

    public Secretary getSecretaryRole() throws MissingAuthorizationException {
        throw new MissingAuthorizationException(ExceptionMessagesEnum.MISSING_AUTH.message);
    }

    public UserRoleEnum getRoleEnumType() throws MissingAuthorizationException {
        try {
            getStudentRole();
            return UserRoleEnum.STUDENT;
        } catch (MissingAuthorizationException e) {
            try {
                getProfessorRole();
                return UserRoleEnum.PROFESSOR;
            } catch (MissingAuthorizationException ex) {
                getSecretaryRole();
                return UserRoleEnum.SECRETARY;
            }
        }
    }
}
