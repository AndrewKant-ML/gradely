package it.uniroma2.dicii.ispw.gradely.model.role.professor.dao;

import it.uniroma2.dicii.ispw.gradely.model.role.professor.Professor;
import it.uniroma2.dicii.ispw.gradely.model.user.UserLazyFactory;
import it.uniroma2.dicii.ispw.gradely.model.user.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAOFS extends AbstractProfessorDAO {

    private ProfessorDAOFS(){ //TODO implementare costruttore vero
}

    public static AbstractProfessorDAO getInstance(){
        if (instance == null) {
            instance = new ProfessorDAOFS();
        }
        return instance;
    }

    @Override
    public Professor getProfessorByUser(User user) {
        return null; //TODO implementare exceptions
    }

    @Override
    public void insert(Professor professor) {

    }

    @Override
    public void cancel(Professor professor) {

    }

    @Override
    public void update(Professor professor) {

    }

    @Override
    public List<Professor> refresh(List<Professor> professors) {
        return null;
    }
}
