package it.uniroma2.dicii.ispw.gradely.model.role.professor.dao;

import it.uniroma2.dicii.ispw.gradely.model.role.professor.Professor;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.util.List;

public abstract class AbstractProfessorDAO {
    protected static AbstractProfessorDAO instance;
    protected List<Professor> professors;

    protected AbstractProfessorDAO(){
    }

    public abstract Professor getProfessorByUser(User user);
    public abstract void update(Professor professor);
    public abstract List<Professor> refresh(List<Professor> professors);

}
