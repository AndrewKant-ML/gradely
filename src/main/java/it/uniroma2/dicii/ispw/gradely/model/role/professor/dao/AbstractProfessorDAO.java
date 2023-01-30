package it.uniroma2.dicii.ispw.gradely.model.role.professor.dao;

import it.uniroma2.dicii.ispw.gradely.dao_interface.DAOInterface;
import it.uniroma2.dicii.ispw.gradely.model.role.professor.Professor;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.util.List;

public abstract class AbstractProfessorDAO implements DAOInterface<Professor> {
    protected static AbstractProfessorDAO instance;

    protected AbstractProfessorDAO(){
    }

    public abstract Professor getProfessorByUser(User user);
}
