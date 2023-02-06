package it.uniroma2.dicii.ispw.gradely.model.role.professor;

import it.uniroma2.dicii.ispw.gradely.dao_interface.DAOInterface;
import it.uniroma2.dicii.ispw.gradely.exceptions.*;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

public abstract class AbstractProfessorDAO implements DAOInterface<Professor> {
    protected static AbstractProfessorDAO instance;

    protected AbstractProfessorDAO(){
    }

    public abstract Professor getProfessorByUser(User user) throws DAOException, UserNotFoundException, PropertyException, ResourceNotFoundException;
}
