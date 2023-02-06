package it.uniroma2.dicii.ispw.gradely.model.role.student;

import it.uniroma2.dicii.ispw.gradely.dao_interface.DAOInterface;
import it.uniroma2.dicii.ispw.gradely.exceptions.DAOException;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

public abstract class AbstractStudentDAO implements DAOInterface<Student> {
    protected static AbstractStudentDAO instance;

    protected AbstractStudentDAO(){

    }

    public abstract Student getStudentByUser(User user) throws DAOException;

}
